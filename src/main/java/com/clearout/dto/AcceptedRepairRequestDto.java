package com.clearout.dto;

import java.util.List;

public record AcceptedRepairRequestDto(
    RepairRequestStatusDto repairRequestStatusDto,
    String requestStatus,
    List<ContactDto> contactDtos
) {
}
