package com.girlcoder.technicaltest.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserDto {
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "Username cannot be empty")
    private String password;
}
