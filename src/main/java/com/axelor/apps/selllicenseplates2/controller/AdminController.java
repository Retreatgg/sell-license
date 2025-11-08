package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotUpdateAdminRequest;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminUpdateDto;
import com.axelor.apps.selllicenseplates2.service.AdminService;
import com.axelor.apps.selllicenseplates2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserAdminDto>> getUsers() {
        List<UserAdminDto> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserAdminDto> updateUser(@PathVariable(name = "userId") Long userId, @RequestBody UserAdminUpdateDto userAdminUpdateDto) {
        return ResponseEntity.ok(adminService.updateUser(userId, userAdminUpdateDto));
    }

    @GetMapping("/car-number-lots")
    public ResponseEntity<List<CarNumberLotAdminDto>> getCarNumberLotsAdminData() {
        List<CarNumberLotAdminDto> carNumberLotAdminDto = adminService.getCarNumberLotsAdminData();
        return ResponseEntity.ok(carNumberLotAdminDto);
    }

    @PutMapping("/car-number-lots/{lotId}")
    public ResponseEntity<CarNumberLotAdminDto> updateCarNumberLot(@PathVariable(name = "lotId") Long lotId, @RequestBody CarNumberLotUpdateAdminRequest request) {
        CarNumberLotAdminDto updatedLot = adminService.updateCarNumberLot(lotId, request);
        return ResponseEntity.ok(updatedLot);
    }

    @PatchMapping("/car-number-lots/{lotId}/cofirm")
    public ResponseEntity<CarNumberLotAdminDto> confirmCarNumberLot(@PathVariable(name = "lotId") Long lotId) {
        CarNumberLotAdminDto confirmedLot = adminService.confirmCarNumberLot(lotId);
        return ResponseEntity.ok(confirmedLot);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/car-number-lots/{lotId}")
    public ResponseEntity<Void> deleteCarNumberLot(@PathVariable(name = "lotId") Long lotId) {
        adminService.deleteCarNumberLot(lotId);
        return ResponseEntity.noContent().build();
    }


}
