package com.clearout.dto;

import lombok.Builder;

@Builder
public record EWasteAgentDto(
        String organizationName,
        String representativeFirstname,
        String representativeLastname,
        String representativeEmail,
        String phoneNumber,
        AddressDto addressDto,
        Integer totalEWasteRequest
) {
}
