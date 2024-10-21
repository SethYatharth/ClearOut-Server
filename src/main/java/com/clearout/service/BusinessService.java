package com.clearout.service;

import com.clearout.config.JwtService;
import com.clearout.dto.BusinessDto;
import com.clearout.entity.Business;
import com.clearout.exception.BusinessException;
import com.clearout.mapper.BusinessMapper;
import com.clearout.repository.BusinessRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final JwtService jwtService;
    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public Business getBusiness(String token) throws BusinessException {
        token = token.substring(7);
        String email = jwtService.extractEmail(token);
        return businessRepository.findByRepresentativeEmail(email).orElseThrow(()->new BusinessException(email+" not found"));
    }

    public BusinessDto getAllDetail(String token) throws BusinessException {
        Business business = getBusiness(token);
        return businessMapper.toBusinessDto(business);
    }

    @Transactional
    public BusinessDto editFirstname(String token, String firstname) throws BusinessException {
        Business business = getBusiness(token);
        businessRepository.updateRepresentativeFirstname(firstname, business.getId());
        entityManager.flush();
        entityManager.clear();
        return businessMapper.toBusinessDto(businessRepository.findById(business.getId()).orElseThrow(()->new BusinessException(firstname+" not found")));
    }

    @Transactional
    public BusinessDto editLastname(String token, String lastname) throws BusinessException {
        Business business = getBusiness(token);
        businessRepository.updateRepresentativeLastname(lastname, business.getId());
        entityManager.flush();
        entityManager.clear();
        return businessMapper.toBusinessDto(businessRepository.findById(business.getId()).orElseThrow(()->new BusinessException(lastname+" not found")));
    }

    @Transactional
    public BusinessDto editPhoneNo(String token, String phoneNo) throws BusinessException {
        Business business = getBusiness(token);
        businessRepository.updatePhoneNo(phoneNo, business.getId());
        entityManager.flush();
        entityManager.clear();
        return businessMapper.toBusinessDto(businessRepository.findById(business.getId()).orElseThrow(()->new BusinessException(phoneNo+" not found")));
    }
}
