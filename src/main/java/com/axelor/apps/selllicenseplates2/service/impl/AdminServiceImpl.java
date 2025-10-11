package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminUpdateDto;
import com.axelor.apps.selllicenseplates2.service.AdminService;
import com.axelor.apps.selllicenseplates2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    @Override
    public List<UserAdminDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserAdminDto updateUser(Long userId, UserAdminUpdateDto userAdminUpdateDto) {
        return userService.updateUser(userId, userAdminUpdateDto);
    }
}
