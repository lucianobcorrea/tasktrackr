package br.feevale.service.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidaTituloUnicoServiceTest {

    @InjectMocks
    private ValidaTituloUnicoService tested;

    @Mock
    private LivroRepository repository;

    @Test
    @DisplayName("Deve fazer nada quando título for único")
    void deveFazerNadaSeTituloUnico() {

        String titulo = "Meu livro de teste";
        when(repository.existsByTitulo(titulo)).thenReturn(false);

        tested.validar(titulo);

        verify(repository).existsByTitulo(titulo);
    }

    @Test
    @DisplayName("Deve retornar erro quando existir outro livro com o mesmo título")
    void deveRetornarErroQuandoTituloDuplicado() {

        String titulo = "Meu livro de teste";
        when(repository.existsByTitulo(titulo)).thenReturn(true);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.validar(titulo));

        assertEquals("título do livro deve ser único", exception.getReason());
    }
}
