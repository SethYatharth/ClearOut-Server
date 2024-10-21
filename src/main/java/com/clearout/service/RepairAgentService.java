package com.clearout.service;

import com.clearout.config.JwtService;
import com.clearout.dto.RepairAgentDto;
import com.clearout.entity.RepairAgent;
import com.clearout.exception.RepairAgentException;
import com.clearout.mapper.RepairAgentMapper;
import com.clearout.repository.RepairAgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepairAgentService {
    private final RepairAgentRepository repairAgentRepository;
    private final JwtService jwtService;
    private final RepairAgentMapper repairAgentMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public RepairAgent getRepairAgent(String token) throws RepairAgentException {
        token = token.substring(7);
        String email = jwtService.extractEmail(token);
        return repairAgentRepository.findByEmail(email).orElseThrow(()->new RepairAgentException(email+" not found"));
    }

    public RepairAgentDto getAllDetail(String token) throws RepairAgentException {
        RepairAgent repairAgent = getRepairAgent(token);
        return repairAgentMapper.toRepairAgentDto(repairAgent);
    }

    @Transactional
    public RepairAgentDto editFirstname(String token, String firstname) throws RepairAgentException {
        RepairAgent repairAgent = getRepairAgent(token);
        repairAgentRepository.updateFirstname(firstname, repairAgent.getId());
        entityManager.flush();
        entityManager.clear();
        return repairAgentMapper.toRepairAgentDto(repairAgentRepository.findById(repairAgent.getId()).orElseThrow(()->new RepairAgentException(firstname+" not found")));
    }

    @Transactional
    public RepairAgentDto editLastname(String token, String lastname) throws RepairAgentException {
        RepairAgent repairAgent = getRepairAgent(token);
        repairAgentRepository.updateLastname(lastname, repairAgent.getId());
        entityManager.flush();
        entityManager.clear();
        return repairAgentMapper.toRepairAgentDto(repairAgentRepository.findById(repairAgent.getId()).orElseThrow(()->new RepairAgentException(lastname+" not found")));
    }

    @Transactional
    public RepairAgentDto editPhoneNo(String token, String phoneNo) throws RepairAgentException {
        RepairAgent repairAgent = getRepairAgent(token);
        repairAgentRepository.updatePhoneNo(phoneNo, repairAgent.getId());
        entityManager.flush();
        entityManager.clear();
        return repairAgentMapper.toRepairAgentDto(repairAgentRepository.findById(repairAgent.getId()).orElseThrow(()->new RepairAgentException(phoneNo+" not found")));
    }
}
