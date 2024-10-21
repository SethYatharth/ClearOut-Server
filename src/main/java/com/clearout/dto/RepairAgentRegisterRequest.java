package com.clearout.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RepairAgentRegisterRequest(
        @NotBlank(message = "firstname is blank") String firstname,
        @NotBlank(message = "lastname is blank") String lastname,
        @NotBlank(message = "representativeLastname is blank") String phoneNo,
        @NotBlank(message = "email is blank") @Email String email,
        @NotBlank(message = "password is blank") @Size(min = 8) String password,
        AddressDto addressDto

) {
}
