package br.feevale.mapper;

import br.feevale.controller.response.LivroResponse;
import br.feevale.factories.LivroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AlterarLivroMapperTest {

    @Test
    @DisplayName("Deve retornar o response quando alterar um livro com sucesso")
    void deveRetornarResponseAoAlterarLivro() {

        Livro livro = LivroFactory.getBuilder().build();

        LivroResponse response = AlterarLivroMapper.toResponse(livro);

        assertEquals(livro.getId(), response.getId());
        assertEquals(livro.getTitulo(), response.getTitulo());
        assertEquals(livro.getSituacao(), response.getSituacao());
    }
}
