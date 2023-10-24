package br.feevale.service;

import br.feevale.controller.request.CreateTaskRequest;
import br.feevale.controller.response.TaskResponse;
import br.feevale.domain.Task;
import br.feevale.mapper.TaskMapper;
import br.feevale.repository.TaskRepository;
import br.feevale.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.time.LocalDateTime.*;

@Service
public class CreateTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsuarioAutenticadoService user;

    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {

        Task task = TaskMapper.toEntity(request);

        task.setUser(user.get());
        task.setCreatedAt(now());

        taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }
}
