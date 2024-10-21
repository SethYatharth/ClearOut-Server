package com.clearout.controller;

import com.clearout.dto.RepairAgentDto;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.RepairAgentException;
import com.clearout.service.RepairAgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/repair-agent")
@RequiredArgsConstructor
public class RepairAgentController {

    private final RepairAgentService repairAgentService;

    @GetMapping("/all-details")
    public ResponseEntity<RepairAgentDto> allDetail(@RequestHeader("Authorization")String token) throws RepairAgentException {
        return ResponseEntity.ok(repairAgentService.getAllDetail(token));
    }

    @PutMapping("/firstname/{firstname}")
    public ResponseEntity<RepairAgentDto> editFirstname(@RequestHeader("Authorization")String token, @PathVariable String firstname) throws IndividualUserException, RepairAgentException {
        return ResponseEntity.ok(repairAgentService.editFirstname(token,firstname));
    }

    @PutMapping("/lastname/{lastname}")
    public ResponseEntity<RepairAgentDto> editLastname(@RequestHeader("Authorization")String token, @PathVariable String lastname) throws IndividualUserException, RepairAgentException {
        return ResponseEntity.ok(repairAgentService.editLastname(token, lastname));
    }

    @PutMapping("/phone-no/{phoneNo}")
    public ResponseEntity<RepairAgentDto> editPhoneNo(@RequestHeader("Authorization")String token, @PathVariable String phoneNo) throws IndividualUserException, RepairAgentException {
        return ResponseEntity.ok(repairAgentService.editPhoneNo(token, phoneNo));
    }
}
