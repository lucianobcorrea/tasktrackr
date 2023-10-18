package br.feevale.service;

import br.com.cwi.crescer.biblioteca.service.core.NowService;
import br.com.cwi.crescer.biblioteca.service.core.ValidaTituloUnicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static br.com.cwi.crescer.biblioteca.domain.Situacao.ATIVO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncluirLivroServiceTest {

    @InjectMocks
    private IncluirLivroService tested;

    @Mock
    private ValidaTituloUnicoService validaTituloUnicoService;

    @Mock
    private NowService nowService;

    @Mock
    private LivroRepository repository;

    @Captor
    private ArgumentCaptor<Livro> livroCaptor;

    @Test
    @DisplayName("Deve incluir novo livro como ativo e com data de inclusão")
    void deveIncluirLivro() {

        LivroRequest request = new LivroRequest();
        request.setTitulo("Livro de teste");

        LocalDate hoje = LocalDate.now();

        when(nowService.getDate()).thenReturn(hoje);

        tested.incluir(request);

        verify(validaTituloUnicoService).validar(request.getTitulo());
        verify(repository).save(livroCaptor.capture());

        Livro livro = livroCaptor.getValue();
        assertEquals(ATIVO, livro.getSituacao());
        assertEquals(hoje, livro.getDataInclusao());
    }

    @Test
    @DisplayName("Não deve incluir livro se existir outro livro com o mesmo título")
    void deveRetornarErroParaTituloDuplicado() {

        LivroRequest request = new LivroRequest();
        request.setTitulo("Livro de teste");

        doThrow(ResponseStatusException.class)
                .when(validaTituloUnicoService)
                .validar(request.getTitulo());

        assertThrows(ResponseStatusException.class, () -> {
            tested.incluir(request);
        });

        verify(repository, never()).save(livroCaptor.capture());
    }
}