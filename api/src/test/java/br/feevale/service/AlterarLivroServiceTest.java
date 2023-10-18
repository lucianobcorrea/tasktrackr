package br.feevale.service;

import br.feevale.controller.response.LivroResponse;
import br.feevale.factories.LivroFactory;
import br.com.cwi.crescer.biblioteca.service.core.BuscarLivroService;
import br.com.cwi.crescer.biblioteca.service.core.ValidaTituloUnicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlterarLivroServiceTest {

    @InjectMocks
    private AlterarLivroService tested;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private ValidaTituloUnicoService validaTituloUnicoService;

    @Test
    @DisplayName("Deve alterar titulo do livro")
    void deveAlterarTituloDoLivroEscolhido() {

        LivroRequest request = new LivroRequest();
        request.setTitulo("livro alterado");

        Livro livro = LivroFactory.getBuilder().build();

        Long livroId = livro.getId();

        when(buscarLivroService.porId(livroId)).thenReturn(livro);

        LivroResponse response = tested.alterar(request, livroId);

        verify(buscarLivroService).porId(livroId);
        verify(livroRepository).save(livro);

        assertEquals(livro.getTitulo(), response.getTitulo());
    }

    @Test
    @DisplayName("Não deve alterar livro que não existe")
    void naoDeveAlterarLivroQueNaoExiste() {

        LivroRequest request = new LivroRequest();
        request.setTitulo("livro alterado");

        Livro livro = LivroFactory.getBuilder().build();
        Long livroId = livro.getId();

        doThrow(ResponseStatusException.class).when(buscarLivroService).porId(livroId);

        assertThrows(ResponseStatusException.class, () -> {
            tested.alterar(request, livroId);
        });

        verify(livroRepository, never()).save(any());
    }

    @Test
    @DisplayName("Não deve alterar livro quando existir outro livro com mesmo nome")
    void naoDeveAlterarSeExistirOutroComMesmoNome() {

        LivroRequest request = new LivroRequest();
        request.setTitulo("Livro de teste");

        Livro livro = LivroFactory.getBuilder().build();
        Long livroId = livro.getId();

        when(buscarLivroService.porId(livroId)).thenReturn(livro);

        doThrow(ResponseStatusException.class)
                .when(validaTituloUnicoService)
                .validar(request.getTitulo());

        assertThrows(ResponseStatusException.class, () -> {
            tested.alterar(request, livroId);
        });

        verify(validaTituloUnicoService).validar(request.getTitulo());
        verify(livroRepository, never()).save(any());
    }
}
