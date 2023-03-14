package br.com.project.msvotacao.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception {

    @Getter
    private final Integer status;

    public ErroComunicacaoMicroservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
