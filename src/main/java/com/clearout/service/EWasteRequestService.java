package com.clearout.service;

import com.clearout.dto.EWasteRequestDto;
import com.clearout.dto.EWasteRequestStatusDto;
import com.clearout.entity.*;
import com.clearout.exception.BusinessException;
import com.clearout.exception.EWasteAgentException;
import com.clearout.exception.EWasteRequestException;
import com.clearout.exception.IndividualUserException;
import com.clearout.mapper.EWasteRequestMapper;
import com.clearout.repository.EWasteRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EWasteRequestService {

    private final EWasteRequestRepository ewasteRequestRepository;
    private final EWasteAgentService eWasteAgentService;
    private final BusinessService businessService;
    private final IndividualUserService individualUserService;
    private final EWasteRequestMapper ewasteRequestMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public EWasteRequestDto generateRequestByIndividualUser(Integer quantity,String token) throws IndividualUserException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        var ewasteRequest = EWasteRequest.builder()
                .city(individualUser.getAddress().getCity())
                .requestDate(LocalDateTime.now())
                .createdBy(individualUser.getRole().name())
                .individualUser(IndividualUser.builder().id(individualUser.getId()).build())
                .requestStatus(RequestStatus.PENDING)
                .quantity(quantity)
                .build();
        return ewasteRequestMapper.toEWasteRequestDto(ewasteRequestRepository.save(ewasteRequest));
    }
    public EWasteRequestDto generateRequestByBusiness(Integer quantity,String token) throws BusinessException {
        Business business = businessService.getBusiness(token);
        var ewasteRequest = EWasteRequest.builder()
                .city(business.getAddress().getCity())
                .requestDate(LocalDateTime.now())
                .createdBy( business.getRole().name())
                .business(Business.builder().id(business.getId()).build())
                .requestStatus(RequestStatus.PENDING)
                .quantity(quantity)
                .build();
        return ewasteRequestMapper.toEWasteRequestDto(ewasteRequestRepository.save(ewasteRequest));
    }

    public List<EWasteRequestStatusDto> getAllEWasteRequestByCity(String token) throws EWasteAgentException {
        EWasteAgent eWasteAgent = eWasteAgentService.getEWasteAgent(token);
        return ewasteRequestRepository.findInCompleteByCity(
                eWasteAgent
                        .getAddress()
                        .getCity())
                .stream()
                .map((eWasteRequest -> {
                    return ewasteRequestMapper.toEWasteRequestStatusDto(eWasteRequest,eWasteAgent);
                }))
                .toList();
    }


    public EWasteRequestStatusDto acceptEWasteRequest(Long id, String token) throws EWasteAgentException, EWasteRequestException {
        EWasteAgent eWasteAgent = eWasteAgentService.getEWasteAgent(token);
        EWasteRequest eWasteRequest = ewasteRequestRepository.findById(id).orElseThrow(()->new EWasteRequestException(id+" Request not found"));
        if(!eWasteRequest.getCity().equals(eWasteAgent.getAddress().getCity())){
            throw new EWasteRequestException(eWasteAgent.getRepresentativeEmail()+" you are trying to access wrong Request with id-> "+id);
        }
        eWasteRequest.setRequestStatus(RequestStatus.ACCEPTED);
        eWasteRequest.getEWasteAgents().add(EWasteAgent.builder().id(eWasteAgent.getId()).build());
        return ewasteRequestMapper.toEWasteRequestStatusDto(ewasteRequestRepository.save(eWasteRequest),eWasteAgent);
    }

    public List<EWasteRequestDto> getAcceptedRequestForIndividualUser(String token) throws IndividualUserException {
        return individualUserService
                .getIndividualUser(token)
                .getEWasteRequests()
                .stream()
                .map(ewasteRequestMapper::toEWasteRequestDto)
                .toList();
    }

    public List<EWasteRequestDto> getAcceptedRequestForBusiness(String token) throws BusinessException {
        return businessService
                .getBusiness(token)
                .getEWasteRequests()
                .stream()
                .map(ewasteRequestMapper::toEWasteRequestDto)
                .toList();
    }

    @Transactional
    public List<EWasteRequestDto> completedRequestByIndividualUser(Long id, String token) throws IndividualUserException, EWasteRequestException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        if(!individualUser.getEWasteRequests().contains(ewasteRequestRepository.findById(id).get())){
            throw new EWasteRequestException(individualUser.getEmail()+" you are trying to access wrong Request with id-> "+id);
        }
        ewasteRequestRepository.updateRequestStatus(id, RequestStatus.COMPLETED);

        entityManager.flush();
        entityManager.clear();

        return individualUserService.getIndividualUser(token).getEWasteRequests().stream().map(ewasteRequestMapper::toEWasteRequestDto).toList();

    }

    @Transactional
    public List<EWasteRequestDto> completedRequestByBusiness(Long id, String token) throws BusinessException, EWasteRequestException {
        Business business = businessService.getBusiness(token);
        if(!business.getEWasteRequests().contains(ewasteRequestRepository.findById(id).get())){
            throw new EWasteRequestException(business.getRepresentativeEmail()+" you are trying to access wrong Request with id-> "+id);
        }

        ewasteRequestRepository.updateRequestStatus(id, RequestStatus.COMPLETED);
        entityManager.flush();
        entityManager.clear();
        return businessService.getBusiness(token).getEWasteRequests().stream().map(ewasteRequestMapper::toEWasteRequestDto).toList();
    }



}
