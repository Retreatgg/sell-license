package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotUpdateRequest;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotUpdateAdminRequest;
import com.axelor.apps.selllicenseplates2.enums.CarNumberCategory;
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
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public void createCarNumberLotAndRegister(CarNumberLotCreateAndRegisterRequest request) {
        User user = userService.createUserFromCarNumberLotRequest(request);
        CarNumberLot carNumberLot = fromRequest(request);
        carNumberLot.setAuthor(user);
        carNumberLot.setPhoneNumber(user.getChangedPhoneNumber());
        carNumberLotRepository.save(carNumberLot);
    }

    @Override
    public List<CarNumberLotDto> getCarNumberLots(Long regionId, Boolean identicalNumbers, Boolean identicalLetters, String sort, CarNumberCategory category) {
        Specification<CarNumberLot> spec = CarNumberLotSpecification
                .hasIdenticalLetters(identicalNumbers)
                .and(CarNumberLotSpecification.hasCategory(category))
                .and(CarNumberLotSpecification.hasIdenticalNumbers(identicalLetters))
                .and(CarNumberLotSpecification.hasRegion(regionId))
                .and(CarNumberLotSpecification.orderByCreatedDate(sort))
                .and(CarNumberLotSpecification.isDeleted(false))
                .and(CarNumberLotSpecification.isConfirm(true))
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

        Specification<CarNumberLot> specification = CarNumberLotSpecification
                .isDeleted(false)
                .and(CarNumberLotSpecification.byAuthorEmail(currentUserEmail));

        List<CarNumberLot> carNumberLots = carNumberLotRepository.findAll(specification);
        return carNumberLotMapper.toListDto(carNumberLots);
    }

    @Override
    public CarNumberLotDto updateCarNumberLot(Long id, CarNumberLotUpdateRequest request) {
        CarNumberLot existingLot = findById(id);
        Region region = regionService.getRegionById(request.getRegionId());

        String currentUserEmail = AuthUtils.getCurrentUserEmail();
        User user = userService.findByEmail(currentUserEmail);

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
            existingLot.setIsConfirm(false);

            existingLot.setRegion(region);

            existingLot.setUpdatedDate(Instant.now());
            carNumberLotRepository.save(existingLot);
            return carNumberLotMapper.toDto(existingLot);
        }
    }

    @Override
    public List<CarNumberLotAdminDto> getCarNumberLotsAdminData() {
        Specification<CarNumberLot> specification = CarNumberLotSpecification.isDeleted(false);
        List<CarNumberLot> carNumberLots = carNumberLotRepository.findAll(specification);
        return carNumberLotMapper.toAdminDtoList(carNumberLots);
    }

    @Override
    public CarNumberLotAdminDto updateCarNumberLotAdmin(Long lotId, CarNumberLotUpdateAdminRequest request) {
        CarNumberLot existingLot = findById(lotId);

        existingLot.setRegion(regionService.getRegionById(request.getRegionId()));
        existingLot.setComment(request.getComment());
        existingLot.setFirstLetter(request.getFirstLetter());
        existingLot.setSecondLetter(request.getSecondLetter());
        existingLot.setThirdLetter(request.getThirdLetter());
        existingLot.setFirstDigit(request.getFirstDigit());
        existingLot.setSecondDigit(request.getSecondDigit());
        existingLot.setThirdDigit(request.getThirdDigit());
        carNumberLotRepository.save(existingLot);

        return carNumberLotMapper.toAdminDto(existingLot);
    }

    @Override
    public void deleteCarNumberLot(Long id) {
        User currentUser = userService.findByEmail(AuthUtils.getCurrentUserEmail());
        CarNumberLot existingLot = findById(id);

        if (!currentUser.getIsAdmin() && currentUser != existingLot.getAuthor()) {
            throw new IllegalArgumentException("Только администратор может удалить номерной знак с ID: " + id);
        }
        existingLot.setIsDeleted(true);
        carNumberLotRepository.save(existingLot);
    }

    @Override
    public CarNumberLotAdminDto confirmCarNumberLot(Long lotId) {
        CarNumberLot existingLot = findById(lotId);
        existingLot.setIsConfirm(true);
        carNumberLotRepository.save(existingLot);
        return carNumberLotMapper.toAdminDto(existingLot);
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
                .isConfirm(false)
                .thirdDigit(request.getThirdDigit())
                .updatedDate(Instant.now())
                .secondLetter(request.getSecondLetter())
                .thirdLetter(request.getThirdLetter())
                .fullCarNumber(fullNumber)
                .markupPrice(finalPrice)
                .build();
    }

}
