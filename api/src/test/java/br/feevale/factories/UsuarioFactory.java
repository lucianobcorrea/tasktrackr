package br.feevale.factories;

import br.feevale.security.domain.Usuario;

public class UsuarioFactory {

    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNome("Usuário de teste");
        usuario.setTelefone("519999999");
        usuario.setEmail("teste@gmail.com");
        usuario.setFoto("fototeste.url");
        usuario.setAtivo(true);
        usuario.setPoints(100);
        return usuario;
    }
}
