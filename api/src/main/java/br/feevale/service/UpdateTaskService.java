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

import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UpdateTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BuscarTarefaService buscarTarefaService;

    @Transactional
    public TaskResponse updateTask(UpdateTaskRequest request, Long id) {
        Task task = buscarTarefaService.porId(id);

        task.setTitle(request.getTitle().isBlank() ? task.getTitle() : request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadlineDate(Objects.isNull(request.getDeadlineDate()) ? task.getDeadlineDate() : request.getDeadlineDate());
        task.setPriority(Objects.isNull(request.getPriority()) ? task.getPriority() : request.getPriority());

        taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }
}
