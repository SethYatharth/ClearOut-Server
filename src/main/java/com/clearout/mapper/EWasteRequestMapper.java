package com.clearout.mapper;

import com.clearout.dto.ContactDto;
import com.clearout.dto.EWasteRequestDto;
import com.clearout.dto.EWasteRequestStatusDto;
import com.clearout.entity.EWasteAgent;
import com.clearout.entity.EWasteRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EWasteRequestMapper {

    public EWasteRequestDto toEWasteRequestDto(EWasteRequest ewasteRequest) {

        List<ContactDto> contactDtos = new ArrayList<>();
        List<EWasteAgent> eWasteAgents = ewasteRequest.getEWasteAgents();

        if (eWasteAgents != null) {
            for (EWasteAgent agent : eWasteAgents) {
                contactDtos.add(new ContactDto(agent.getRepresentativeFirstname(),
                                agent.getRepresentativeLastname(),
                                agent.getPhoneNo(),
                                agent.getRepresentativeEmail()
                        )
                );
            }
        }


        return EWasteRequestDto.builder()
                .id(ewasteRequest.getId())
                .city(ewasteRequest.getCity())
                .createdAt(ewasteRequest.getRequestDate().toLocalDate())
                .quantity(ewasteRequest.getQuantity())
                .requestStatus(ewasteRequest.getRequestStatus().name())
                .contactDtos(contactDtos)
                .build();
    }

    public EWasteRequestStatusDto toEWasteRequestStatusDto(EWasteRequest ewasteRequest, EWasteAgent ewasteAgent) {
        return new EWasteRequestStatusDto(ewasteRequest.getId(),
                ewasteRequest.getQuantity(),
                ewasteRequest.getCity(),
                ewasteRequest.getCreatedBy(),
                ewasteRequest.getEWasteAgents().contains(ewasteAgent),
                ewasteRequest.getRequestDate().toLocalDate());
    }

}
