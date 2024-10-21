package com.clearout.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record BusinessRegisterRequest(
        @NotBlank(message = "companyName is blank") String companyName,
        @NotBlank(message = "representativeFirstname is blank") String representativeFirstname,
        @NotBlank(message = "representativeLastname is blank") String representativeLastname,
        @NotBlank(message = "phoneNo is blank") String phoneNo,
        @NotBlank(message = "representativeEmail is blank") @Email String representativeEmail,
        @NotBlank(message = "password is blank") @Size(min = 8) String password,
        AddressDto addressDto

) {

}
