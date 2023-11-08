package br.feevale.security.repository;

import br.feevale.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("select u from Usuario u order by u.points desc")
    List<Usuario> findAllUsuariosByRanking();
}
