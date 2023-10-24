package br.feevale.service;

import br.feevale.controller.response.TaskResponse;
import br.feevale.domain.Status;
import br.feevale.mapper.TaskMapper;
import br.feevale.repository.TaskRepository;
import br.feevale.security.domain.Usuario;
import br.feevale.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class ListTaskService {

    @Autowired
    private UsuarioAutenticadoService user;

    @Autowired
    private TaskRepository taskRepository;

    public Page<TaskResponse> listTask(Pageable pageable, String filter) {
        Usuario authenticatedUser = user.get();

        if (filter.equalsIgnoreCase("late")) {
            return taskRepository
                    .findByUserAndStatusAndLate(
                            authenticatedUser,
                            Status.IN_PROGRESS,
                            now(),
                            pageable)
                    .map(TaskMapper::toResponse);
        }

        if (filter.equalsIgnoreCase("today")) {
            return taskRepository
                    .findByUserAndStatusAndDay(
                            authenticatedUser,
                            Status.IN_PROGRESS,
                            now(),
                            pageable)
                    .map(TaskMapper::toResponse);
        }

        Status filteredStatus = buscaStatus(filter);
        return taskRepository.findByUserAndStatus(authenticatedUser, filteredStatus, pageable).map(TaskMapper::toResponse);
    }

    public Status buscaStatus(String filter) {
        return stream(Status.values())
                .filter(status -> status.toString().equalsIgnoreCase(filter))
                .collect(toList()).stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "Invalid status"));
    }
}
