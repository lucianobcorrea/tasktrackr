package br.feevale.service;

import br.feevale.controller.request.UpdateTaskRequest;
import br.feevale.domain.Priority;
import br.feevale.domain.Task;
import br.feevale.factories.TarefaFactory;
import br.feevale.factories.UsuarioFactory;
import br.feevale.repository.TaskRepository;
import br.feevale.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtualizarTarefaServiceTest {

    @InjectMocks
    private UpdateTaskService tested;

    @Mock
    private BuscarTarefaService buscarTarefaService;

    @Mock
    private TaskRepository tarefaRepository;

    @Captor
    private ArgumentCaptor<Task> tarefaCaptor;


    @Test
    @DisplayName("Deve atualizar tarefa corretamente")
    void deveAtualizarTarefaCorretamente() {
        Usuario usuario = UsuarioFactory.get();
        Task tarefa = TarefaFactory.get(usuario);

        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("Novo titulo");
        request.setDescription("Nova descrição");
        request.setDeadlineDate(LocalDateTime.now().plusDays(2));
        request.setPriority(Priority.LOW);

        tested.updateTask(request, tarefa.getId());

        verify(tarefaRepository).save(tarefaCaptor.capture());

        Task novaTarefa = tarefaCaptor.getValue();

        assertEquals(novaTarefa.getTitle(), request.getTitle());
        assertEquals(novaTarefa.getDescription(), request.getDescription());
        assertEquals(novaTarefa.getDeadlineDate(), request.getDeadlineDate());
        assertEquals(novaTarefa.getPriority(), request.getPriority());
    }

    @Test
    @DisplayName("Deve atualizar titulo e descricao")
    void deveAtualizarSomenteTitulo() {
        Usuario usuario = UsuarioFactory.get();
        Task tarefa = TarefaFactory.get(usuario);

        LocalDateTime prazoEsperado = tarefa.getDeadlineDate();
        Priority prioridadeEsperada = tarefa.getPriority();
        String descricaoEsperada = "";

        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("Novo titulo");
        request.setDescription("");

        tested.updateTask(request, tarefa.getId());

        verify(tarefaRepository).save(tarefaCaptor.capture());

        Task novaTarefa = tarefaCaptor.getValue();

        assertEquals(novaTarefa.getTitle(), request.getTitle());
        assertEquals(novaTarefa.getDescription(), descricaoEsperada);
        assertEquals(novaTarefa.getDeadlineDate(), prazoEsperado);
        assertEquals(novaTarefa.getPriority(), prioridadeEsperada);
    }

    @Test
    @DisplayName("Deve atualizar descricao")
    void deveAtualizarSomenteDescricao() {
        Usuario usuario = UsuarioFactory.get();
        Task tarefa = TarefaFactory.get(usuario);

        String tituloEsperado = tarefa.getTitle();
        LocalDateTime prazoEsperado = tarefa.getDeadlineDate();
        Priority prioridadeEsperada = tarefa.getPriority();

        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("");
        request.setDescription("Nova descricao!");

        tested.updateTask(request, tarefa.getId());

        verify(tarefaRepository).save(tarefaCaptor.capture());

        Task novaTarefa = tarefaCaptor.getValue();

        assertEquals(novaTarefa.getTitle(), tituloEsperado);
        assertEquals(novaTarefa.getDescription(), request.getDescription());
        assertEquals(novaTarefa.getDeadlineDate(), prazoEsperado);
        assertEquals(novaTarefa.getPriority(), prioridadeEsperada);
    }

    @Test
    @DisplayName("Deve atualizar prazo")
    void deveAtualizarSomentePrazo() {
        Usuario usuario = UsuarioFactory.get();
        Task tarefa = TarefaFactory.get(usuario);

        String tituloEsperado = tarefa.getTitle();
        String descricaoEsperada = "";
        Priority prioridadeEsperada = tarefa.getPriority();

        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("");
        request.setDescription("");
        request.setDeadlineDate(LocalDateTime.now().plusDays(5));

        tested.updateTask(request, tarefa.getId());

        verify(tarefaRepository).save(tarefaCaptor.capture());

        Task novaTarefa = tarefaCaptor.getValue();

        assertEquals(novaTarefa.getTitle(), tituloEsperado);
        assertEquals(novaTarefa.getDescription(), descricaoEsperada);
        assertEquals(novaTarefa.getDeadlineDate(), request.getDeadlineDate());
        assertEquals(novaTarefa.getPriority(), prioridadeEsperada);
    }

    @Test
    @DisplayName("Deve atualizar prioridade")
    void deveAtualizarSomentePrioridade() {
        Usuario usuario = UsuarioFactory.get();
        Task tarefa = TarefaFactory.get(usuario);

        String tituloEsperado = tarefa.getTitle();
        String descricaoEsperada = "";
        LocalDateTime prazoEsperado = tarefa.getDeadlineDate();

        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("");
        request.setDescription("");
        request.setPriority(Priority.LOW);

        tested.updateTask(request, tarefa.getId());

        verify(tarefaRepository).save(tarefaCaptor.capture());

        Task novaTarefa = tarefaCaptor.getValue();

        assertEquals(novaTarefa.getTitle(), tituloEsperado);
        assertEquals(novaTarefa.getDescription(), descricaoEsperada);
        assertEquals(novaTarefa.getDeadlineDate(), prazoEsperado);
        assertEquals(novaTarefa.getPriority(), request.getPriority());
    }
}
