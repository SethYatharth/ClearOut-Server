package com.clearout.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductStatusDto(
        Long id,
        String productName,
        String description,
        Double price,
        String city,
        String image,
        boolean alreadyBought,
        LocalDate createdAt
) {
}
