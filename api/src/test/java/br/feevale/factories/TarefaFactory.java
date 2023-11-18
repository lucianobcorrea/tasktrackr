package br.feevale.factories;

import br.feevale.domain.Priority;
import br.feevale.domain.Status;
import br.feevale.domain.Task;
import br.feevale.security.domain.Usuario;

import java.time.LocalDateTime;

public class TarefaFactory {

    public static Task get() {
        return Task.builder()
                .id(SimpleFactory.getRandomLong())
                .title("Tarefa teste")
                .description("Isso é uma tarefa de teste")
                .priority(Priority.AVERAGE)
                .status(Status.IN_PROGRESS)
                .user(UsuarioFactory.get())
                .build();
    }

    public static Task get(Usuario usuario) {
        Task tarefa = Task.builder()
                .id(SimpleFactory.getRandomLong())
                .title("Tarefa teste")
                .description("Isso é uma tarefa de teste")
                .priority(Priority.AVERAGE)
                .status(Status.IN_PROGRESS)
                .user(usuario)
                .build();
        usuario.getTarefas().add(tarefa);
        return tarefa;
    }

    public static Task getComDataCriacao() {
        Task tarefa = get();

        tarefa.setCreatedAt(LocalDateTime.now());

        return tarefa;
    }
}
