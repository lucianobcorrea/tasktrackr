package br.feevale.service;

import br.feevale.controller.request.UpdateTaskRequest;
import br.feevale.controller.response.TaskResponse;
import br.feevale.domain.Task;
import br.feevale.mapper.TaskMapper;
import br.feevale.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UpdateTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public TaskResponse updateTask(UpdateTaskRequest request, Long id) {
        Task task = byId(id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadlineDate(request.getDeadlineDate());
        task.setPriority(request.getPriority());

        taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }

    public Task byId(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task not found"));
    }
}
