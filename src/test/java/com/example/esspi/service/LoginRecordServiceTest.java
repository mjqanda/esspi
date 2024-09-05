package com.example.esspi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.esspi.dto.LoginRecordDTO;
import com.example.esspi.model.LoginRecord;
import com.example.esspi.repository.LoginRecordRepository;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Transactional
@Rollback
class LoginRecordServiceTest {

    @Mock
    private LoginRecordRepository loginRecordRepository;

    @InjectMocks
    private LoginRecordService loginRecordService;

    @Test
    void testFindAllLoginRecords() {
        // Given
        LoginRecord mockLoginRecord = new LoginRecord();
        // Set mock properties as needed
        when(loginRecordRepository.findAll()).thenReturn(Collections.singletonList(mockLoginRecord));

        // When
        List<LoginRecord> result = loginRecordService.findAllLoginRecords();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(mockLoginRecord, result.get(0));
    }

    @Test
    void testRecordLogin() {
        // Given
        Long userId = 1L;

        // When
        loginRecordService.recordLogin(userId);

        // Then
        verify(loginRecordRepository, times(1)).recordLogin(userId);
    }

    @Test
    void testGetLoginRecordsWithUserDetails() {
        // Given
        LoginRecordDTO mockLoginRecordDTO = new LoginRecordDTO();
        // Set mock properties as needed
        when(loginRecordRepository.getLoginRecordsWithUserDetails()).thenReturn(Collections.singletonList(mockLoginRecordDTO));

        // When
        List<LoginRecordDTO> result = loginRecordService.getLoginRecordsWithUserDetails();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(mockLoginRecordDTO, result.get(0));
    }
}
