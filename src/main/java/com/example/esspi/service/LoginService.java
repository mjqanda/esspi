package com.example.esspi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.esspi.dto.LoginRequestDTO;
import com.example.esspi.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {

    private final UserService userService;
    private final LoginRecordService loginRecordService;

    public LoginService(UserService userService, LoginRecordService loginRecordService) {
        this.userService = userService;
        this.loginRecordService = loginRecordService;
    }

    public ResponseEntity<String> login(LoginRequestDTO loginRequest) {
        try {
            User user = userService.findByUsername(loginRequest.getUsername());

            if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
                loginRecordService.recordLogin(user.getId());
                return ResponseEntity.ok("Login successful");
            }

            if (user == null) {
                userService.createUser(loginRequest.getUsername(), loginRequest.getPassword());
                user = userService.findByUsername(loginRequest.getUsername());
                loginRecordService.recordLogin(user.getId());
                return ResponseEntity.ok("User created and logged in successfully");
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            log.error("Login error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
