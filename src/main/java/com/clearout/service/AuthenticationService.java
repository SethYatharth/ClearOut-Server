package com.clearout.service;

import com.clearout.config.JwtService;
import com.clearout.exception.*;
import com.clearout.repository.BusinessRepository;
import com.clearout.repository.EWasteAgentRepository;
import com.clearout.repository.IndividualUserRepository;
import com.clearout.repository.RepairAgentRepository;
import com.clearout.dto.*;
import com.clearout.entity.*;
import com.clearout.util.embedded.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final BusinessRepository businessRepository;
    private final IndividualUserRepository individualUserRepository;
    private final RepairAgentRepository repairAgentRepository;
    private final EWasteAgentRepository eWasteAgentRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registerIndividualUser(IndividualUserRegisterRequest request) throws IndividualUserException {

        var user = individualUserRepository.findByEmail(request.email());
        if(user.isPresent()){
            throw new IndividualUserException(request.email()+" already registered");
        }


        var individualUser = IndividualUser.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .phoneNo(request.phoneNo())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.INDIVIDUAL_USER)
                .address(Address.builder()
                        .houseNo(request.addressDto().houseNo())
                        .landmark(request.addressDto().landmark())
                        .street(request.addressDto().street())
                        .city(request.addressDto().city())
                        .state(request.addressDto().state())
                        .country(request.addressDto().country())
                        .zip(request.addressDto().zip())
                        .build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        IndividualUser save = individualUserRepository.save(individualUser);
        var token = jwtService.generateToken(save);
        return new AuthenticationResponse(token,save.getRole().name());
    }
    public AuthenticationResponse registerBusiness( BusinessRegisterRequest request) throws BusinessException {

        var user = businessRepository.findByRepresentativeEmail(request.representativeEmail());
        if(user.isPresent()){
            throw new BusinessException(request.representativeEmail()+" already registered");
        }

        var business = Business.builder()
                .companyName(request.companyName())
                .representativeFirstname(request.representativeFirstname())
                .representativeLastname(request.representativeLastname())
                .phoneNo(request.phoneNo())
                .representativeEmail(request.representativeEmail())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.BUSINESS)
                .address(Address.builder()
                        .houseNo(request.addressDto().houseNo())
                        .landmark(request.addressDto().landmark())
                        .street(request.addressDto().street())
                        .city(request.addressDto().city())
                        .state(request.addressDto().state())
                        .country(request.addressDto().country())
                        .zip(request.addressDto().zip())
                        .build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Business save = businessRepository.save(business);
        var token = jwtService.generateToken(save);
        return new AuthenticationResponse(token,save.getRole().name());
    }
    public AuthenticationResponse registerRepairAgent(RepairAgentRegisterRequest request) throws RepairAgentException {

        var user = repairAgentRepository.findByEmail(request.email());
        if(user.isPresent()){
            throw new RepairAgentException(request.email()+" already registered");
        }

        var repairAgent = RepairAgent.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .phoneNo(request.phoneNo())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.REPAIR_AGENT)
                .address(Address.builder()
                        .houseNo(request.addressDto().houseNo())
                        .landmark(request.addressDto().landmark())
                        .street(request.addressDto().street())
                        .city(request.addressDto().city())
                        .state(request.addressDto().state())
                        .country(request.addressDto().country())
                        .zip(request.addressDto().zip())
                        .build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        RepairAgent save = repairAgentRepository.save(repairAgent);
        var token = jwtService.generateToken(save);
        return new AuthenticationResponse(token,save.getRole().name());
    }
    public AuthenticationResponse registerEWasteAgent( EWasteAgentRegisterRequest request) throws EWasteAgentException {

        var user = eWasteAgentRepository.findByRepresentativeEmail(request.representativeEmail());
        if(user.isPresent()){
            throw new EWasteAgentException(request.representativeEmail()+" already registered");
        }

        var eWasteAgent = EWasteAgent.builder()
                .organizationName(request.organizationName())
                .representativeFirstname(request.representativeFirstname())
                .representativeLastname(request.representativeLastname())
                .phoneNo(request.phoneNo())
                .representativeEmail(request.representativeEmail())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.E_WASTE_AGENT)
                .address(Address.builder()
                        .houseNo(request.addressDto().houseNo())
                        .landmark(request.addressDto().landmark())
                        .street(request.addressDto().street())
                        .city(request.addressDto().city())
                        .state(request.addressDto().state())
                        .country(request.addressDto().country())
                        .zip(request.addressDto().zip())
                        .build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        EWasteAgent save = eWasteAgentRepository.save(eWasteAgent);
        var token = jwtService.generateToken(save);
        return new AuthenticationResponse(token,save.getRole().name());
    }


    // login api
    public AuthenticationResponse loginIndividualUser( LoginRequest request) throws IndividualUserException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var individualUser = individualUserRepository.findByEmail(request.email()).orElseThrow(()->new IndividualUserException(request.email()+" not found"));
        var token = jwtService.generateToken(individualUser);
        return new AuthenticationResponse(token,individualUser.getRole().name());
    }
    public AuthenticationResponse loginBusiness( LoginRequest request) throws BusinessException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var business = businessRepository.findByRepresentativeEmail(request.email()).orElseThrow(()->new BusinessException(request.email()+" not found"));
        var token = jwtService.generateToken(business);
        return new AuthenticationResponse(token,business.getRole().name());
    }
    public AuthenticationResponse loginRepairAgent( LoginRequest request) throws RepairRequestException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email()
                        ,request.password()
                )
        );
        var repairAgent = repairAgentRepository.findByEmail(request.email()).orElseThrow(()->new RepairRequestException(request.email()+" not found"));
        var token = jwtService.generateToken(repairAgent);
        return new AuthenticationResponse(token,repairAgent.getRole().name());
    }
    public AuthenticationResponse loginEWasteAgent(LoginRequest request) throws EWasteAgentException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var eWasteAgent = eWasteAgentRepository.findByRepresentativeEmail(request.email()).orElseThrow(()->new EWasteAgentException(request.email()+" not found"));
        var token = jwtService.generateToken(eWasteAgent);
        return new AuthenticationResponse(token,eWasteAgent.getRole().name());
    }



}
