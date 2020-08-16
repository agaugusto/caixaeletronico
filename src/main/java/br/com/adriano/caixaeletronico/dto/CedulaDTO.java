package br.com.adriano.caixaeletronico.dto;


import br.com.adriano.caixaeletronico.tipo.TipoNota;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CedulaDTO {
    private TipoNota tipoNota;
    private Integer quantidade;
}
