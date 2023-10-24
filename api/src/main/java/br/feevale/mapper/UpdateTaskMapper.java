package br.feevale.mapper;

import br.feevale.controller.request.UpdateTaskRequest;
import br.feevale.controller.response.TaskResponse;
import br.feevale.domain.Task;

public class UpdateTaskMapper {

    public static Task updateTask(UpdateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadlineDate(request.getDeadlineDate());
        return task;
    }
}
