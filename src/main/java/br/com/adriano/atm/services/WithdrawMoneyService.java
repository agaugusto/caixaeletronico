package br.com.adriano.atm.services;

import br.com.adriano.atm.dto.MoneyBillDTO;
import br.com.adriano.atm.error.BallotUnavailableException;
import br.com.adriano.atm.error.NumberOfCedulasUnavailableException;
import br.com.adriano.atm.error.WithdrawalAmountUnavailableException;
import br.com.adriano.atm.model.MoneyBill;
import br.com.adriano.atm.tipo.MoneyBillsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawMoneyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawMoneyService.class);
    private final CashDispenserService cashDispenserService;

    public WithdrawMoneyService(CashDispenserService cashDispenserService) {
        this.cashDispenserService = cashDispenserService;
    }

    public List<MoneyBillDTO> moneyBill(Integer valor) throws WithdrawalAmountUnavailableException, NumberOfCedulasUnavailableException {

        List<MoneyBillDTO> moneyBillDTOSList = findDistributionOfMoneyBill(valor);
        updateCashDispenser(moneyBillDTOSList);
        return moneyBillDTOSList;
    }

    private List<MoneyBillDTO> findDistributionOfMoneyBill(Integer value) throws NumberOfCedulasUnavailableException, WithdrawalAmountUnavailableException {
        List<MoneyBillDTO> listMoneyBills = new ArrayList<>();
        Integer amount = value;
        for (MoneyBill moneyBill : cashDispenserService.findCashDispenser()) {
            Optional<MoneyBillDTO> moneyBillDTO = findQuantityMoneyBillsType(moneyBill.getMoneyBillsType(), amount);
            if (moneyBillDTO.isPresent()) {
                listMoneyBills.add(moneyBillDTO.get());
                amount = amount - (moneyBill.getMoneyBillsType().getValue() * moneyBillDTO.get().getQuantity());
            }
        }
        if (amount >= 10) {
            throw new NumberOfCedulasUnavailableException("Valor solicitado indisponível!");
        } else if (amount != 0) {
            throw new WithdrawalAmountUnavailableException("Não é permitido Cedulas menor que 10!");
        }
        return listMoneyBills;
    }

    private Optional<MoneyBillDTO> findQuantityMoneyBillsType(MoneyBillsType moneyBillsType, Integer valorRestante) {
        if (valorRestante >= moneyBillsType.getValue()) {
            Integer quantity = valorRestante / moneyBillsType.getValue();
            Optional<MoneyBill> moneyBill = cashDispenserService.findMoneyBillsType(moneyBillsType);
            if (moneyBill.isPresent()) {
                if (quantity > moneyBill.get().getQuantityAvailable()) {
                    quantity = moneyBill.get().getQuantityAvailable();
                }
                if (quantity > 0) {
                    return Optional.of(buildMoneyBillDTO(quantity, moneyBillsType));
                }
            }
        }
        return Optional.empty();
    }

    private void updateCashDispenser(List<MoneyBillDTO> moneyBillDTOList) {
        moneyBillDTOList
                .stream()
                .forEach(moneyBillDTO -> {
                    try {
                        cashDispenserService.updateWithdrawMoney(moneyBillDTO.getMoneyBillsType(), moneyBillDTO.getQuantity());
                    } catch (BallotUnavailableException e) {
                        LOGGER.error(e.getMessage());
                    } catch (NumberOfCedulasUnavailableException e) {
                        LOGGER.error(e.getMessage());
                    }
                });
    }

    private MoneyBillDTO buildMoneyBillDTO(Integer quantity, MoneyBillsType tipo) {
        MoneyBillDTO moneyBillDTO = new MoneyBillDTO();
        moneyBillDTO.setQuantity(quantity);
        moneyBillDTO.setMoneyBillsType(tipo);
        return moneyBillDTO;
    }
}
