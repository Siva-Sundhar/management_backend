package com.cloud9.Management.task_requirement.service;

import com.cloud9.Management.task_requirement.dto.request.RequirementRequest;
import com.cloud9.Management.task_requirement.dto.response.DayBookResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementResponseWithoutItems;
import com.cloud9.Management.task_requirement.mapper.TaskMapper;
import com.cloud9.Management.task_requirement.model.TaskRequirement;
import com.cloud9.Management.task_requirement.model.RequirementItem;
import com.cloud9.Management.task_requirement.repository.TaskRepository;
import com.cloud9.Management.voucher_number_generaotr.service.VoucherNumberSequenceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final VoucherNumberSequenceService voucherNumberSequenceService;


    @Transactional
    public RequirementResponse saveTask(RequirementRequest requirementRequest) {
        TaskRequirement task = taskMapper.toTaskRequest(requirementRequest);
        String voucherNumber = voucherNumberSequenceService.saveVoucherNumber("WT");
        task.setVoucherNo(voucherNumber);

        if (requirementRequest.requirementItems() != null) {
            requirementRequest.requirementItems().forEach(itemDto -> {
                RequirementItem item = taskMapper.toTaskItem(itemDto);
                task.addTaskItem(item);
            });
        }
        taskRepository.save(task);
        return taskMapper.toTaskResponse(task);
    }

    @Transactional
    public RequirementResponse taskById(Long id) {
        TaskRequirement task = taskRepository.findById(id).orElse(null);
        return taskMapper.toTaskResponse(task);
    }

    @Transactional
    public List<RequirementResponse> getAllTasks() {
        List<TaskRequirement> taskRequirements = taskRepository.findAll();
        return taskMapper.toTaskResponseList(taskRequirements);
    }


    @Transactional
    public void updateTask(Long id, RequirementRequest request) {

        TaskRequirement task =
                taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found " + id));
        task.setCustomerName(request.customerName());
        task.setVoucherDate(request.voucherDate());
        List<RequirementItem> newItems = taskMapper.toTaskEntity(request.requirementItems());
        task.getRequirementItems().clear();

        if (newItems != null) {
            log.info("Updating requirement items for task : {}" , id);
            log.info("Items for task : {}", newItems.toString());
            newItems.forEach(task::addTaskItem);
        }

    }

    public List<RequirementResponseWithoutItems> getAllTasksByDate(LocalDate date) {
        return taskMapper.toTaskResponseWithoutItems(taskRepository.findByVoucherDate(date));
    }

    public List<DayBookResponse> dayBookFlatList(LocalDate date) {
        List<TaskRequirement> result = taskRepository.findByVoucherDate(date);

        return result.stream()
                .flatMap(taskRequirement -> {
                    if (taskRequirement.getRequirementItems() == null)
                        return Stream.empty();

                    if (taskRequirement.getRequirementItems().isEmpty())
                        return Stream.empty();
                    return taskRequirement.getRequirementItems().stream()
                            .map(item -> taskMapper.toDayBookResponse(taskRequirement, item));

                }).toList();
    }

}
