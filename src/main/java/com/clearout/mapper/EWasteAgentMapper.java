package com.clearout.mapper;

import com.clearout.dto.AddressDto;
import com.clearout.dto.EWasteAgentDto;
import com.clearout.entity.EWasteAgent;
import org.springframework.stereotype.Component;

@Component
public class EWasteAgentMapper {
    public EWasteAgentDto toEWasteAgentDto(EWasteAgent eWasteAgent) {
        return EWasteAgentDto.builder()
                .organizationName(eWasteAgent.getOrganizationName())
                .representativeFirstname(eWasteAgent.getRepresentativeFirstname())
                .representativeLastname(eWasteAgent.getRepresentativeLastname())
                .representativeEmail(eWasteAgent.getRepresentativeEmail())
                .phoneNumber(eWasteAgent.getPhoneNo())
                .addressDto(new AddressDto(
                        eWasteAgent.getAddress().getHouseNo(),
                        eWasteAgent.getAddress().getStreet(),
                        eWasteAgent.getAddress().getLandmark(),
                        eWasteAgent.getAddress().getCity(),
                        eWasteAgent.getAddress().getState(),
                        eWasteAgent.getAddress().getCountry(),
                        eWasteAgent.getAddress().getZip()
                ))
                .totalEWasteRequest(eWasteAgent.getEWasteRequests()!=null?eWasteAgent.getEWasteRequests().size():0)
                .build();
    }
}
