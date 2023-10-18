package br.feevale.service;

import br.feevale.factories.LivroFactory;
import br.feevale.factories.SimpleFactory;
import br.com.cwi.crescer.biblioteca.service.core.BuscarLivroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DevolverLivroServiceTest {

    @InjectMocks
    private DevolverLivroService tested;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private LivroRepository livroRepository;

    @Test
    @DisplayName("Deve devolver livro alugado")
    void deveDevolverLivroAlugadoCompleto() {

        Livro livro = LivroFactory.getAlugado();
        Long livroId = livro.getId();

        when(buscarLivroService.porId(livroId)).thenReturn(livro);

        tested.devolver(livroId);

        verify(buscarLivroService).porId(livroId);
        verify(livroRepository).save(livro);

        assertNull(livro.getResponsavel());
        assertNull(livro.getDataDevolucao());
    }

    @Test
    @DisplayName("Não deve devolver livro que estiver disponível")
    void naoDeveDevolverLivroDisponivel() {

        Livro livro = LivroFactory.getDisponivel();
        Long id = livro.getId();

        when(buscarLivroService.porId(id)).thenReturn(livro);

        assertThrows(ResponseStatusException.class, () -> {
            tested.devolver(id);
        });

        verify(livroRepository, never()).save(livro);
    }

    @Test
    @DisplayName("Não deve devolver livro que não existe")
    void naoDeveDevolverLivroQueNaoExiste() {

        Long id = SimpleFactory.getRandomLong();
        doThrow(ResponseStatusException.class).when(buscarLivroService).porId(id);

        assertThrows(ResponseStatusException.class, () -> tested.devolver(id));

        verify(livroRepository, never()).save(Mockito.any());
    }
}
