package br.feevale.security.service;

import br.feevale.domain.Status;
import br.feevale.domain.Task;
import br.feevale.repository.TaskRepository;
import br.feevale.security.controller.response.EstatisticaResponse;
import br.feevale.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static br.feevale.domain.Status.COMPLETED;
import static br.feevale.domain.Status.IN_PROGRESS;
import static java.time.LocalDate.from;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;

@Service
public class EstatisticaService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private TaskRepository tarefaRepository;

    private static final String INTERVALO_SEMANA = "semana";
    private static final String INTERVALO_MES = "mes";


    public EstatisticaResponse get(String tipoIntervaloMapa) {
        Map<LocalDate, Integer> tarefasPorIntervalo;
        Usuario usuario = usuarioAutenticadoService.get();

        int tarefasConcluidas = getQuantTarefasPorStatus(usuario, COMPLETED);
        int tarefasEmAndamento = getQuantTarefasPorStatus(usuario, IN_PROGRESS);

        tarefasPorIntervalo = getMapaIntervalo(usuario, tipoIntervaloMapa);

        return EstatisticaResponse.builder()
                .tarefasConcluidas(tarefasConcluidas)
                .tarefasEmAndamento(tarefasEmAndamento)
                .tarefasPorIntervalo(tarefasPorIntervalo)
                .pontuacao(usuario.getPoints())
                .build();
    }

    public Map<LocalDate, Integer> getMapaEmBranco(LocalDate dataInicial, LocalDate dataFinal) {
        long diasEntreIntervalo = DAYS
                .between(dataInicial, dataFinal);

        List<LocalDate> diasEmIntervalo = IntStream.iterate(1, i -> i + 1)
                .limit(diasEntreIntervalo)
                .mapToObj(dataInicial::plusDays)
                .collect(toList());

        Map<LocalDate, Integer> mapaEmBranco = new TreeMap<>();

        diasEmIntervalo.forEach(dia -> mapaEmBranco.put(dia, 0));

        return mapaEmBranco;

    }

    public Map<LocalDate, Integer> getMapaIntervalo(Usuario usuario, String tipoIntervalo) {
        LocalDate dataInicial = getDataInicialIntervalo(tipoIntervalo);
        LocalDate dataFinal = now();


        Map<LocalDate, Integer> mapa = getMapaEmBranco(dataInicial, dataFinal);

        List<Task> tarefas = getTarefasPorIntervalo(usuario, tipoIntervalo);

        tarefas.forEach(tarefa -> {
            LocalDate keyAtual = tarefa.getFinishDate();
            int valorNovo = mapa.get(keyAtual) + 1;
            mapa.replace(keyAtual, valorNovo);
        });

        return mapa;
    }

    public LocalDate getDataInicialIntervalo(String tipoIntervalo) {
        if (tipoIntervalo.equalsIgnoreCase(INTERVALO_SEMANA)) {
            return now().minusWeeks(1L);
        }
        if (tipoIntervalo.equalsIgnoreCase(INTERVALO_MES)) {
            return now().minusMonths(1L);
        }
        return now().minusYears(1L);

    }


    public Integer getQuantTarefasPorStatus(Usuario usuario, Status status) {
        return (int) usuario
                .getTarefas().stream()
                .filter(tarefa -> tarefa.getStatus() == status)
                .count();
    }


    public List<Task> getTarefasPorIntervalo(Usuario usuario, String tipoIntervalo) {
        LocalDate intervaloDesejado;
        if (tipoIntervalo.equalsIgnoreCase(INTERVALO_SEMANA)) {
            intervaloDesejado = now().minusWeeks(1);
        } else if (tipoIntervalo.equalsIgnoreCase(INTERVALO_MES))
            intervaloDesejado = now().minusMonths(1);
        else {
            intervaloDesejado = now().minusYears(1);
        }

        return tarefaRepository
                .buscarPorUsuarioEStatusEPeriodo(
                        usuario,
                        COMPLETED,
                        now(),
                        intervaloDesejado
                );
    }
}
