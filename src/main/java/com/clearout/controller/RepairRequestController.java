package com.clearout.controller;

import com.clearout.dto.AcceptedRepairRequestDto;
import com.clearout.dto.RepairRequestDto;
import com.clearout.dto.RepairRequestStatusDto;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.RepairAgentException;
import com.clearout.exception.RepairRequestException;
import com.clearout.service.RepairAgentService;
import com.clearout.service.RepairRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repair-request")
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;


    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PostMapping("/generate")
    public ResponseEntity<RepairRequestDto> generateRepairRequest(@RequestBody RepairRequestDto repairRequestDto,
                                                                  @RequestHeader("Authorization") String token
    ) throws IndividualUserException {

        return new ResponseEntity<>(repairRequestService.generateRepairRequest(repairRequestDto, token), HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('REPAIR_AGENT')")
    @GetMapping("/pending")
    public ResponseEntity<List<RepairRequestStatusDto>> getAllRepairRequestByCity(@RequestHeader("Authorization") String token) throws RepairAgentException {
        return ResponseEntity.ok(repairRequestService.getAllRepairRequestStatusDtoByCity(token));
    }

    @PreAuthorize("hasAuthority('REPAIR_AGENT')")
    @PutMapping("/accept/{id}")
    public ResponseEntity<RepairRequestStatusDto> acceptRepairRequest(@PathVariable Long id,@RequestHeader("Authorization") String token) throws RepairAgentException, RepairRequestException {
        return ResponseEntity.ok(repairRequestService.acceptRepairRequest(id,token));
    }


    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @GetMapping("/get-accepted")
    public ResponseEntity<List<AcceptedRepairRequestDto>> getAcceptedRequest(@RequestHeader("Authorization") String token) throws IndividualUserException {
        return ResponseEntity.ok(repairRequestService.getAcceptedRequest(token));
    }

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PutMapping("/complete/{id}")
    public ResponseEntity<List<AcceptedRepairRequestDto>> completedRequest(@PathVariable Long id,@RequestHeader("Authorization") String token) throws IndividualUserException, RepairRequestException {
        return ResponseEntity.ok(repairRequestService.completedRequest(id,token));
    }



}
