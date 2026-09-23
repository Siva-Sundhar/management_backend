package com.cloud9.Management.task_requirement.dto.request;

import java.time.LocalDate;
import java.util.List;

public record RequirementRequest(
        
        String voucherNo,
        String customerName,
        LocalDate voucherDate,
        String finalStatus,
        List<RequirementItemRequest> requirementItems

) {
}
