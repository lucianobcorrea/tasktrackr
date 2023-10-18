package br.feevale.factories;

import java.time.LocalDate;

import static br.com.cwi.crescer.biblioteca.domain.Situacao.ATIVO;

public class LivroFactory {

    public static Livro.LivroBuilder getBuilder() {
        return Livro.builder()
                .id(SimpleFactory.getRandomLong())
                .titulo("Livro de teste")
                .situacao(ATIVO)
                .dataInclusao(LocalDate.of(2022, 4, 1));
    }

    public static Livro getAlugado() {
        return getBuilder()
                .responsavel(UsuarioFactory.get())
                .dataDevolucao(LocalDate.of(2022, 4, 5))
                .build();
    }

    public static Livro getDisponivel() {
        return getBuilder().build();
    }
}

