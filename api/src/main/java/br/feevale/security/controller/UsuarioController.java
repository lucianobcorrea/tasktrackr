package br.feevale.security.controller;

import br.feevale.security.controller.request.UsuarioRequest;
import br.feevale.security.controller.request.UsuarioUpdateRequest;
import br.feevale.security.controller.response.UsuarioResponse;
import br.feevale.security.service.IncluirUsuarioService;
import br.feevale.security.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService service;

    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping
    @ResponseStatus(CREATED)
    public UsuarioResponse incluir(@RequestBody UsuarioRequest request) {
        return service.incluir(request);
    }

    @PatchMapping("/update")
    @ResponseStatus(OK)
    public UsuarioResponse update(@RequestBody UsuarioUpdateRequest request) {
        return updateUserService.update(request);
    }
}
