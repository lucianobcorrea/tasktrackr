package br.feevale.service;

import br.feevale.factories.LivroFactory;
import br.feevale.factories.SimpleFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.cwi.crescer.biblioteca.domain.Situacao.INATIVO;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoverLivroServiceTest {

    @InjectMocks
    private RemoverLivroService tested;

    @Mock
    private LivroRepository livroRepository;

    @Test
    @DisplayName("Deve remover (mais completo) livro com sucesso")
    void deveRemoverMaisCompleto() {

        Livro livro = LivroFactory.getDisponivel();
        Long livroId = livro.getId();

        when(livroRepository.findById(livroId)).thenReturn(of(livro));

        tested.removerMaisCompleto(livroId);

        verify(livroRepository).findById(livroId);
        verify(livroRepository).save(livro);

        assertEquals(INATIVO, livro.getSituacao());
    }





















    @Test
    @DisplayName("Deve remover livro com sucesso")
    void deveRemover() {

        Long livroId = SimpleFactory.getRandomLong();

        tested.remover(livroId);

        verify(livroRepository).deleteById(livroId);
    }
}