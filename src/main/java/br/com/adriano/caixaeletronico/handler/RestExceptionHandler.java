package br.com.adriano.caixaeletronico.handler;

import br.com.adriano.caixaeletronico.error.CedulaIndisponivelException;
import br.com.adriano.caixaeletronico.error.NumeroDeNotasIndisponivelException;
import br.com.adriano.caixaeletronico.error.ExceptionDetails;
import br.com.adriano.caixaeletronico.error.ValorIndisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ValorIndisponivelException.class)
    public ResponseEntity<?>handleValorIndisponivelException(ValorIndisponivelException exception){
        ExceptionDetails eDatails = ExceptionDetails.Builder.newBuilder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developorMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(eDatails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumeroDeNotasIndisponivelException.class)
    public ResponseEntity<?>handleNumeroDeNotasIndisponivelException(NumeroDeNotasIndisponivelException exception){
        ExceptionDetails eDatails = ExceptionDetails.Builder.newBuilder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developorMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(eDatails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CedulaIndisponivelException.class)
    public ResponseEntity<?>handleCedulaIndisponivelException(CedulaIndisponivelException exception){
        ExceptionDetails eDatails = ExceptionDetails.Builder.newBuilder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developorMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(eDatails, HttpStatus.NOT_FOUND);
    }
}
