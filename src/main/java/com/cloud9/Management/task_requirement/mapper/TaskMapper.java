package com.cloud9.Management.task_requirement.mapper;

import com.cloud9.Management.task_requirement.dto.request.RequirementItemRequest;
import com.cloud9.Management.task_requirement.dto.request.RequirementRequest;
import com.cloud9.Management.task_requirement.dto.response.DayBookResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementItemResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementResponseWithoutItems;
import com.cloud9.Management.task_requirement.model.TaskRequirement;
import com.cloud9.Management.task_requirement.model.RequirementItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // --> Request to Entity <--
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requirementItems", ignore = true)
    TaskRequirement toTaskRequest(RequirementRequest requirementRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "taskRequirement", ignore = true)
    RequirementItem toTaskItem(RequirementItemRequest requirementItemRequest);

    List<RequirementItem> toTaskEntity(List<RequirementItemRequest> taskItemsRequest);

    // --> Entity to Response <--
    RequirementResponse toTaskResponse(TaskRequirement taskRequirement);

    RequirementItemResponse toTaskItemResponse(RequirementItem requirementItem);

    List<RequirementItemResponse> toTaskItemResponse(List<RequirementItem> requirementItems);

    List<RequirementResponse> toTaskResponseList(List<TaskRequirement> taskRequirements);

    List<RequirementResponseWithoutItems> toTaskResponseWithoutItems(List<TaskRequirement> taskRequirements);

    default DayBookResponse toDayBookResponse(TaskRequirement requirement, RequirementItem item) {
        return new DayBookResponse(
                requirement.getId(),
                requirement.getVoucherNo(),
                requirement.getCustomerName(),
                requirement.getVoucherDate(),
                item.getExecutiveName(),
                item.getRequirement(),
                item.getDeployment()
        );
    }
}
