package com.clearout.controller;

import com.clearout.dto.*;
import com.clearout.exception.BusinessException;
import com.clearout.exception.EWasteAgentException;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.RepairAgentException;
import com.clearout.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {

    public final AuthenticationService authenticationService;

    // register api
    @PostMapping("/individual-user")
    public ResponseEntity<AuthenticationResponse> registerIndividualUser(@Valid @RequestBody IndividualUserRegisterRequest registerRequest) throws IndividualUserException {
        return new ResponseEntity<>(authenticationService.registerIndividualUser(registerRequest), HttpStatus.CREATED);

    }
    @PostMapping("/business")
    public ResponseEntity<AuthenticationResponse> registerBusiness(@Valid @RequestBody BusinessRegisterRequest registerRequest) throws BusinessException {
        System.out.println(registerRequest.addressDto().city());
        return new ResponseEntity<>(authenticationService.registerBusiness(registerRequest), HttpStatus.CREATED);

    }
    @PostMapping("/repair-agent")
    public ResponseEntity<AuthenticationResponse> registerRepairAgent(@Valid @RequestBody RepairAgentRegisterRequest registerRequest) throws RepairAgentException {
        return new ResponseEntity<>(authenticationService.registerRepairAgent(registerRequest), HttpStatus.CREATED);

    }
    @PostMapping("/e-waste-agent")
    public ResponseEntity<AuthenticationResponse> registerEWasteAgent(@Valid @RequestBody EWasteAgentRegisterRequest registerRequest) throws EWasteAgentException {
        return new ResponseEntity<>(authenticationService.registerEWasteAgent(registerRequest), HttpStatus.CREATED);

    }

}
