package com.clearout.userDetailService;

import com.clearout.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessDetailService implements UserDetailsService {

    private final BusinessRepository businessRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return businessRepository.findByRepresentativeEmail(email).orElseThrow(() -> new UsernameNotFoundException(email+" Not Found"));
    }
}
