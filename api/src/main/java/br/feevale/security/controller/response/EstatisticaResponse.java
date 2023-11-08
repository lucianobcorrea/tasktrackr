package br.feevale.security.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticaResponse {

    private Integer pontuacao;
    private Integer tarefasConcluidas;
    private Integer tarefasEmAndamento;
    private Map<LocalDate, Integer> tarefasPorIntervalo;
}
