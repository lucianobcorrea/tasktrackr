package br.feevale.security.controller;

import br.feevale.security.controller.request.UsuarioRequest;
import br.feevale.security.controller.request.UsuarioUpdateRequest;
import br.feevale.security.controller.response.EstatisticaResponse;
import br.feevale.security.controller.response.UsuarioResponse;
import br.feevale.security.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService service;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private EstatisticaService estatisticaService;

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

    @GetMapping("/me")
    @ResponseStatus(OK)
    public UsuarioResponse buscar() {
        return buscarUsuarioService.buscar();
    }

    @GetMapping("/estatisticas/{tipoIntervalo}")
    public EstatisticaResponse getEstatisticas(@PathVariable String tipoIntervalo) {
        return estatisticaService.get(tipoIntervalo);
    }
}
