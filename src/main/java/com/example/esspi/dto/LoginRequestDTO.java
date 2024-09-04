package com.example.esspi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

}
