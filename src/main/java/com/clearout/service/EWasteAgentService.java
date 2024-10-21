package com.clearout.service;

import com.clearout.config.JwtService;
import com.clearout.dto.EWasteAgentDto;
import com.clearout.entity.EWasteAgent;
import com.clearout.exception.EWasteAgentException;
import com.clearout.mapper.EWasteAgentMapper;
import com.clearout.repository.EWasteAgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EWasteAgentService {
    private final JwtService jwtService;
    private final EWasteAgentRepository ewasteAgentRepository;
    private final EWasteAgentMapper ewasteAgentMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public EWasteAgent getEWasteAgent(String token) throws EWasteAgentException {
        token = token.substring(7);
        String email = jwtService.extractEmail(token);
        return ewasteAgentRepository.findByRepresentativeEmail(email).orElseThrow(()->new EWasteAgentException(email+" not found"));
    }

    public EWasteAgentDto getAllDetail(String token) throws EWasteAgentException {
        EWasteAgent eWasteAgent = getEWasteAgent(token);
        return ewasteAgentMapper.toEWasteAgentDto(eWasteAgent);
    }

    @Transactional
    public EWasteAgentDto editFirstname(String token, String firstname) throws EWasteAgentException {
        EWasteAgent eWasteAgent = getEWasteAgent(token);
        ewasteAgentRepository.updateRepresentativeFirstname(firstname, eWasteAgent.getId());
        entityManager.flush();
        entityManager.clear();
        return ewasteAgentMapper.toEWasteAgentDto(ewasteAgentRepository.findById(eWasteAgent.getId()).orElseThrow(()->new EWasteAgentException(firstname+" not found")));
    }

    @Transactional
    public EWasteAgentDto editLastname(String token, String lastname) throws EWasteAgentException {
        EWasteAgent eWasteAgent = getEWasteAgent(token);
        ewasteAgentRepository.updateRepresentativeLastname(lastname, eWasteAgent.getId());
        entityManager.flush();
        entityManager.clear();
        return ewasteAgentMapper.toEWasteAgentDto(ewasteAgentRepository.findById(eWasteAgent.getId()).orElseThrow(()->new EWasteAgentException(lastname+" not found")));
    }

    @Transactional
    public EWasteAgentDto editPhoneNo(String token, String phoneNo) throws EWasteAgentException {
        EWasteAgent eWasteAgent = getEWasteAgent(token);
        ewasteAgentRepository.updatePhoneNo(phoneNo, eWasteAgent.getId());
        entityManager.flush();
        entityManager.clear();
        return ewasteAgentMapper.toEWasteAgentDto(ewasteAgentRepository.findById(eWasteAgent.getId()).orElseThrow(()->new EWasteAgentException(phoneNo+" not found")));
    }
}
