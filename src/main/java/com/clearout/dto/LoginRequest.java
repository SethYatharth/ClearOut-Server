package com.clearout.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Email(message = "email Not in email Format") String email,
        @NotBlank(message = "password is blank space") @Size(min = 8) String password
) {
}
