package br.com.adriano.caixaeletronico.controller;

import br.com.adriano.caixaeletronico.dto.NotaDTO;
import br.com.adriano.caixaeletronico.services.SaqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saques")
public class Saque {

    private final SaqueService saqueService;

    public Saque(SaqueService saqueService) {
        this.saqueService = saqueService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{valor}")
    public ResponseEntity<?> retornaQuantidadeDeNotas(@PathVariable("valor") Integer valor) {
        try {
            return new ResponseEntity<>(saqueService.distribuicaoNotas(valor),HttpStatus.OK);
        } catch (Exception e) {
            throw new NumberFormatException("testando");
        }


    }

}
