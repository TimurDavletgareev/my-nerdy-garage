package com.mynerdygarage.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserDto {

    @NotNull
    @NotBlank
    @Size(min = 2, message = "size must be between 2 and 250")
    @Size(max = 250, message = "size must be between 2 and 250")
    private final String name;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 6, message = "size must be between 6 and 254")
    @Size(max = 254, message = "size must be between 6 and 254")
    private final String email;

    @NotNull
    @NotBlank
    @Size(min = 6, message = "size must be between 6 and 254")
    @Size(max = 254, message = "size must be between 6 and 254")
    private final String birthDate;

    @NotNull
    @NotBlank
    @Size(min = 6, message = "size must be between 6 and 15")
    @Size(max = 15, message = "size must be between 6 and 15")
    private final String password;

    @NotNull
    private final String passwordConfirm;
}
