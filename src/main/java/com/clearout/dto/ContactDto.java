package com.clearout.dto;

public record ContactDto(
        String firstname,
        String lastname,
        String phoneNo,
        String email
) {
}
