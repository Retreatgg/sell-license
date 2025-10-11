package com.axelor.apps.selllicenseplates2.controller;

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
    public ResponseEntity<UserAdminDto> updateUser(@PathVariable Long userId, @RequestBody UserAdminUpdateDto userAdminUpdateDto) {
        return ResponseEntity.ok(adminService.updateUser(userId, userAdminUpdateDto));
    }

}
