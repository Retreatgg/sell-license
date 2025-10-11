package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateRequest;

import java.util.List;

public interface CarNumberLotService {
    void createCarNumberLots(CarNumberLotCreateRequest request);
    void createCarNumberLotAndRegister(CarNumberLotCreateAndRegisterRequest request);
    List<CarNumberLotDto> getCarNumberLots(Long regionId, Boolean identicalNumbers, Boolean identicalLetters, String sort);
    CarNumberLotDto getCarNumberLotById(Long id);
}
