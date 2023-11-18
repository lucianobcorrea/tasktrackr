package br.feevale.service;

import br.feevale.domain.Task;
import br.feevale.security.domain.Usuario;
import br.feevale.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaTarefaUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void validar(Task tarefa) {
        Usuario usuario = usuarioAutenticadoService.get();
        if(!usuario.getTarefas().contains(tarefa)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não possui tarefa com id especificado");
        }
    }
}
