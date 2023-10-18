package br.feevale.service.core;

import br.feevale.factories.LivroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarLivroServiceTest {

    @InjectMocks
    private BuscarLivroService tested;

    @Mock
    private LivroRepository repository;

    @Test
    @DisplayName("Deve buscar livro por id")
    void deveRetornarLivro() {
        Livro livro = LivroFactory.getDisponivel();
        when(repository.findById(livro.getId())).thenReturn(Optional.of(livro));

        Livro retorno = tested.porId(livro.getId());

        verify(repository).findById(livro.getId());
        assertEquals(livro, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar livro")
    void deveRetornarErro() {

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.porId(1L));

        assertEquals("livro não encontrado", exception.getReason());
    }
}
