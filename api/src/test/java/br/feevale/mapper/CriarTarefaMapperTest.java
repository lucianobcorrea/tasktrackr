package br.feevale.mapper;

import br.feevale.controller.request.CreateTaskRequest;
import br.feevale.domain.Priority;
import br.feevale.domain.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriarTarefaMapperTest {

    @Test
    @DisplayName("Deve retornar a entity completa")
    void deveRetornarEntityCompleta() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Teste");
        request.setDescription("Descricao Teste");
        request.setPriority(Priority.AVERAGE);
        request.setDeadlineDate(LocalDateTime.now());

        Task entity = TaskMapper.toEntity(request);

        assertEquals(request.getTitle(), entity.getTitle());
        assertEquals(request.getDescription(), entity.getDescription());
        assertEquals(request.getPriority(), entity.getPriority());
        assertEquals(request.getDeadlineDate(), entity.getDeadlineDate());
    }
}
