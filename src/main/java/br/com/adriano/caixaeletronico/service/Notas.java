package br.com.adriano.caixaeletronico.service;

import br.com.adriano.caixaeletronico.controller.TipoNotas;

import java.util.Objects;

public class Notas {
    private Integer quantidadeDisponivel;
    private TipoNotas nota;

    public Notas(Integer quantidadeDisponivel, TipoNotas nota) {
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.nota = nota;
    }


    public void retirada(Integer quantidade) throws Exception {
        if(quantidadeDisponivel >= quantidade){
            quantidadeDisponivel=quantidadeDisponivel-quantidade;
        }else {
            throw new Exception("Erro não previsto, tente outra vez!");
        }
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notas notas = (Notas) o;
        return nota == notas.nota;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota);
    }

    public TipoNotas getNota() {
        return nota;
    }
}
