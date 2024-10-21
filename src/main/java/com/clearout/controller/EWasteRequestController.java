package com.clearout.controller;

import com.clearout.dto.EWasteRequestDto;
import com.clearout.dto.EWasteRequestStatusDto;
import com.clearout.exception.BusinessException;
import com.clearout.exception.EWasteAgentException;
import com.clearout.exception.EWasteRequestException;
import com.clearout.exception.IndividualUserException;
import com.clearout.service.EWasteRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/e-waste-request")
@RequiredArgsConstructor
public class EWasteRequestController {

    private final EWasteRequestService ewasteRequestService;

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PostMapping("/individual-user/generate/{quantity}")
    public ResponseEntity<EWasteRequestDto> generateRequestByIndividualUser(@PathVariable Integer quantity,
                                                            @RequestHeader("Authorization") String token) throws IndividualUserException {
        return new ResponseEntity<>(ewasteRequestService.generateRequestByIndividualUser(quantity,token), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('BUSINESS')")
    @PostMapping("/business/generate/{quantity}")
    public ResponseEntity<EWasteRequestDto> generateRequestByBusiness(@PathVariable Integer quantity,
                                                            @RequestHeader("Authorization") String token) throws BusinessException {
        return new ResponseEntity<>(ewasteRequestService.generateRequestByBusiness(quantity,token), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('E_WASTE_AGENT')")
    @GetMapping("/pending")
    public ResponseEntity<List<EWasteRequestStatusDto>> getAllEWasteRequestByCity(@RequestHeader("Authorization") String token) throws EWasteAgentException {
        return ResponseEntity.ok(ewasteRequestService.getAllEWasteRequestByCity(token));
    }

    @PreAuthorize("hasAuthority('E_WASTE_AGENT')")
    @PutMapping("/accept/{id}")
    public ResponseEntity<EWasteRequestStatusDto> acceptEWasteRequest(@PathVariable Long id,@RequestHeader("Authorization") String token) throws EWasteRequestException, EWasteAgentException {
        return ResponseEntity.ok(ewasteRequestService.acceptEWasteRequest(id,token));
    }



    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @GetMapping("/individual-user/get-accepted")
    public ResponseEntity<List<EWasteRequestDto>> getAcceptedRequestForIndividualUser(@RequestHeader("Authorization") String token) throws IndividualUserException {
        return ResponseEntity.ok(ewasteRequestService.getAcceptedRequestForIndividualUser(token));
    }

    @PreAuthorize("hasAuthority('BUSINESS')")
    @GetMapping("/business/get-accepted")
    public ResponseEntity<List<EWasteRequestDto>> getAcceptedRequestForBusiness(@RequestHeader("Authorization") String token) throws BusinessException {
        return ResponseEntity.ok(ewasteRequestService.getAcceptedRequestForBusiness(token));
    }

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PutMapping("/individual-user/complete/{id}")
    public ResponseEntity<List<EWasteRequestDto>> completedRequestByIndividualUser(@PathVariable Long id,@RequestHeader("Authorization") String token) throws IndividualUserException, EWasteRequestException {
        return ResponseEntity.ok(ewasteRequestService.completedRequestByIndividualUser(id,token));
    }

    @PreAuthorize("hasAuthority('BUSINESS')")
    @PutMapping("/business/complete/{id}")
    public ResponseEntity<List<EWasteRequestDto>> completedRequestByBusiness(@PathVariable Long id,@RequestHeader("Authorization") String token) throws BusinessException, EWasteRequestException {
        return ResponseEntity.ok(ewasteRequestService.completedRequestByBusiness(id,token));
    }

}
