package com.clearout.config;

import com.clearout.userDetailService.BusinessDetailService;
import com.clearout.userDetailService.EWasteAgentDetailService;
import com.clearout.userDetailService.IndividualUserDetailService;
import com.clearout.userDetailService.RepairAgentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsResolver {

    private final BusinessDetailService businessDetailService;
    private final IndividualUserDetailService individualUserDetailService;
    private final RepairAgentDetailService repairAgentDetailService;
    private  final EWasteAgentDetailService eWasteAgentDetailService;


    public UserDetails resolveUserDetails(String role,String email) {
        return switch (role) {
            case "E_WASTE_AGENT" -> eWasteAgentDetailService.loadUserByUsername(email);
            case "REPAIR_AGENT" -> repairAgentDetailService.loadUserByUsername(email);
            case "BUSINESS" -> businessDetailService.loadUserByUsername(email);
            default -> individualUserDetailService.loadUserByUsername(email);
        };
    }
}
