package com.clearout.userDetailService;

import com.clearout.repository.RepairAgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepairAgentDetailService implements UserDetailsService {

    private final RepairAgentRepository repairAgentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repairAgentRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email+" Not Found"));
    }

}
