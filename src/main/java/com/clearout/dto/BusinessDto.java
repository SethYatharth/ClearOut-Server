package com.clearout.dto;

import lombok.Builder;

@Builder
public record BusinessDto(
        String companyName,
        String representativeFirstname,
        String representativeLastname,
        String representativeEmail,
        String phoneNumber,
        AddressDto addressDto,
        Integer totalEWasteRequest
) {
}
