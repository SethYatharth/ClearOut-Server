package com.clearout.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDto(
        @NotNull int houseNo,
        @NotBlank(message = "street is blank")String street,
        String landmark,
        @NotBlank(message = "city is blank")String city,
        @NotBlank(message = "state is blank")String state,
        @NotBlank(message = "country is blank")String country,
        @NotBlank(message = "zip is blank")String zip
) {
}
