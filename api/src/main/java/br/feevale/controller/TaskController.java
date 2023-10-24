package br.feevale.controller;

import br.feevale.controller.request.CreateTaskRequest;
import br.feevale.controller.request.UpdateTaskRequest;
import br.feevale.controller.response.TaskResponse;
import br.feevale.repository.TaskRepository;
import br.feevale.service.CreateTaskService;
import br.feevale.service.DeleteTaskService;
import br.feevale.service.ListTaskService;
import br.feevale.service.UpdateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private CreateTaskService createTaskService;

    @Autowired
    private UpdateTaskService updateTaskService;

    @Autowired
    private DeleteTaskService deleteTaskService;

    @Autowired
    private ListTaskService listTaskService;

    @PostMapping
    @ResponseStatus(CREATED)
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        return createTaskService.createTask(request);
    }

    @PatchMapping("/{id}/update")
    @ResponseStatus(OK)
    public TaskResponse update(@Valid @RequestBody UpdateTaskRequest request, @PathVariable Long id) {
        return updateTaskService.updateTask(request, id);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        deleteTaskService.deleteTask(id);
    }

    @GetMapping
    public Page<TaskResponse> list(Pageable pageable, String filter) {
        return listTaskService.listTask(pageable, filter);
    }
}
