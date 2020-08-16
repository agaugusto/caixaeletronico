package br.com.adriano.caixaeletronico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NumeroDeNotasIndisponivelException extends Exception {
    public NumeroDeNotasIndisponivelException(String message) {
        super(message);
    }
}