package br.feevale.controller.request;

import br.feevale.domain.Priority;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateTaskRequest {

    @NotBlank(message = "Digite um título para continuar")
    public String title;

    public String description;

    @NotNull(message = "Data prazo para concluir tarefa deve ser preenchido")
    public LocalDateTime deadlineDate;

    @NotNull(message = "Prioridade da tarefa deve ser escolhida")
    public Priority priority;
}
