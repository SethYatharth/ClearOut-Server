package com.clearout.dto;

import lombok.Builder;

@Builder
public record RepairAgentDto(
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        AddressDto addressDto,
        Integer totalRepairRequest
) {

}
