package br.com.adriano.caixaeletronico.services;

import br.com.adriano.caixaeletronico.dto.CedulaDTO;
import br.com.adriano.caixaeletronico.error.NumeroDeNotasIndisponivelException;
import br.com.adriano.caixaeletronico.error.ValorIndisponivelException;
import br.com.adriano.caixaeletronico.tipo.TipoNota;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaqueService {

    private final DispenserService dispenser;

    public SaqueService(DispenserService dispenser) {
        this.dispenser = dispenser;
    }

    public List<CedulaDTO> buscarDistribuicaoDeCedulas(Integer valor) throws NumeroDeNotasIndisponivelException, ValorIndisponivelException {
        List<CedulaDTO> listaCedulas = new ArrayList<>();
        Integer valorRestante = valor;
        CedulaDTO cedulaDTO;
        List<Cedula> cedulasList = dispenser.buscarNotasEmEstoque();
        for(Cedula cedula : cedulasList){
            cedulaDTO = buscarQuantidadeDeCedulasDoTipo(cedula.getNota(), valorRestante);
            if (cedulaDTO != null) {
                listaCedulas.add(cedulaDTO);
                valorRestante = valorRestante - (cedula.getNota().getValue() * cedulaDTO.getQuantidade());
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

    private CedulaDTO buscarQuantidadeDeCedulasDoTipo(TipoNota tipoNota, Integer valorRestante) {
        if (valorRestante >= tipoNota.getValue()) {
            Integer quantidade = valorRestante / tipoNota.getValue();
            Optional<Cedula> cedula = dispenser.buscarCedulaDoTipo(tipoNota);
            if(cedula.isPresent()) {
                if (quantidade > cedula.get().getQuantidadeDisponivel()) {
                    quantidade = cedula.get().getQuantidadeDisponivel();
                }
                if (quantidade > 0) {
                    return buildCedulaDTO(quantidade, tipoNota);
                }
            }
        }
        return null;
    }

    private void atualizarDispenser(List<CedulaDTO> cedulaDTOList){
        cedulaDTOList
                .stream()
                .forEach(cedulaDTO -> {
                    try {
                        dispenser.atualizarRetiraDeCedulas(cedulaDTO.getTipoNota(), cedulaDTO.getQuantidade());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private CedulaDTO buildCedulaDTO(Integer quantidade, TipoNota tipo) {
        CedulaDTO cedulaDTO = new CedulaDTO();
        cedulaDTO.setQuantidade(quantidade);
        cedulaDTO.setTipoNota(tipo);
        return cedulaDTO;
    }
}
