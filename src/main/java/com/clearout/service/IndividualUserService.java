package com.clearout.service;

import com.clearout.config.JwtService;
import com.clearout.dto.IndividualUserDto;
import com.clearout.entity.IndividualUser;
import com.clearout.exception.IndividualUserException;
import com.clearout.mapper.IndividualUserMapper;
import com.clearout.repository.IndividualUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualUserService {

    private final IndividualUserRepository individualUserRepository;
    private final JwtService jwtService;
    private final IndividualUserMapper individualUserMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public IndividualUser getIndividualUser(String jwtToken) throws IndividualUserException {
        jwtToken = jwtToken.substring(7);
        String email = jwtService.extractEmail(jwtToken);
        return individualUserRepository.findByEmail(email).orElseThrow(()->new IndividualUserException(email+" not found"));
    }

    public IndividualUserDto getAllDetails(String token) throws IndividualUserException {
        IndividualUser individualUser = getIndividualUser(token);
        return individualUserMapper.toIndividualUser(individualUser);
    }

    @Transactional
    public IndividualUserDto editFirstname(String token, String firstname) throws IndividualUserException {
        IndividualUser individualUser = getIndividualUser(token);
        individualUserRepository.updateIndividualFirstname(firstname, individualUser.getId());
        entityManager.flush();
        entityManager.clear();
        return individualUserMapper.toIndividualUser(individualUserRepository.findById(individualUser.getId()).orElseThrow(() -> new IndividualUserException(individualUser.getId() + " not found")));
    }

    @Transactional
    public IndividualUserDto editLastname(String token, String lastname) throws IndividualUserException {
        IndividualUser individualUser = getIndividualUser(token);
        individualUserRepository.updateIndividualLastname(lastname, individualUser.getId());
        entityManager.flush();
        entityManager.clear();
        return individualUserMapper.toIndividualUser(individualUserRepository.findById(individualUser.getId()).orElseThrow(()->new IndividualUserException(individualUser.getId()+" not found")));

    }

    @Transactional
    public IndividualUserDto editPhoneNo(String token, String phoneNo) throws IndividualUserException {
        IndividualUser individualUser = getIndividualUser(token);
        individualUserRepository.updateIndividualPhoneNo(phoneNo, individualUser.getId());
        entityManager.flush();
        entityManager.clear();
        return individualUserMapper.toIndividualUser(individualUserRepository.findById(individualUser.getId()).orElseThrow(()->new IndividualUserException(individualUser.getId()+" not found")));
    }
}
