package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.*;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminUpdateDto;
import com.axelor.apps.selllicenseplates2.mapper.RoleMapper;
import com.axelor.apps.selllicenseplates2.mapper.UserMapper;
import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import com.axelor.apps.selllicenseplates2.model.Role;
import com.axelor.apps.selllicenseplates2.model.User;
import com.axelor.apps.selllicenseplates2.repository.UserRepository;
import com.axelor.apps.selllicenseplates2.service.RoleService;
import com.axelor.apps.selllicenseplates2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Value("${app.defaultPhoneNumber}")
    private String DEFAULT_CHANGED_NUMBER;

    @Override
    public User createUserFromCarNumberLotRequest(CarNumberLotCreateAndRegisterRequest request) {
        if (isEmailExists(request.getEmail())) {
            throw new IllegalArgumentException("Пользователь с почтой: " + request.getEmail() + " уже существует");
        }
        User user = User.builder()
                .email(request.getEmail())
                .enabled(true)
                .fullName(request.getFullName())
                .originalPhoneNumber(request.getPhoneNumber())
                .changedPhoneNumber(DEFAULT_CHANGED_NUMBER)
                .role(roleService.getRoleForUser())
                .password(encoder.encode(request.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<UserAdminDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toAdminDtoList(users);
    }

    @Override
    public UserAdminDto updateUser(Long userId, UserAdminUpdateDto userAdminUpdateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID: " + userId + " не найден"));

        user.setEnabled(userAdminUpdateDto.getEnabled());
        user.setFullName(userAdminUpdateDto.getFullName());
        user.setChangedPhoneNumber(userAdminUpdateDto.getChangedPhoneNumber());
        user.setOriginalPhoneNumber(userAdminUpdateDto.getOriginalPhoneNumber());

        User updatedUser = userRepository.save(user);
        return userMapper.toAdminDto(updatedUser);
    }

    @Override
    public UserDto register(UserRegisterRequest request) {
        if (isEmailExists(request.getEmail())) {
            throw new IllegalArgumentException("Пользователь с почтой: " + request.getEmail() + " уже существует");
        }
        User user = User.builder()
                .email(request.getEmail())
                .enabled(true)
                .fullName(request.getFullName())
                .originalPhoneNumber(request.getPhoneNumber())
                .changedPhoneNumber(DEFAULT_CHANGED_NUMBER)
                .role(roleService.getRoleForUser())
                .password(encoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с почтой: " + username + " не найден"));
    }

    @Override
    public UserDto login(UserLoginRequest request) {
        User user = findByEmail(request.getEmail());
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }

        UserDto userDto = userMapper.toDto(user);
        com.axelor.apps.selllicenseplates2.model.Role role = roleService.getRoleForUser();
        RoleDto roleDto = roleMapper.toDto(role);
        userDto.setRoleDto(roleDto);
        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID: " + userId + " не найден"));
        userRepository.delete(user);
    }

    private boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
