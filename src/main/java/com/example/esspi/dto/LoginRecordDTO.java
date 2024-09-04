package com.example.esspi.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class LoginRecordDTO {

    private Long id;
    private Long userId;
    private LocalDateTime loginTime;
    private String username;
    private String password;
    private Date createdDate;

    public LoginRecordDTO(Long id, Long userId, LocalDateTime loginTime,
            String username, String password, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.loginTime = loginTime;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
    }

    public LoginRecordDTO() {
    }

}
