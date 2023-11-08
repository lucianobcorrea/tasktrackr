package br.feevale.service;

import br.feevale.controller.response.RankingResponse;
import br.feevale.mapper.UsuarioRankingMapper;
import br.feevale.security.domain.Usuario;
import br.feevale.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRankingService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<RankingResponse> ranking(Pageable pageable) {

        List<Usuario> users = usuarioRepository.findAllUsuariosByRanking();

        return users.stream().map(UsuarioRankingMapper::toResponse).collect(Collectors.toList());
    }
}
