package com.clearout.dto;

import com.clearout.entity.EWasteAgent;
import com.clearout.entity.RequestStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EWasteRequestDto(
        Long id,
        Integer quantity,
        String city,
        LocalDate createdAt,
        String requestStatus,
        List<ContactDto> contactDtos
) {

}
