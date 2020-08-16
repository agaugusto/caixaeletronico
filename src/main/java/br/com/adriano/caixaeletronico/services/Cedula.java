package br.com.adriano.caixaeletronico.services;

import br.com.adriano.caixaeletronico.tipo.TipoNota;

import java.util.Objects;

public class Cedula {
    private Integer quantidadeDisponivel;
    private TipoNota nota;

    public Cedula(Integer quantidadeDisponivel, TipoNota nota) {
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.nota = nota;
    }

    public void retirarEstoque(Integer quantidade) throws Exception {
        if(quantidadeDisponivel >= quantidade){
            quantidadeDisponivel=quantidadeDisponivel-quantidade;
        }else {
            throw new Exception("Erro não previsto, tente outra vez!");
        }
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cedula notas = (Cedula) o;
        return nota == notas.nota;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota);
    }

    public TipoNota getNota() {
        return nota;
    }
}
