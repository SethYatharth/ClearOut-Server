package com.clearout.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RepairRequestStatusDto(
        Long id,
        String description,
        String imageOfDevice,
        String city,
        boolean isRequested,
        LocalDate createdAt

) {
}
