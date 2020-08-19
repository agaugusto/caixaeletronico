package br.com.adriano.atm.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BallotUnavailableException extends Exception {
    public BallotUnavailableException(String message) {
        super(message);
    }
}
