package br.com.adriano.caixaeletronico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NumeroDeNotasIndisoinivelException extends Exception {
}
