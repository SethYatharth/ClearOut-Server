package com.clearout.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductResponseDto(
         Long id,
         String productName,
         String description,
         Double price,
         String city,
         String image,
         LocalDate createdAt,
         String productStatus,
         List<ContactDto> contactDtos
) {
}
