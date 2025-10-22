package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotUpdateRequest;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotUpdateAdminRequest;
import com.axelor.apps.selllicenseplates2.exception.CarNumberLotNotFoundException;
import com.axelor.apps.selllicenseplates2.mapper.CarNumberLotMapper;
import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import com.axelor.apps.selllicenseplates2.model.Region;
import com.axelor.apps.selllicenseplates2.model.User;
import com.axelor.apps.selllicenseplates2.repository.CarNumberLotRepository;
import com.axelor.apps.selllicenseplates2.service.CarNumberLotService;
import com.axelor.apps.selllicenseplates2.service.RegionService;
import com.axelor.apps.selllicenseplates2.service.UserService;
import com.axelor.apps.selllicenseplates2.specification.CarNumberLotSpecification;
import com.axelor.apps.selllicenseplates2.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarNumberLotServiceImpl implements CarNumberLotService {

    private final CarNumberLotRepository carNumberLotRepository;
    private final CarNumberLotMapper carNumberLotMapper;
    private final RegionService regionService;
    private final UserService userService;

    @Value("${app.markup.percent}")
    private Integer percentMarkup;
    @Value("${app.defaultPhoneNumber}")
    private String changeNumber;

    @Override
    public CarNumberLotDto createCarNumberLots(CarNumberLotCreateRequest request) {
        CarNumberLot carNumberLot = fromRequest(request);
        String authorEmail = AuthUtils.getCurrentUserEmail();
        User author = userService.findByEmail(authorEmail);
        log.info("Creating CarNumberLot by user: {}", author);
        carNumberLot.setAuthor(author);
        carNumberLot.setPhoneNumber(changeNumber);
        carNumberLotRepository.save(carNumberLot);
        return carNumberLotMapper.toDto(carNumberLot);
    }

    @Override
    public void createCarNumberLotAndRegister(CarNumberLotCreateAndRegisterRequest request) {
        User user = userService.createUserFromCarNumberLotRequest(request);
        CarNumberLot carNumberLot = fromRequest(request);
        carNumberLot.setAuthor(user);
        carNumberLot.setPhoneNumber(user.getChangedPhoneNumber());
        carNumberLotRepository.save(carNumberLot);
    }

    @Override
    public List<CarNumberLotDto> getCarNumberLots(Long regionId, Boolean identicalNumbers, Boolean identicalLetters, String sort) {
        Specification<CarNumberLot> spec = CarNumberLotSpecification
                .hasIdenticalLetters(identicalNumbers)
                .and(CarNumberLotSpecification.hasIdenticalNumbers(identicalLetters))
                .and(CarNumberLotSpecification.hasRegion(regionId))
                .and(CarNumberLotSpecification.orderByCreatedDate(sort))
                .and(CarNumberLotSpecification.isDeleted(false))
                .and(CarNumberLotSpecification.isSold(false));

        List<CarNumberLot> carNumberLots = carNumberLotRepository.findAll(spec);
        return carNumberLotMapper.toListDto(carNumberLots);
    }

    @Override
    public CarNumberLotDto getCarNumberLotById(Long id) {
        CarNumberLot carNumberLot = findById(id);
        return carNumberLotMapper.toDto(carNumberLot);
    }

    @Override
    public List<CarNumberLotDto> getMyCarNumberLots() {
        String currentUserEmail = AuthUtils.getCurrentUserEmail();
        List<CarNumberLot> carNumberLots = carNumberLotRepository.findByAuthor_Email(currentUserEmail);
        return carNumberLotMapper.toListDto(carNumberLots);
    }

    @Override
    public CarNumberLotDto updateCarNumberLot(Long id, CarNumberLotUpdateRequest request, User user) {
        CarNumberLot existingLot = findById(id);
        Region region = regionService.getRegionById(request.getRegionId());

        if(!user.equals(existingLot.getAuthor()) && !user.getIsAdmin()) {
            throw new IllegalArgumentException("Пользователь не является автором номерного знака с ID: " + id);
        } else {
            existingLot.setComment(request.getComment());

            existingLot.setFirstDigit(request.getFirstDigit());
            existingLot.setSecondDigit(request.getSecondDigit());
            existingLot.setThirdDigit(request.getThirdDigit());
            existingLot.setFirstLetter(request.getFirstLetter());
            existingLot.setSecondLetter(request.getSecondLetter());
            existingLot.setThirdLetter(request.getThirdLetter());

            String fullNumber = request.getFirstLetter()
                    + request.getFirstDigit()
                    + request.getSecondDigit()
                    + request.getThirdDigit()
                    + request.getSecondLetter()
                    + request.getThirdLetter();

            if (!isNumberUnique(fullNumber, request.getRegionId())) {
                throw new IllegalArgumentException("Номерной знак: " + fullNumber + " в регионе: " + region.getRegionCode() + " уже существует");
            }

            existingLot.setRegion(region);

            existingLot.setUpdatedDate(Instant.now());
            carNumberLotRepository.save(existingLot);
            return carNumberLotMapper.toDto(existingLot);
        }
    }

    @Override
    public List<CarNumberLotAdminDto> getCarNumberLotsAdminData() {
        List<CarNumberLot> carNumberLots = carNumberLotRepository.findAll();
        return carNumberLotMapper.toAdminDtoList(carNumberLots);
    }

    @Override
    public CarNumberLotAdminDto updateCarNumberLotAdmin(Long lotId, CarNumberLotUpdateAdminRequest request) {
//        CarNumberLot existingLot = findById(lotId);
//
//        carNumberLotRepository.save(existingLot);
        return null;
    }

    private CarNumberLot findById(Long id) {
        return carNumberLotRepository.findById(id)
                .orElseThrow(() -> new CarNumberLotNotFoundException("Номерной знак не найден с ID: " + id));
    }

    private CarNumberLot fromRequest(CarNumberLotCreateRequest request) {
        String fullNumber = request.getFirstLetter()
                + request.getFirstDigit()
                + request.getSecondDigit()
                + request.getThirdDigit()
                + request.getSecondLetter()
                + request.getThirdLetter();

        Region region = regionService.getRegionById(request.getRegionId());

        if (!isNumberUnique(fullNumber, region.getId())) {
            throw new IllegalArgumentException("Номерной знак: " + fullNumber + " в регионе: "
                    + region.getRegionCode() + " уже существует");
        }

        BigDecimal markupPrice = request.getPrice() == null ? BigDecimal.ZERO :
                request.getPrice().multiply(BigDecimal.valueOf(percentMarkup)).divide(BigDecimal.valueOf(100));

        BigDecimal finalPrice = request.getPrice() == null ? BigDecimal.ZERO :
                request.getPrice().add(markupPrice);

        return CarNumberLot.builder()
                .firstDigit(request.getFirstDigit())
                .comment(request.getComment())
                .createdDate(Instant.now())
                .firstLetter(request.getFirstLetter())
                .originalPrice(request.getPrice())
                .secondDigit(request.getSecondDigit())
                .region(region)
                .isSold(false)
                .isDeleted(false)
                .thirdDigit(request.getThirdDigit())
                .updatedDate(Instant.now())
                .secondLetter(request.getSecondLetter())
                .thirdLetter(request.getThirdLetter())
                .fullCarNumber(fullNumber)
                .markupPrice(finalPrice)
                .build();
    }

    private boolean isNumberUnique(String fullCarNumber, Long regionId) {
        return carNumberLotRepository.findByFullCarNumberAndRegionId(fullCarNumber, regionId).isEmpty();
    }

}
