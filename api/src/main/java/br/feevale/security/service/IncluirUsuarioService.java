package br.feevale.security.service;

import br.feevale.security.controller.request.UsuarioRequest;
import br.feevale.security.controller.response.UsuarioResponse;
import br.feevale.security.domain.Usuario;
import br.feevale.security.repository.UsuarioRepository;
import br.feevale.security.service.validator.ImageExtensionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.feevale.security.mapper.UsuarioMapper.toEntity;
import static br.feevale.security.mapper.UsuarioMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageExtensionValidator imageExtensionValidator;

    public UsuarioResponse incluir(UsuarioRequest request) {

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.setAtivo(true);

        if(request.getFoto() != null) {
            imageExtensionValidator.validate(request.getFoto().split("\\."));
        }

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }
}
