package br.com.adriano.caixaeletronico.services;

import br.com.adriano.caixaeletronico.tipo.TipoNota;
import br.com.adriano.caixaeletronico.repository.DispenserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DispenserService {

    private final DispenserRepository dispenserRepository;
    private List<Cedula> notasDisponiveis;

    public DispenserService(DispenserRepository dispenserRepository) {
        this.dispenserRepository = dispenserRepository;
        notasDisponiveis = dispenserRepository.buscaNotasDispenser();
    }

    public Integer retornaQuantidadeDeNotasDoTipo(TipoNota tipoNota){
        return notasDisponiveis
                .stream()
                .filter(notas -> notas.getNota().equals(tipoNota))
                .findFirst()
                .get().getQuantidadeDisponivel();
    }

    public void atualizarQuantidadeNotas(TipoNota tipoNota, Integer quantidade) throws Exception {
        notasDisponiveis
                .stream()
                .filter(cedula -> cedula.getNota().equals(tipoNota))
                .findFirst()
                .get().retirarEstoque(quantidade);
    }

    public List<Cedula> listarNotasEmEstoque(){
        return notasDisponiveis
                .stream()
                .filter(cedula -> cedula.getQuantidadeDisponivel() > 0)
                .collect(Collectors.toList());
    }
}
