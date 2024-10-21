package com.clearout.controller;

import com.clearout.dto.BusinessDto;
import com.clearout.exception.BusinessException;
import com.clearout.exception.IndividualUserException;
import com.clearout.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @GetMapping("/all-details")
    public ResponseEntity<BusinessDto> allDetail(@RequestHeader("Authorization")String token) throws BusinessException {
        return ResponseEntity.ok(businessService.getAllDetail(token));
    }

    @PutMapping("/firstname/{firstname}")
    public ResponseEntity<BusinessDto> editFirstname(@RequestHeader("Authorization")String token, @PathVariable String firstname) throws IndividualUserException, BusinessException {
        return ResponseEntity.ok(businessService.editFirstname(token,firstname));
    }

    @PutMapping("/lastname/{lastname}")
    public ResponseEntity<BusinessDto> editLastname(@RequestHeader("Authorization")String token, @PathVariable String lastname) throws IndividualUserException, BusinessException {
        return ResponseEntity.ok(businessService.editLastname(token, lastname));
    }

    @PutMapping("/phone-no/{phoneNo}")
    public ResponseEntity<BusinessDto> editPhoneNo(@RequestHeader("Authorization")String token, @PathVariable String phoneNo) throws IndividualUserException, BusinessException {
        return ResponseEntity.ok(businessService.editPhoneNo(token, phoneNo));
    }

}
