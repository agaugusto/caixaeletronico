package br.com.adriano.caixaeletronico.services;

import br.com.adriano.caixaeletronico.dto.NotaDTO;
import br.com.adriano.caixaeletronico.exception.NumeroDeNotasIndisponivelException;
import br.com.adriano.caixaeletronico.exception.ValorIndisponivelException;
import br.com.adriano.caixaeletronico.tipo.TipoNota;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaqueService {

    private final DispenserService dispenser;

    public SaqueService(DispenserService dispenser) {
        this.dispenser = dispenser;
    }

    public List<NotaDTO> distribuicaoNotas(Integer valor) throws Exception {
        List<NotaDTO> listaCedulas = new ArrayList<>();
        Integer valorRestante = valor.intValue();
        NotaDTO notaDTO = new NotaDTO();
        List<Cedula> cedulasList = dispenser.listarNotasEmEstoque();
        for(Cedula cedula : cedulasList){
            notaDTO = calculaQuantidadeDeNotas(cedula.getNota(), valorRestante);
            if (notaDTO != null) {
                listaCedulas.add(notaDTO);
                valorRestante = valorRestante - (cedula.getNota().getValue() * notaDTO.getQuantidade());
            }
        }
        if(valorRestante >= 10){
            throw new NumeroDeNotasIndisponivelException("Valor solicitado indisponível!");
        }else if(valorRestante != 0){
            throw new ValorIndisponivelException("Não é permitido valor menor que 10 reais!");
        }
        atualizarDispenser(listaCedulas);
        return listaCedulas;
    }

    private NotaDTO calculaQuantidadeDeNotas (TipoNota tipoNota, Integer valorRestante) {
        if (valorRestante >= tipoNota.getValue()) {
            Integer quantidade = valorRestante / tipoNota.getValue();
            Integer quantidadeDisponivel = dispenser.retornaQuantidadeDeNotasDoTipo(tipoNota);
            if (quantidade > quantidadeDisponivel) {
                quantidade = quantidadeDisponivel;
            }
            if (quantidade > 0) {
                return buildNotaDTO(quantidade, tipoNota);
            }
        }
        return null;
    }

    private void atualizarDispenser(List<NotaDTO> notaDTOList){
        notaDTOList
                .stream()
                .forEach(notaDTO -> {
                    try {
                        dispenser.atualizarQuantidadeNotas(notaDTO.getTipoNota(), notaDTO.getQuantidade());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private NotaDTO buildNotaDTO(Integer quantidade, TipoNota tipo) {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setQuantidade(quantidade);
        notaDTO.setTipoNota(tipo);
        return notaDTO;
    }
}
