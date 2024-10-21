package com.clearout.controller;

import com.clearout.dto.EWasteAgentDto;
import com.clearout.exception.EWasteAgentException;
import com.clearout.exception.IndividualUserException;
import com.clearout.service.EWasteAgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/e-waste-agent")
@RequiredArgsConstructor
public class EWasteAgentController {

    private final EWasteAgentService ewasteAgentService;

    @GetMapping("/all-details")
    public ResponseEntity<EWasteAgentDto> allDetail(@RequestHeader("Authorization")String token) throws EWasteAgentException {
        return ResponseEntity.ok(ewasteAgentService.getAllDetail(token));
    }

    @PutMapping("/firstname/{firstname}")
    public ResponseEntity<EWasteAgentDto> editFirstname(@RequestHeader("Authorization")String token, @PathVariable String firstname) throws IndividualUserException, EWasteAgentException {
        return ResponseEntity.ok(ewasteAgentService.editFirstname(token,firstname));
    }


    @PutMapping("/lastname/{lastname}")
    public ResponseEntity<EWasteAgentDto> editLastname(@RequestHeader("Authorization")String token, @PathVariable String lastname) throws IndividualUserException, EWasteAgentException {
        return ResponseEntity.ok(ewasteAgentService.editLastname(token, lastname));
    }

    @PutMapping("/phone-no/{phoneNo}")
    public ResponseEntity<EWasteAgentDto> editPhoneNo(@RequestHeader("Authorization")String token, @PathVariable String phoneNo) throws IndividualUserException, EWasteAgentException {
        return ResponseEntity.ok(ewasteAgentService.editPhoneNo(token, phoneNo));
    }
}
