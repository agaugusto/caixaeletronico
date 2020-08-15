package br.com.adriano.caixaeletronico.service;

import br.com.adriano.caixaeletronico.controller.TipoNotas;
import br.com.adriano.caixaeletronico.dto.response.NotasDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotasService {
    
    private final DispenserService dispenser;

    public NotasService(DispenserService dispenser) {
        this.dispenser = dispenser;
    }


    public List<NotasDTO> distribuicaoNotas(Integer valor) throws Exception {
        List<NotasDTO> listaCedulas = new ArrayList<>();
        NotasDTO cedulaDTO = null;
        Integer valorRestante = valor.intValue();
        Integer quantidade = 0;

            if (valorRestante >= 100) {
                quantidade = valorRestante / 100;
                Integer quantidadeDisponivel = dispenser.retornaNota(TipoNotas.NOTAS_100).getQuantidadeDisponivel();
                if(quantidade > quantidadeDisponivel){
                    quantidade = quantidadeDisponivel;
                }
                if (quantidade > 0) {
                    valorRestante = valorRestante - (100 * quantidade);
                    listaCedulas.add(buildNotaDTO(quantidade, TipoNotas.NOTAS_100));
                }
            }
            if (valorRestante >= 50) {
                quantidade = valorRestante / 50;
                Integer quantidadeDisponivel = dispenser.retornaNota(TipoNotas.NOTAS_50).getQuantidadeDisponivel();
                if(quantidade > quantidadeDisponivel){
                    quantidade = quantidadeDisponivel;
                }
                if (quantidade > 0) {
                    valorRestante = valorRestante - (50 * quantidade);
                    listaCedulas.add(buildNotaDTO(quantidade, TipoNotas.NOTAS_50));
                }
            }
            if (valorRestante >= 20) {
                quantidade = valorRestante / 20;
                Integer quantidadeDisponivel = dispenser.retornaNota(TipoNotas.NOTAS_20).getQuantidadeDisponivel();
                if(quantidade > quantidadeDisponivel){
                    quantidade = quantidadeDisponivel;
                }
                if (quantidade > 0) {
                    valorRestante = valorRestante - (20 * quantidade);
                    listaCedulas.add(buildNotaDTO(quantidade, TipoNotas.NOTAS_20));
                }
            }
            if (valorRestante >= 10) {
                quantidade = valorRestante / 10;
                Integer quantidadeDisponivel = dispenser.retornaNota(TipoNotas.NOTAS_10).getQuantidadeDisponivel();
                if(quantidade > quantidadeDisponivel){
                    quantidade = quantidadeDisponivel;
                }
                if (quantidade > 0) {
                    valorRestante = valorRestante - (10 * quantidade);
                    listaCedulas.add(buildNotaDTO(quantidade, TipoNotas.NOTAS_10));
                }
            }
            if(valorRestante >= 10){
                throw new Exception("Valor solicitado indisponível!");
            }else if(valorRestante != 0){
                throw new Exception("Não é permitido valor menor que 10 reais!");
            }
        return listaCedulas;
    }

    private NotasDTO buildNotaDTO(Integer quantidade, TipoNotas tipo) {
        NotasDTO notasDTO = new NotasDTO();
        notasDTO.setQuantidade(quantidade);
        notasDTO.setNota(tipo);
        return notasDTO;
    }
}
