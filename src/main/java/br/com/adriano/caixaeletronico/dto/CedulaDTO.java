package br.com.adriano.caixaeletronico.dto;


import br.com.adriano.caixaeletronico.tipo.TipoNota;
import lombok.*;

@Data
public class CedulaDTO {
    private TipoNota tipoNota;
    private Integer quantidade;
}
