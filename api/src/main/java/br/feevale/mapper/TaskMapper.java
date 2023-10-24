package br.feevale.mapper;

import br.feevale.controller.request.CreateTaskRequest;
import br.feevale.controller.response.TaskResponse;
import br.feevale.domain.Task;

public class TaskMapper {

    public static Task toEntity(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadlineDate(request.getDeadlineDate());
        task.setPriority(request.getPriority());
        return task;
    }

    public static TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getTitle())
                .createdAt(task.getCreatedAt())
                .deadlineDate(task.getDeadlineDate())
                .finishDate(task.getFinishDate())
                .status(task.getStatus())
                .priority(task.getPriority())
                .build();
    }
}
