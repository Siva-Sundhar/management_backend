package com.cloud9.Management.task_requirement.dto.request;

public record RequirementItemRequest(
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
