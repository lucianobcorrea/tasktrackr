package br.feevale.mapper;

import br.feevale.controller.response.RankingResponse;
import br.feevale.security.domain.Usuario;

public class UsuarioRankingMapper {

    public static RankingResponse toResponse(Usuario usuario) {
        return RankingResponse.builder()
                .image(usuario.getFoto())
                .name(usuario.getNome())
                .points(usuario.getPoints())
                .build();
    }
}
