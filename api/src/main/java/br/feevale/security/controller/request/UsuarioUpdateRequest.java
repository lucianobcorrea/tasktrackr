package br.feevale.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioUpdateRequest {

    @NotBlank
    private String nome;

    private String telefone;
    private String foto;
}
