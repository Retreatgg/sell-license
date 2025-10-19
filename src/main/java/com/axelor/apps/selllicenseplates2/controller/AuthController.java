package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.UserDto;
import com.axelor.apps.selllicenseplates2.dto.UserLoginRequest;
import com.axelor.apps.selllicenseplates2.dto.UserRegisterRequest;
import com.axelor.apps.selllicenseplates2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserLoginRequest request) {
       return ResponseEntity.ok(userService.login(request));
    }
}
