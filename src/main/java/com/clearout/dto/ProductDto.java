package com.clearout.dto;

import java.time.LocalDateTime;

public record ProductDto(

        String productName,
        String description,
        Double price,
        String image

) {
}
