package br.feevale.security.service;

import br.feevale.security.controller.request.UsuarioUpdateRequest;
import br.feevale.security.controller.response.UsuarioResponse;
import br.feevale.security.domain.Usuario;
import br.feevale.security.mapper.UsuarioMapper;
import br.feevale.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse update(UsuarioUpdateRequest request) {

        Usuario user = usuarioAutenticadoService.get();

        user.setFoto(request.getFoto());
        user.setNome(request.getNome());
        user.setTelefone(request.getTelefone());

        usuarioRepository.save(user);

        return UsuarioMapper.toResponse(user);
    }
}
