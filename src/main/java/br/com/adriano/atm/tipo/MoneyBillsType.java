package br.com.adriano.atm.tipo;

import lombok.Getter;

@Getter
public enum MoneyBillsType {
    NOTAS_10(10),
    NOTAS_20(20),
    NOTAS_50(50),
    NOTAS_100(100);
    private Integer value;

    private MoneyBillsType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
