package br.com.adriano.caixaeletronico.dto.response;


import br.com.adriano.caixaeletronico.controller.TipoNotas;

import java.util.Objects;

public class NotasDTO {

    private TipoNotas nota;
    private Integer quantidade;

    public TipoNotas getNota() {
        return nota;
    }

    public void setNota(TipoNotas nota) {
        this.nota = nota;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotasDTO that = (NotasDTO) o;
        return quantidade == that.quantidade &&
                nota == that.nota;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, quantidade);
    }
}
