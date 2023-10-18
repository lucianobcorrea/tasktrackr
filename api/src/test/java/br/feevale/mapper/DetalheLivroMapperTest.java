package br.feevale.mapper;

import br.feevale.controller.response.DetalheLivroResponse;
import br.feevale.factories.LivroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DetalheLivroMapperTest {

    @Test
    @DisplayName("Deve retornar o response completo quando receber um livro completo")
    void deveRetornarResponseCompleto() {

        Livro livro = LivroFactory.getAlugado();

        DetalheLivroResponse response = DetalheLivroMapper.toResponse(livro);

        assertEquals(livro.getId(), response.getId());
        assertEquals(livro.getTitulo(), response.getTitulo());
        assertEquals(livro.getSituacao(), response.getSituacao());
        assertEquals(livro.getDataInclusao(), response.getDataInclusao());
        assertEquals(livro.getDataDevolucao(), response.getDataDevolucao());
        assertEquals(livro.getResponsavel().getId(), response.getResponsavelId());
    }

    @Test
    @DisplayName("Deve retonar response sem responsavel quando livro não tiver responsavel")
    void deveRetornarResponseSemResponsavel() {

        Livro livro = LivroFactory.getDisponivel();

        DetalheLivroResponse response = DetalheLivroMapper.toResponse(livro);

        assertNull(response.getResponsavelId());
    }

    @Test
    @DisplayName("Deve retornar response vazio quando livro for nulo")
    void deveRetornarResponseVazio() {

        DetalheLivroResponse response = DetalheLivroMapper.toResponse(null);

        assertNotNull(response);
    }
}
