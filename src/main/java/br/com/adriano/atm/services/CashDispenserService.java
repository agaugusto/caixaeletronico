package br.com.adriano.atm.services;

import br.com.adriano.atm.error.BallotUnavailableException;
import br.com.adriano.atm.error.NumberOfCedulasUnavailableException;
import br.com.adriano.atm.model.MoneyBill;
import br.com.adriano.atm.tipo.MoneyBillsType;
import br.com.adriano.atm.repository.DispenserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashDispenserService {

    private List<MoneyBill> cashAccountAvailable;
    private final DispenserRepository dispenserRepository;

    public CashDispenserService(DispenserRepository dispenserRepository) {
        this.dispenserRepository = dispenserRepository;
    }

    private void loadCashDispenser() {
        cashAccountAvailable = dispenserRepository.loadCashDispenser();
    }

    public Optional<MoneyBill> findMoneyBillsType(MoneyBillsType moneyBillsType) {
        if (cashAccountAvailable == null || cashAccountAvailable.isEmpty()) {
            loadCashDispenser();
        }
        return cashAccountAvailable
                .stream()
                .filter(moneyBill -> moneyBill.getMoneyBillsType().equals(moneyBillsType))
                .findFirst();
    }

    public void updateWithdrawMoney(MoneyBillsType moneyBillsType, Integer quantidade) throws BallotUnavailableException, NumberOfCedulasUnavailableException {
        Optional<MoneyBill> moneyBillOptional = findMoneyBillsType(moneyBillsType);

        if (moneyBillOptional.isPresent()) {
            moneyBillOptional.get().withdrawMoney(quantidade);
        } else {
            throw new BallotUnavailableException("Cedula não encontrada!");
        }

    }

    public List<MoneyBill> findCashDispenser() {
        if (cashAccountAvailable == null || cashAccountAvailable.isEmpty()) {
            loadCashDispenser();
        }
        return cashAccountAvailable
                .stream()
                .filter(cedula -> cedula.getQuantityAvailable() > 0)
                .collect(Collectors.toList());
    }
}
