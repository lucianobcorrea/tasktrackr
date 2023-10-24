package br.feevale.security.service;

import br.feevale.security.controller.request.UsuarioRequest;
import br.feevale.security.controller.response.UsuarioResponse;
import br.feevale.security.domain.Permissao;
import br.feevale.security.domain.Usuario;
import br.feevale.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.feevale.security.domain.Funcao.USUARIO;
import static br.feevale.security.mapper.UsuarioMapper.toEntity;
import static br.feevale.security.mapper.UsuarioMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse incluir(UsuarioRequest request) {

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.adicionarPermissao(getPermissaoPadrao());
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

    private Permissao getPermissaoPadrao() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(USUARIO);
        return permissao;
    }
}
