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
        notasDisponiveis.add(new Notas(5, TipoNotas.NOTAS_10));
        notasDisponiveis.add(new Notas(7, TipoNotas.NOTAS_50));
        notasDisponiveis.add(new Notas(5, TipoNotas.NOTAS_100));
        notasDisponiveis.add(new Notas(2, TipoNotas.NOTAS_20));
    }

    public void incluirNota(Notas notas){
        if(!notasDisponiveis.contains(notas)) {
            notasDisponiveis.add(notas);
        }else{
            notasDisponiveis.get(notasDisponiveis.indexOf(notas))
                    .setQuantidadeDisponivel(notas.getQuantidadeDisponivel());
        }
    }

    public Notas retornaNota(TipoNotas tipo){

        return notasDisponiveis
                .stream()
                .filter(notas -> notas.getNota().equals(tipo))
                .findAny()
                .get();
    }
}
