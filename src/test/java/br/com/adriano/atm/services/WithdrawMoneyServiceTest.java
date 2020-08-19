package br.com.adriano.atm.services;


import br.com.adriano.atm.error.WithdrawalAmountUnavailableException;
import br.com.adriano.atm.model.MoneyBill;
import br.com.adriano.atm.tipo.MoneyBillsType;
import br.com.adriano.atm.dto.MoneyBillDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WithdrawMoneyServiceTest {

    WithdrawMoneyService service;
    CashDispenserService dispenserService;

    @Before
    public void setup() {
        dispenserService = mock(CashDispenserService.class);
        service = new WithdrawMoneyService(dispenserService);
    }

    @Test
    public void deveRetornarUmaNotaDeDezUmaDeVinteUmaDeCinquentaEDuasDeCemComSucesso() {
        List<MoneyBillDTO> moneyBillDTO = new ArrayList<>();
        moneyBillDTO.add(buildNotaDTO(2, MoneyBillsType.NOTAS_100));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_50));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_20));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_10));
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_100, 2);
        mockCedula(MoneyBillsType.NOTAS_50, 2);
        mockCedula(MoneyBillsType.NOTAS_20, 2);
        mockCedula(MoneyBillsType.NOTAS_10, 2);

        try {
            List<MoneyBillDTO> retorno = service.moneyBill(280);
            assertArrayEquals(moneyBillDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarUmaNotaDeDezEUmaDeCinquentaComSucesso() {
        List<MoneyBillDTO> moneyBillDTO = new ArrayList<>();
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_100));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_10));
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_100, 2);
        mockCedula(MoneyBillsType.NOTAS_10, 2);

        try {
            List<MoneyBillDTO> retorno = service.moneyBill(110);
            assertArrayEquals(moneyBillDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarDuasDeVinteEUmaDeCinquentaComSucesso() {
        List<MoneyBillDTO> moneyBillDTO = new ArrayList<>();
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_50));
        moneyBillDTO.add(buildNotaDTO(2, MoneyBillsType.NOTAS_20));
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_50, 2);
        mockCedula(MoneyBillsType.NOTAS_20, 2);

        try {
            List<MoneyBillDTO> retorno = service.moneyBill(90);
            assertArrayEquals(moneyBillDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarUmaDeVinteEUmaDeDezComSucesso() {
        List<MoneyBillDTO> moneyBillDTO = new ArrayList<>();
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_20));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_10));
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_20, 2);
        mockCedula(MoneyBillsType.NOTAS_10, 2);

        try {
            List<MoneyBillDTO> retorno = service.moneyBill(30);
            assertArrayEquals(moneyBillDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarUmaDeCinquentaUmaDeVinteEUmaDeDezComSucesso() {
        List<MoneyBillDTO> moneyBillDTO = new ArrayList<>();
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_50));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_20));
        moneyBillDTO.add(buildNotaDTO(1, MoneyBillsType.NOTAS_10));
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_50, 2);
        mockCedula(MoneyBillsType.NOTAS_20, 2);
        mockCedula(MoneyBillsType.NOTAS_10, 2);

        try {
            List<MoneyBillDTO> retorno = service.moneyBill(80);
            assertArrayEquals(moneyBillDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveGerarExceptionQuandoEncontrarValorMenorQueDez() {
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_100, 2);
        mockCedula(MoneyBillsType.NOTAS_50, 2);
        mockCedula(MoneyBillsType.NOTAS_20, 2);
        mockCedula(MoneyBillsType.NOTAS_10, 2);

        try {
            service.moneyBill(285);
            fail("Deve retornar ValorIndisponivelException!");
        } catch (WithdrawalAmountUnavailableException e) {
            assertEquals("Não é permitido Cedulas menor que 10!", e.getMessage());
        } catch (Exception e) {
            fail("Deve retornar ValorIndisponivelException!");
        }
    }

    @Test
    public void deveRetornarDuasDeCinquentaENoveDeDezQuandoDemiasNotasZeradasComSucesso() {
        List<MoneyBillDTO> moneyBillDTO = new ArrayList<>();
        moneyBillDTO.add(buildNotaDTO(2, MoneyBillsType.NOTAS_50));
        moneyBillDTO.add(buildNotaDTO(9, MoneyBillsType.NOTAS_10));
        when(dispenserService.findCashDispenser())
                .thenReturn(buildMoneyBillDispenser());

        mockCedula(MoneyBillsType.NOTAS_100, 0);
        mockCedula(MoneyBillsType.NOTAS_50, 2);
        mockCedula(MoneyBillsType.NOTAS_20, 0);
        mockCedula(MoneyBillsType.NOTAS_10, 9);
        try {
            List<MoneyBillDTO> retorno = service.moneyBill(190);
            assertArrayEquals(moneyBillDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    private MoneyBillDTO buildNotaDTO(Integer quantidade, MoneyBillsType tipo) {
        MoneyBillDTO moneyBillDTO = new MoneyBillDTO();
        moneyBillDTO.setQuantity(quantidade);
        moneyBillDTO.setMoneyBillsType(tipo);
        return moneyBillDTO;
    }

    private List<MoneyBill> buildMoneyBillDispenser() {
        List<MoneyBill> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new MoneyBill(5, MoneyBillsType.NOTAS_100));
        notasDisponiveis.add(new MoneyBill(7, MoneyBillsType.NOTAS_50));
        notasDisponiveis.add(new MoneyBill(2, MoneyBillsType.NOTAS_20));
        notasDisponiveis.add(new MoneyBill(5, MoneyBillsType.NOTAS_10));
        return notasDisponiveis;
    }

    private void mockCedula(MoneyBillsType moneyBillsType, Integer quantidade) {
        when(dispenserService.findMoneyBillsType(moneyBillsType))
                .thenReturn(java.util.Optional.of(new MoneyBill(quantidade, moneyBillsType)));
    }
}
