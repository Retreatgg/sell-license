package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<UserAdminDto> getAllUsers();
    UserAdminDto updateUser(Long userId, UserAdminUpdateDto userAdminUpdateDto);
}
