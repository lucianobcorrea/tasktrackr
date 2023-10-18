package br.feevale.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(NON_NULL)
public class DetalheLivroResponse {

    private Long id;
    private String titulo;
    private Situacao situacao;
    private LocalDate dataInclusao;
    private Long responsavelId;
    private LocalDate dataDevolucao;
}
