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
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.status = :status AND DATE(t.deadlineDate) < :actualDate ")
    Page<Task> findByUserAndStatusAndLate(
            @Param("user") Usuario usuario,
            @Param("status") Status status,
            @Param("actualDate") LocalDate actualDate,
            Pageable pageable
    );

    @Query("SELECT t FROM Tarefa t WHERE t.user = :user AND t.status = :status AND DATE(t.deadlineDate) = :deadLineDate ")
    Page<Task> findByUserAndStatusAndDay(
            @Param("user") Usuario user,
            @Param("status") Status status,
            @Param("deadLineDate") LocalDate deadLineDate,
            Pageable pageable
    );

    Page<Task> findByUserAndStatus(Usuario authenticatedUser, Status filteredStatus, Pageable pageable);
}
