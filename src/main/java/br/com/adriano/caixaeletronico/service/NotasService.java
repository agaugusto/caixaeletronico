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
        Integer valorRestante = valor.intValue();
        NotasDTO calc = new NotasDTO();
        calc = calculaQuantidadeDeNotas(TipoNotas.NOTAS_100, valorRestante);
        if(calc != null){
            listaCedulas.add(calc);
            valorRestante = valorRestante - (TipoNotas.NOTAS_100.getValue() * calc.getQuantidade());
        }

        calc = calculaQuantidadeDeNotas(TipoNotas.NOTAS_50, valorRestante);
        if(calc != null){
            listaCedulas.add(calc);
            valorRestante = valorRestante - (TipoNotas.NOTAS_50.getValue() * calc.getQuantidade());
        }

        calc = calculaQuantidadeDeNotas(TipoNotas.NOTAS_20, valorRestante);
        if(calc != null){
            listaCedulas.add(calc);
            valorRestante = valorRestante - (TipoNotas.NOTAS_20.getValue() * calc.getQuantidade());
        }

        calc = calculaQuantidadeDeNotas(TipoNotas.NOTAS_10, valorRestante);
        if(calc != null){
            listaCedulas.add(calc);
            valorRestante = valorRestante - (TipoNotas.NOTAS_10.getValue() * calc.getQuantidade());
        }

        if(valorRestante >= 10){
            throw new Exception("Valor solicitado indisponível!");
        }else if(valorRestante != 0){
            throw new Exception("Não é permitido valor menor que 10 reais!");
        }
        retiradaDispenser(listaCedulas);
        return listaCedulas;
    }

    private NotasDTO calculaQuantidadeDeNotas (TipoNotas tipoNotas, Integer valorRestante) {
        if (valorRestante >= tipoNotas.getValue()) {
            Integer quantidade = valorRestante / tipoNotas.getValue();
            Integer quantidadeDisponivel = dispenser.retornaNota(tipoNotas).getQuantidadeDisponivel();
            if (quantidade > quantidadeDisponivel) {
                quantidade = quantidadeDisponivel;
            }
            if (quantidade > 0) {
                return buildNotaDTO(quantidade, tipoNotas);
            }
        }
        return null;
    }

    private void retiradaDispenser(List<NotasDTO> notasDTOList){
        notasDTOList
                .stream()
                .forEach(notasDTO -> {
                    try {
                        dispenser.retornaNota(notasDTO.getNota()).retirada(notasDTO.getQuantidade());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private NotasDTO buildNotaDTO(Integer quantidade, TipoNotas tipo) {
        NotasDTO notasDTO = new NotasDTO();
        notasDTO.setQuantidade(quantidade);
        notasDTO.setNota(tipo);
        return notasDTO;
    }
}
