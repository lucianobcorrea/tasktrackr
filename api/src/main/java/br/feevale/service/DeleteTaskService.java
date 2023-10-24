package br.feevale.service;

import br.feevale.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
