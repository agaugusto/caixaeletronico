package br.com.adriano.caixaeletronico.controller;

import br.com.adriano.caixaeletronico.dto.response.NotasDTO;
import br.com.adriano.caixaeletronico.service.NotasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Saque")
public class Saque {

    private final NotasService notasService;

    public Saque(NotasService notasService) {
        this.notasService = notasService;
    }

    @GetMapping("/{valor}")
    public List<NotasDTO> retornaQuantidadeDeNotas(@PathVariable("valor") Double valor) throws Exception {
        //return notasService.distribuicaoNotas(valor);
        return null;
    }

}
