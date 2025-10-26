package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotUpdateAdminRequest;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<UserAdminDto> getAllUsers();
    UserAdminDto updateUser(Long userId, UserAdminUpdateDto userAdminUpdateDto);
    List<CarNumberLotAdminDto> getCarNumberLotsAdminData();
    CarNumberLotAdminDto updateCarNumberLot(Long lotId, CarNumberLotUpdateAdminRequest request);
    CarNumberLotAdminDto confirmCarNumberLot(Long lotId);
    void deleteCarNumberLot(Long lotId);
}
