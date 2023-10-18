package br.feevale.service;

import br.feevale.controller.response.LivroResponse;
import br.feevale.factories.LivroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarLivroServiceTest {

    @InjectMocks
    private ListarLivroService tested;

    @Mock
    private LivroRepository livroRepository;

    @Test
    @DisplayName("Deve buscar todos os livros ativos e retornar reponse paginado")
    void deveRetornarTodosOsLivrosAtivos() {

        Pageable pageable = PageRequest.of(0, 5);

        List<Livro> livros = List.of(
                LivroFactory.getBuilder().id(1L).build(),
                LivroFactory.getBuilder().id(2L).build(),
                LivroFactory.getBuilder().id(3L).build()
        );

        Page<Livro> livrosPaginado = new PageImpl<>(livros);

        when(livroRepository.findAllBySituacao(Situacao.ATIVO, pageable)).thenReturn(livrosPaginado);

        Page<LivroResponse> response = tested.listar(pageable);

        verify(livroRepository).findAllBySituacao(Situacao.ATIVO, pageable);
        assertEquals(livros.size(), response.getSize());
        assertEquals(livros.get(0).getId(), response.getContent().get(0).getId());
        assertEquals(livros.get(1).getId(), response.getContent().get(1).getId());
        assertEquals(livros.get(2).getId(), response.getContent().get(2).getId());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não encontrar livros")
    void deveRetornarListaVazia() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Livro> livrosPaginado = new PageImpl<>(new ArrayList<>());

        when(livroRepository.findAllBySituacao(Situacao.ATIVO, pageable)).thenReturn(livrosPaginado);

        Page<LivroResponse> response = tested.listar(pageable);

        Assertions.assertNotNull(response);
        assertEquals(0, response.getSize());
    }
}