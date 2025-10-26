package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotCreateAndRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.UserDto;
import com.axelor.apps.selllicenseplates2.dto.UserLoginRequest;
import com.axelor.apps.selllicenseplates2.dto.UserRegisterRequest;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminUpdateDto;
import com.axelor.apps.selllicenseplates2.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUserFromCarNumberLotRequest(CarNumberLotCreateAndRegisterRequest request);
    List<UserAdminDto> getAllUsers();
    UserAdminDto updateUser(Long userId, UserAdminUpdateDto userAdminUpdateDto);
    UserDto register(UserRegisterRequest request);

    User findByEmail(String username);
    UserDto login(UserLoginRequest request);
    void deleteUser(Long userId);
}
