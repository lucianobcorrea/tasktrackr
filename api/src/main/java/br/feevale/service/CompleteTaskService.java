package br.feevale.service;

import br.feevale.domain.Priority;
import br.feevale.domain.Status;
import br.feevale.domain.Task;
import br.feevale.repository.TaskRepository;
import br.feevale.security.domain.Usuario;
import br.feevale.security.repository.UsuarioRepository;
import br.feevale.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

import static br.feevale.domain.Priority.*;
import static br.feevale.domain.Status.COMPLETED;
import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CompleteTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository userRepository;

    public void completeTask(Long id) {

        Usuario user = usuarioAutenticadoService.get();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));

        task.setStatus(COMPLETED);
        task.setFinishDate(LocalDate.now());

        Map<Priority, Integer> points = new EnumMap<>(Priority.class);
        points.put(LOW, LOW.getPoints());
        points.put(AVERAGE, AVERAGE.getPoints());
        points.put(HIGH, HIGH.getPoints());

        if(points.containsKey(task.getPriority())) {
            user.setPoints(user.getPoints() + points.get(task.getPriority()));
        }

        userRepository.save(user);
    }
}
