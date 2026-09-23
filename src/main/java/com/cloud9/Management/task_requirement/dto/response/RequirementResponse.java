package com.cloud9.Management.task_requirement.dto.response;

import java.time.LocalDate;
import java.util.List;

public record RequirementResponse(
        Long id,
        String voucherNo,
        String customerName,
        LocalDate voucherDate,
        String finalStatus,
        List<RequirementItemResponse> requirementItems

) {
}
