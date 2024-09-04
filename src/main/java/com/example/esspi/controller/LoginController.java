package com.example.esspi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.esspi.dto.LoginRecordDTO;
import com.example.esspi.dto.LoginRequestDTO;
import com.example.esspi.model.User;
import com.example.esspi.service.LoginRecordService;
import com.example.esspi.service.UserService;

import java.util.List;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/esspi/api")
public class LoginController {

    private final UserService userService;
    private final LoginRecordService loginRecordService;

    public LoginController(UserService userService, LoginRecordService loginRecordService) {
        this.userService = userService;
        this.loginRecordService = loginRecordService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
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

    @GetMapping("/login-records")
    public ResponseEntity<List<LoginRecordDTO>> getLoginRecords() {
        List<LoginRecordDTO> loginRecords = loginRecordService.getLoginRecordsWithUserDetails();
        return ResponseEntity.ok(loginRecords);
    }

}