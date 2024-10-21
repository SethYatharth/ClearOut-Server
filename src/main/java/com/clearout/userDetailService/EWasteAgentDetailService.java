package com.clearout.userDetailService;

import com.clearout.repository.EWasteAgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EWasteAgentDetailService implements UserDetailsService {

    public final EWasteAgentRepository eWasteAgentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return eWasteAgentRepository.findByRepresentativeEmail(email).orElseThrow(() -> new UsernameNotFoundException(email+" Not Found"));
    }
}
