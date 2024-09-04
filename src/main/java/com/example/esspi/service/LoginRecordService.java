package com.example.esspi.service;

import org.springframework.stereotype.Service;

import com.example.esspi.dto.LoginRecordDTO;
import com.example.esspi.model.LoginRecord;
import com.example.esspi.repository.LoginRecordRepository;

import java.util.List;

@Service
public class LoginRecordService {

    private final LoginRecordRepository loginRecordRepository;

    public LoginRecordService(LoginRecordRepository loginRecordRepository) {
        this.loginRecordRepository = loginRecordRepository;
    }

    // public void recordLogin(Long userId) { traditional spring jpa way
    // LoginRecord loginRecord = new LoginRecord();
    // loginRecord.setUserId(userId);
    // loginRecord.setLoginTime(LocalDateTime.now());
    // loginRecordRepository.save(loginRecord);
    // }

    public List<LoginRecord> findAllLoginRecords() {
        return loginRecordRepository.findAll();
    }

    public void recordLogin(Long userId) {
        loginRecordRepository.recordLogin(userId);
    }

    public List<LoginRecordDTO> getLoginRecordsWithUserDetails() {
        return loginRecordRepository.getLoginRecordsWithUserDetails();
    }
}