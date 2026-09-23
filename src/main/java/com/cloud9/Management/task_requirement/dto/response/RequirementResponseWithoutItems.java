package com.cloud9.Management.task_requirement.dto.response;

import java.time.LocalDate;

public record RequirementResponseWithoutItems(
        Long id,
        String voucherNo,
        String customerName,
        LocalDate voucherDate,
        String finalStatus
) {
}
