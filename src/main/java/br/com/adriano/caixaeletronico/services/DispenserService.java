package br.com.adriano.caixaeletronico.services;

import br.com.adriano.caixaeletronico.error.CedulaIndisponivelException;
import br.com.adriano.caixaeletronico.error.NumeroDeNotasIndisponivelException;
import br.com.adriano.caixaeletronico.model.Cedula;
import br.com.adriano.caixaeletronico.tipo.TipoNota;
import br.com.adriano.caixaeletronico.repository.DispenserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DispenserService {

    private List<Cedula> notasDisponiveis;
    private final DispenserRepository dispenserRepository;

    public DispenserService(DispenserRepository dispenserRepository) {
        this.dispenserRepository = dispenserRepository;
    }

    private void buscarNotasDispenser() {
        notasDisponiveis = dispenserRepository.buscarNotasDispenser();
    }

    public Optional<Cedula> buscarCedulaDoTipo(TipoNota tipoNota) {
        if (notasDisponiveis == null || notasDisponiveis.isEmpty()) {
            buscarNotasDispenser();
        }
        return notasDisponiveis
                .stream()
                .filter(cedula -> cedula.getNota().equals(tipoNota))
                .findFirst();
    }

    public void atualizarRetiraDeCedulas(TipoNota tipoNota, Integer quantidade) throws CedulaIndisponivelException, NumeroDeNotasIndisponivelException {
        Optional<Cedula> cedulaOptional = buscarCedulaDoTipo(tipoNota);

        if (cedulaOptional.isPresent()) {
            cedulaOptional.get().retirarEstoque(quantidade);
        } else {
            throw new CedulaIndisponivelException("Cedula não encontrada!");
        }

    }

    public List<Cedula> buscarNotasEmEstoque() {
        if (notasDisponiveis == null || notasDisponiveis.isEmpty()) {
            buscarNotasDispenser();
        }
        return notasDisponiveis
                .stream()
                .filter(cedula -> cedula.getQuantidadeDisponivel() > 0)
                .collect(Collectors.toList());
    }
}
