package br.feevale.security.service;

import br.feevale.security.controller.response.UsuarioResponse;
import br.feevale.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.feevale.security.mapper.UsuarioMapper.toResponse;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public UsuarioResponse buscar() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return toResponse(usuarioAutenticado);
    }
}
