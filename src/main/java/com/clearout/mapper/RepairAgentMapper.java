package com.clearout.mapper;

import com.clearout.dto.AddressDto;
import com.clearout.dto.RepairAgentDto;
import com.clearout.entity.RepairAgent;
import org.springframework.stereotype.Component;

@Component
public class RepairAgentMapper {

    public RepairAgentDto toRepairAgentDto(RepairAgent repairAgent) {
        return RepairAgentDto.builder()
                .firstname(repairAgent.getFirstname())
                .lastname(repairAgent.getLastname())
                .email(repairAgent.getEmail())
                .phoneNumber(repairAgent.getPhoneNo())
                .addressDto(new AddressDto(
                        repairAgent.getAddress().getHouseNo(),
                        repairAgent.getAddress().getStreet(),
                        repairAgent.getAddress().getLandmark(),
                        repairAgent.getAddress().getCity(),
                        repairAgent.getAddress().getState(),
                        repairAgent.getAddress().getCountry(),
                        repairAgent.getAddress().getZip()
                ))
                .totalRepairRequest(repairAgent.getRepairRequests()!=null?repairAgent.getRepairRequests().size():0)
                .build();
    }
}
