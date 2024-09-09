package com.example.esspi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.esspi.dto.LoginRecordDTO;
import com.example.esspi.dto.LoginRequestDTO;
import com.example.esspi.service.LoginRecordService;
import com.example.esspi.service.LoginService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/esspi/api")
public class LoginController {

    private final LoginService loginService;
    private final LoginRecordService loginRecordService;

    public LoginController(LoginService loginService, LoginRecordService loginRecordService) {
        this.loginService = loginService;
        this.loginRecordService = loginRecordService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return loginService.login(loginRequest);
    }

    @GetMapping("/login-records")
    public ResponseEntity<List<LoginRecordDTO>> getLoginRecords() {
        List<LoginRecordDTO> loginRecords = loginRecordService.getLoginRecordsWithUserDetails();
        return ResponseEntity.ok(loginRecords);
    }

}