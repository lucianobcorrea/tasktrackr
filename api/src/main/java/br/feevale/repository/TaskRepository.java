package br.feevale.repository;

import br.feevale.domain.Status;
import br.feevale.domain.Task;
import br.feevale.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.status = :status AND t.deadlineDate < :actualDate ")
    Page<Task> findByUserAndStatusAndLate(
            @Param("user") Usuario usuario,
            @Param("status") Status status,
            @Param("actualDate") LocalDateTime actualDate,
            Pageable pageable
    );

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.status = :status AND DATE(t.deadlineDate) = :deadLineDate ")
    Page<Task> findByUserAndStatusAndDay(
            @Param("user") Usuario user,
            @Param("status") Status status,
            @Param("deadLineDate") LocalDate deadLineDate,
            Pageable pageable
    );

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.status = :status AND t.finishDate <= :dataAtual AND  t.finishDate >= :dataSemanaPassada")
    List<Task> buscarPorUsuarioEStatusEPeriodo(
            @Param("user") Usuario usuario,
            @Param("status") Status status,
            @Param("dataAtual") LocalDate dataAtual,
            @Param("dataSemanaPassada") LocalDate dataSemanaPassada
    );

    Page<Task> findByUserAndStatus(Usuario authenticatedUser, Status filteredStatus, Pageable pageable);
}
