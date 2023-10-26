package br.feevale.domain;

import br.feevale.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime deadlineDate;
    private LocalDateTime finishDate;

    @Enumerated(STRING)
    private Priority priority;

    @Enumerated(STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;
}
