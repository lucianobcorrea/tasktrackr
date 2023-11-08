package br.feevale.controller;

import br.feevale.controller.request.CreateTaskRequest;
import br.feevale.controller.request.UpdateTaskRequest;
import br.feevale.controller.response.RankingResponse;
import br.feevale.controller.response.TaskResponse;
import br.feevale.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

    @Autowired
    private CompleteTaskService completeTaskService;

    @Autowired
    private UserRankingService userRankingService;

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
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteTaskService.deleteTask(id);
    }

    @GetMapping
    public Page<TaskResponse> list(Pageable pageable, String filter) {
        return listTaskService.listTask(pageable, filter);
    }

    @PatchMapping("/{id}/complete")
    @ResponseStatus(NO_CONTENT)
    public void complete(@PathVariable Long id) {
        completeTaskService.completeTask(id);
    }

    @GetMapping("/ranking")
    @ResponseStatus(OK)
    public List<RankingResponse> ranking(Pageable pageable) {
        return userRankingService.ranking(pageable);
    }
}
