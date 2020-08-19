package br.com.adriano.atm.handler;

import br.com.adriano.atm.error.BallotUnavailableException;
import br.com.adriano.atm.error.NumberOfCedulasUnavailableException;
import br.com.adriano.atm.error.ExceptionDetails;
import br.com.adriano.atm.error.WithdrawalAmountUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(WithdrawalAmountUnavailableException.class)
    public ResponseEntity<?> handleValorIndisponivelException(WithdrawalAmountUnavailableException exception) {
        ExceptionDetails eDatails = ExceptionDetails.builder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developorMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(eDatails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberOfCedulasUnavailableException.class)
    public ResponseEntity<?> handleNumeroDeNotasIndisponivelException(NumberOfCedulasUnavailableException exception) {
        ExceptionDetails eDatails = ExceptionDetails.builder()
                .timeStamp(Timestamp.valueOf(LocalDateTime.now()).getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developorMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(eDatails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BallotUnavailableException.class)
    public ResponseEntity<?> handleCedulaIndisponivelException(BallotUnavailableException exception) {
        ExceptionDetails eDatails = ExceptionDetails.builder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developorMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(eDatails, HttpStatus.NOT_FOUND);
    }
}
