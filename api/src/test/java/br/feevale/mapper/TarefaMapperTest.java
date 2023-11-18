package br.feevale.mapper;

import br.feevale.controller.response.TaskResponse;
import br.feevale.domain.Task;
import br.feevale.factories.TarefaFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TarefaMapperTest {

    @Test
    @DisplayName("Deve retornar o response completo")
    void deveRetornarResponseCompleto() {
        Task tarefa = TarefaFactory.getComDataCriacao();

        TaskResponse response = TaskMapper.toResponse(tarefa);

        assertEquals(tarefa.getId(), response.getId());
        assertEquals(tarefa.getTitle(), response.getTitle());
        assertEquals(tarefa.getDescription(), response.getDescription());
        assertEquals(tarefa.getCreatedAt(), response.getCreatedAt());
        assertEquals(tarefa.getDeadlineDate(), response.getDeadlineDate());
        assertEquals(tarefa.getPriority(), response.getPriority());
        assertEquals(tarefa.getStatus(), response.getStatus());
    }
}
