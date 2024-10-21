package com.clearout.mapper;

import com.clearout.dto.AcceptedRepairRequestDto;
import com.clearout.dto.ContactDto;
import com.clearout.dto.RepairRequestDto;
import com.clearout.dto.RepairRequestStatusDto;
import com.clearout.entity.RepairAgent;
import com.clearout.entity.RepairRequest;
import com.clearout.entity.RequestStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepairRequestMapper {

    public RepairRequest toRepairRequest(RepairRequestDto repairRequestDto) {
        return RepairRequest.builder()
                .description(repairRequestDto.description())
                .imageOfDevice(repairRequestDto.imageOfDevice())
                .generatedAt(LocalDateTime.now())
                .requestStatus(RequestStatus.PENDING)
                .build();
    }

    public RepairRequestDto toRepairRequestDto(RepairRequest save) {
        return new RepairRequestDto(save.getDescription(), save.getImageOfDevice());
    }

    public RepairRequestStatusDto toRepairRequestStatusDto(RepairRequest repairRequest,RepairAgent repairAgent) {
        return new RepairRequestStatusDto(
                repairRequest.getId(),
                repairRequest.getDescription(),
                repairRequest.getImageOfDevice(),
                repairRequest.getCity(),
                repairRequest.getRepairAgents().contains(repairAgent),
                repairRequest.getGeneratedAt().toLocalDate());
    }

    public AcceptedRepairRequestDto toAcceptedRepairRequestDto(RepairRequest repairRequest) {

        List<ContactDto> list = new ArrayList<>();
        List<RepairAgent> repairAgents = repairRequest.getRepairAgents();

        if (repairAgents != null) {
            for (RepairAgent agent : repairAgents) {
                list.add(new ContactDto(agent.getFirstname(), agent.getLastname(), agent.getPhoneNo(), agent.getEmail()));
            }
        }

        return new AcceptedRepairRequestDto(toRepairRequestStatusDto(repairRequest,new RepairAgent()), repairRequest.getRequestStatus().name(), list);

    }

}
