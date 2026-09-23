package com.cloud9.Management.task_requirement.dto.response;

public record RequirementItemResponse(
        Long id,
        String executiveName,
        String requirement,
        String analysis,
        String codingDevelopment,
        String testing,
        String demo,
        String internalChecking,
        String customerApproval,
        String deployment,
        String enhancement
) {
}
