package com.clearout.dto;

public record AuthenticationResponse(
        String token,
        String role
){
}
