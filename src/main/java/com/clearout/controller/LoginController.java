package com.clearout.controller;

import com.clearout.config.JwtService;
import com.clearout.exception.BusinessException;
import com.clearout.exception.EWasteAgentException;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.RepairRequestException;
import com.clearout.service.AuthenticationService;
import com.clearout.dto.AuthenticationResponse;
import com.clearout.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {
    public final AuthenticationService authenticationService;
    public final JwtService jwtService;
    // login api
    @PostMapping("/individual-user")
    public ResponseEntity<AuthenticationResponse> loginIndividualUser(@Valid @RequestBody LoginRequest loginRequest) throws IndividualUserException {
        return ResponseEntity.ok(authenticationService.loginIndividualUser(loginRequest));
    }
    @PostMapping("/business")
    public ResponseEntity<AuthenticationResponse> loginBusiness(@Valid @RequestBody LoginRequest loginRequest) throws BusinessException {
        return ResponseEntity.ok(authenticationService.loginBusiness(loginRequest));
    }
    @PostMapping("/repair-agent")
    public ResponseEntity<AuthenticationResponse> loginRepairAgent(@Valid @RequestBody LoginRequest loginRequest) throws RepairRequestException {
        return ResponseEntity.ok(authenticationService.loginRepairAgent(loginRequest));
    }
    @PostMapping("/e-waste-agent")
    public ResponseEntity<AuthenticationResponse> loginEWasteAgent(@Valid @RequestBody LoginRequest loginRequest) throws EWasteAgentException {
        return ResponseEntity.ok(authenticationService.loginEWasteAgent(loginRequest));
    }

    @GetMapping("/expiry/{token}")
    public ResponseEntity<Boolean> isTokenExpired(@PathVariable String token) {
        return ResponseEntity.ok(jwtService.isTokenExpired(token));

    }

}
