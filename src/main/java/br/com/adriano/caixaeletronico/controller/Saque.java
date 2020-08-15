package br.com.adriano.caixaeletronico.controller;

import br.com.adriano.caixaeletronico.dto.response.NotasDTO;
import br.com.adriano.caixaeletronico.service.NotasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saque")
public class Saque {

    private final NotasService notasService;

    public Saque(NotasService notasService) {
        this.notasService = notasService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{valor}")
    public List<NotasDTO> retornaQuantidadeDeNotas(@PathVariable("valor") Integer valor) {
        try {
            return notasService.distribuicaoNotas(valor);
        } catch (Exception e) {
            return (List<NotasDTO>) ResponseEntity.badRequest().build();
        }


    }

}
