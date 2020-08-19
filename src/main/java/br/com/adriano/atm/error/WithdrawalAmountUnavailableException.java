package br.com.adriano.atm.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WithdrawalAmountUnavailableException extends Exception {
    public WithdrawalAmountUnavailableException(String message) {
        super(message);
    }
}
