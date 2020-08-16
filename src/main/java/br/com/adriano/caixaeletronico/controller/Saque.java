package br.com.adriano.caixaeletronico.controller;

import br.com.adriano.caixaeletronico.error.NumeroDeNotasIndisponivelException;
import br.com.adriano.caixaeletronico.error.ValorIndisponivelException;
import br.com.adriano.caixaeletronico.services.SaqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saques")
public class Saque {

    private final SaqueService saqueService;

    public Saque(SaqueService saqueService) {
        this.saqueService = saqueService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{valor}")
    public ResponseEntity<?> retornaQuantidadeDeNotas(@PathVariable("valor") Integer valor) throws ValorIndisponivelException, NumeroDeNotasIndisponivelException {
        return new ResponseEntity<>(saqueService.buscarDistribuicaoDeCedulas(valor),HttpStatus.OK);
    }

}
