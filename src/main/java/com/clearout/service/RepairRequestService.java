package com.clearout.service;

import com.clearout.dto.AcceptedRepairRequestDto;
import com.clearout.dto.RepairRequestStatusDto;
import com.clearout.entity.IndividualUser;
import com.clearout.config.JwtService;
import com.clearout.entity.RepairAgent;
import com.clearout.entity.RequestStatus;
import com.clearout.dto.RepairRequestDto;
import com.clearout.entity.RepairRequest;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.RepairAgentException;
import com.clearout.exception.RepairRequestException;
import com.clearout.mapper.RepairRequestMapper;
import com.clearout.repository.RepairRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairRequestService {

    private final IndividualUserService individualUserService;
    private final RepairRequestMapper repairRequestMapper;
    private final RepairRequestRepository repairRequestRepository;
    private final RepairAgentService repairAgentService;

    @PersistenceContext
    private EntityManager entityManager;

    public RepairRequestDto generateRepairRequest(RepairRequestDto dto,String token) throws IndividualUserException {
        IndividualUser reqUser = individualUserService.getIndividualUser(token);
        RepairRequest repairRequest = repairRequestMapper.toRepairRequest(dto);
        repairRequest.setCity(reqUser.getAddress().getCity());
        repairRequest.setIndividualUser(IndividualUser.builder().id(reqUser.getId()).build());
        return repairRequestMapper.toRepairRequestDto(repairRequestRepository.save(repairRequest));
    }

    public List<RepairRequestStatusDto> getAllRepairRequestStatusDtoByCity(String token) throws RepairAgentException {
        RepairAgent repairAgent = repairAgentService.getRepairAgent(token);
        return repairRequestRepository.findInCompleteByCity(
                repairAgent.getAddress().getCity()
                )
                .stream().
                map((repairRequest -> {
                            return repairRequestMapper.toRepairRequestStatusDto(repairRequest,repairAgent );
                        })
                ).toList();
    }

    public RepairRequestStatusDto acceptRepairRequest(Long id, String token) throws RepairAgentException, RepairRequestException {
        RepairRequest repairRequest = repairRequestRepository.findById(id).orElseThrow(
                () -> new RepairRequestException(id + " not found")
        );
        RepairAgent repairAgent = repairAgentService.getRepairAgent(token);

        if(!repairAgent.getAddress().getCity().equals(repairRequest.getCity()))
            throw new RepairRequestException(repairAgent.getEmail()+" you are trying to access wrong Request with id-> "+id);

        repairRequest.setRequestStatus(RequestStatus.ACCEPTED);
        repairRequest.getRepairAgents().add(RepairAgent.builder().id(repairAgent.getId()).build());
        return repairRequestMapper.toRepairRequestStatusDto(repairRequestRepository.save(repairRequest),repairAgent);
    }


    public List<AcceptedRepairRequestDto> getAcceptedRequest(String token) throws IndividualUserException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);

        return individualUser.getRepairRequests().stream().map(repairRequestMapper::toAcceptedRepairRequestDto).toList();
    }

    @Transactional
    public List<AcceptedRepairRequestDto> completedRequest(Long id, String token) throws IndividualUserException, RepairRequestException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        if(!individualUser.getRepairRequests().contains(repairRequestRepository.findById(id).get())){
            throw new RepairRequestException(individualUser.getEmail()+" you are trying to access wrong Request with id-> "+id);
        }
        repairRequestRepository.updateRequestStatus(id, RequestStatus.COMPLETED);

        entityManager.flush();
        entityManager.clear();

        return individualUserService.getIndividualUser(token).getRepairRequests().stream().map(repairRequestMapper::toAcceptedRepairRequestDto).toList();
    }
}
