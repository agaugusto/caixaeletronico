package br.com.adriano.atm.model;

import br.com.adriano.atm.error.NumberOfCedulasUnavailableException;
import br.com.adriano.atm.tipo.MoneyBillsType;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class MoneyBill {
    private Integer quantityAvailable;
    private MoneyBillsType moneyBillsType;

    public MoneyBill(Integer quantityAvailable, MoneyBillsType moneyBillsType) {
        this.quantityAvailable = quantityAvailable;
        this.moneyBillsType = moneyBillsType;
    }

    public void withdrawMoney(Integer quantity) throws NumberOfCedulasUnavailableException {
        if (quantityAvailable < quantity) {
            throw new NumberOfCedulasUnavailableException("Número de notas indisponível!");
        }
        quantityAvailable = quantityAvailable - quantity;
    }
}
