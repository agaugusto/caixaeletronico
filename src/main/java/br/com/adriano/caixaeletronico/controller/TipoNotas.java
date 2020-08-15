package br.com.adriano.caixaeletronico.controller;

public enum TipoNotas {
    NOTAS_10(10),
    NOTAS_20(20),
    NOTAS_50(50),
    NOTAS_100(100);
    private Integer value;

    private TipoNotas(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }


}
