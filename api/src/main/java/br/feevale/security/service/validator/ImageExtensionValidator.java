package br.feevale.security.service.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ImageExtensionValidator {

    public void validate(String[] extensao) {
        if(!extensao[extensao.length - 1].equals("jpg") && !extensao[extensao.length - 1].equals("png") && !extensao[extensao.length - 1].equals("")) {
            throw new ResponseStatusException(BAD_REQUEST, "Imagem inválida, tente novamente com uma " +
                    "imagem com extensão png ou jpg");
        }
    }
}
