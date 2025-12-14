package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.CarNumberLotUpdateRequest;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotUpdateAdminRequest;
import com.axelor.apps.selllicenseplates2.enums.CarNumberCategory;

import java.util.List;

public interface CarNumberLotService {
    CarNumberLotDto createCarNumberLots(CarNumberLotCreateRequest request);
    void createCarNumberLotAndRegister(CarNumberLotCreateAndRegisterRequest request);
    List<CarNumberLotDto> getCarNumberLots(Long regionId, Boolean identicalNumbers, Boolean identicalLetters, String sort, CarNumberCategory category);
    CarNumberLotDto getCarNumberLotById(Long id);
    List<CarNumberLotDto> getMyCarNumberLots();
    CarNumberLotDto updateCarNumberLot(Long id, CarNumberLotUpdateRequest request);
    List<CarNumberLotAdminDto> getCarNumberLotsAdminData();
    CarNumberLotAdminDto updateCarNumberLotAdmin(Long lotId, CarNumberLotUpdateAdminRequest request);
    void deleteCarNumberLot(Long id);
    CarNumberLotAdminDto confirmCarNumberLot(Long lotId);
}
