package com.clearout.userDetailService;

import com.clearout.repository.IndividualUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualUserDetailService implements UserDetailsService {

    private final IndividualUserRepository individualUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return individualUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email+" Not Found"));
    }

}
