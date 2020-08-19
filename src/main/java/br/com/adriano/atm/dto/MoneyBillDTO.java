package br.com.adriano.atm.dto;


import br.com.adriano.atm.tipo.MoneyBillsType;
import lombok.*;

@Data
public class MoneyBillDTO {
    private MoneyBillsType moneyBillsType;
    private Integer quantity;
}
