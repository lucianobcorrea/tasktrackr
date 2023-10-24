package br.feevale.controller.response;

import br.feevale.domain.Priority;
import br.feevale.domain.Status;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
public class TaskResponse {
    
    private Long id;
    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime deadlineDate;
    private LocalDateTime finishDate;

    private Status status;
    private Priority priority;
}
