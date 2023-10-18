package br.feevale.mapper;

import br.feevale.controller.response.DetalheLivroResponse;
import br.feevale.controller.response.LivroResponse;
import br.feevale.factories.LivroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LivroMapperTest {

    @Test
    @DisplayName("Deve retornar response completo quando request completo")
    void deveRetornarResponseCompleto() {

        Livro livro = LivroFactory.getAlugado();

        LivroResponse response = LivroMapper.toResponse(livro);

        assertEquals(livro.getId(), response.getId());
        assertEquals(livro.getTitulo(), response.getTitulo());
        assertEquals(livro.getSituacao(), response.getSituacao());
    }

    @Test
    @DisplayName("Deve retornar response vazio quando request for nulo")
    void deveRetornarResponseVazio() {

        DetalheLivroResponse response = DetalheLivroMapper.toResponse(null);

        assertNotNull(response);
    }
}