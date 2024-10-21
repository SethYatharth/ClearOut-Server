package com.clearout.config;

import com.clearout.userDetailService.BusinessDetailService;
import com.clearout.userDetailService.EWasteAgentDetailService;
import com.clearout.userDetailService.IndividualUserDetailService;
import com.clearout.userDetailService.RepairAgentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

    private final BusinessDetailService businessDetailService;
    private final IndividualUserDetailService individualUserDetailService;
    private final RepairAgentDetailService repairAgentDetailService;
    private final EWasteAgentDetailService eWasteAgentDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        DaoAuthenticationProvider businessAuthProvider = new DaoAuthenticationProvider();
        businessAuthProvider.setUserDetailsService(businessDetailService);
        businessAuthProvider.setPasswordEncoder(passwordEncoder());

        DaoAuthenticationProvider repairAgentAuthProvider = new DaoAuthenticationProvider();
        repairAgentAuthProvider.setUserDetailsService(repairAgentDetailService);
        repairAgentAuthProvider.setPasswordEncoder(passwordEncoder());

        DaoAuthenticationProvider individualUserAuthProvider = new DaoAuthenticationProvider();
        individualUserAuthProvider.setUserDetailsService(individualUserDetailService);
        individualUserAuthProvider.setPasswordEncoder(passwordEncoder());

        DaoAuthenticationProvider eWastAgentAuthProvider = new DaoAuthenticationProvider();
        eWastAgentAuthProvider.setUserDetailsService(eWasteAgentDetailService);
        eWastAgentAuthProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(List.of(
                businessAuthProvider,
                individualUserAuthProvider,
                eWastAgentAuthProvider,
                repairAgentAuthProvider
            )
        );

    }

}
