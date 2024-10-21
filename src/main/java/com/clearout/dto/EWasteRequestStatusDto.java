package com.clearout.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EWasteRequestStatusDto(
        Long id,
        Integer quantity,
        String city,
        String createdBy,
        boolean isRequested,
        LocalDate createdAt
) {
}
