package br.com.adriano.atm.repository;

import br.com.adriano.atm.tipo.MoneyBillsType;
import br.com.adriano.atm.model.MoneyBill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DispenserRepository {
    private List<MoneyBill> moneyBillAvailable;

    public DispenserRepository() {
        moneyBillAvailable = new ArrayList<>();
        moneyBillAvailable.add(new MoneyBill(5, MoneyBillsType.NOTAS_100));
        moneyBillAvailable.add(new MoneyBill(7, MoneyBillsType.NOTAS_50));
        moneyBillAvailable.add(new MoneyBill(2, MoneyBillsType.NOTAS_20));
        moneyBillAvailable.add(new MoneyBill(5, MoneyBillsType.NOTAS_10));
    }

    public List<MoneyBill> loadCashDispenser() {
        return moneyBillAvailable;
    }
}
