package br.com.adriano.caixaeletronico.service;

import br.com.adriano.caixaeletronico.controller.TipoNotas;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispenserService {

    private List<Notas> notasDisponiveis;

    public DispenserService() {
        notasDisponiveis = new ArrayList<>();
    }

    public void incluirNota(Notas notas){
        if(!notasDisponiveis.contains(notas)) {
            notasDisponiveis.add(notas);
        }else{
            notasDisponiveis.get(notasDisponiveis.indexOf(notas)).setQuantidadeDisponivel(notas.getQuantidadeDisponivel());
        }
    }

    public Notas retornaNota(TipoNotas tipo){

        return notasDisponiveis
                .stream()
                .filter(notas -> notas.equals(tipo))
                .findFirst()
                .get();
    }
}
