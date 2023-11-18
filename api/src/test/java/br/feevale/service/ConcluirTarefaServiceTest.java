package br.feevale.service;

import br.feevale.domain.Status;
import br.feevale.domain.Task;
import br.feevale.factories.TarefaFactory;
import br.feevale.security.domain.Usuario;
import br.feevale.security.repository.UsuarioRepository;
import br.feevale.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.feevale.domain.Priority.LOW;
import static br.feevale.factories.UsuarioFactory.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ConcluirTarefaServiceTest {

    @InjectMocks
    private CompleteTaskService tested;

    @Mock
    private BuscarTarefaService buscarTarefaService;

    @Mock
    private UsuarioRepository userRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve concluir tarefa corretamente")
    void deveConcluirCorretamente() {
        Task tarefa = TarefaFactory.get();
        tarefa.setPriority(LOW);
        tarefa.setStatus(Status.IN_PROGRESS);

        Usuario usuario = get();
        usuario.setPoints(0);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        tested.completeTask(tarefa.getId());

        assertEquals(Status.COMPLETED, tarefa.getStatus());
        assertEquals(LOW.getPoints(), usuario.getPoints());
        assertEquals(tarefa.getFinishDate(), LocalDate.now());
        verify(userRepository).save(usuario);
    }

    @Test
    @DisplayName("Deve retornar erro caso tentar concluir uma tarefa ja concluida")
    void deveRetornarErroComTarefaJaConcluida() {
        Task tarefa = TarefaFactory.get();
        tarefa.setStatus(Status.COMPLETED);
        Long id = tarefa.getId();

        when(buscarTarefaService.porId(tarefa.getId())).thenReturn(tarefa);

        assertThrows(ResponseStatusException.class, () -> {
            tested.completeTask(id);
        });
    }
}
