package br.com.adriano.atm.controller;

import br.com.adriano.atm.error.NumberOfCedulasUnavailableException;
import br.com.adriano.atm.error.WithdrawalAmountUnavailableException;
import br.com.adriano.atm.services.WithdrawMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdraw")
public class WithdrawMoneyController {

    private final WithdrawMoneyService withdrawMoneyService;

    public WithdrawMoneyController(WithdrawMoneyService withdrawMoneyService) {
        this.withdrawMoneyService = withdrawMoneyService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{value}")
    public ResponseEntity<?> retornaQuantidadeDeNotas(@PathVariable("value") Integer valeu) throws WithdrawalAmountUnavailableException, NumberOfCedulasUnavailableException {
        return new ResponseEntity<>(withdrawMoneyService.moneyBill(valeu), HttpStatus.OK);
    }

}
