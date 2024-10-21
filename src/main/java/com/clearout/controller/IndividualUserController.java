package com.clearout.controller;

import com.clearout.dto.IndividualUserDto;
import com.clearout.exception.IndividualUserException;
import com.clearout.service.IndividualUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/individual-user")
@RequiredArgsConstructor
public class IndividualUserController {

    private final IndividualUserService individualUserService;

    @GetMapping("/all-details")
    public ResponseEntity<IndividualUserDto> allDetails(@RequestHeader("Authorization")String token) throws IndividualUserException {
        return ResponseEntity.ok(individualUserService.getAllDetails(token));
    }

    @PutMapping("/firstname/{firstname}")
    public ResponseEntity<IndividualUserDto> editFirstname(@RequestHeader("Authorization")String token, @PathVariable String firstname) throws IndividualUserException {
        return ResponseEntity.ok(individualUserService.editFirstname(token,firstname));
    }

    @PutMapping("/lastname/{lastname}")
    public ResponseEntity<IndividualUserDto> editLastname(@RequestHeader("Authorization")String token, @PathVariable String lastname) throws IndividualUserException {
        return ResponseEntity.ok(individualUserService.editLastname(token, lastname));
    }

    @PutMapping("/phone-no/{phoneNo}")
    public ResponseEntity<IndividualUserDto> editPhoneNo(@RequestHeader("Authorization")String token, @PathVariable String phoneNo) throws IndividualUserException {
        return ResponseEntity.ok(individualUserService.editPhoneNo(token, phoneNo));
    }

}
