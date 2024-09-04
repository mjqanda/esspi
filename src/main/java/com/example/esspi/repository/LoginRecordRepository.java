package com.example.esspi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.esspi.dto.LoginRecordDTO;
import com.example.esspi.model.LoginRecord;

import java.util.List;

public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {

    @Modifying
    @Transactional
    @Query(value = "BEGIN record_login(:userId); END;", nativeQuery = true)
    void recordLogin(Long userId);

    @Query("""
            SELECT new com.example.esspi.dto.LoginRecordDTO(l.id,
                l.userId, l.loginTime, u.username, u.password, u.createdDate)
            FROM LoginRecord l JOIN User u ON l.userId = u.id """)
    List<LoginRecordDTO> getLoginRecordsWithUserDetails();

}
