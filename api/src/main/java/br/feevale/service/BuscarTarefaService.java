package br.feevale.service;

import br.feevale.domain.Status;
import br.feevale.domain.Task;
import br.feevale.repository.TaskRepository;
import br.feevale.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class BuscarTarefaService {

    @Autowired
    private TaskRepository tarefaRepository;

    private static final Status statusPadrao = Status.IN_PROGRESS;

    public Task porId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tarefa não encontrada"));
    }
}
