package com.clearout.dto;

import lombok.Builder;

@Builder
public record IndividualUserDto(
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        AddressDto addressDto,
        Integer totalRepairRequest,
        Integer totalEWasteRequest,
        Integer totalProductSold,
        Integer totalBoughtProduct
) {
}
