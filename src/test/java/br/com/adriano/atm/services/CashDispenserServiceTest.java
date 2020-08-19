package br.com.adriano.atm.services;

import br.com.adriano.atm.error.BallotUnavailableException;
import br.com.adriano.atm.error.NumberOfCedulasUnavailableException;
import br.com.adriano.atm.model.MoneyBill;
import br.com.adriano.atm.repository.DispenserRepository;
import br.com.adriano.atm.tipo.MoneyBillsType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CashDispenserServiceTest {

    private CashDispenserService service;
    DispenserRepository dispenserRepository;


    @Before

    public void setup() {
        dispenserRepository = mock(DispenserRepository.class);
        when(dispenserRepository.loadCashDispenser())
                .thenReturn(buildCedulasDispenser());
        service = new CashDispenserService(dispenserRepository);
    }

    @Test
    public void deveRetornarCedulasDoTipo100ComSucesso() {

        Optional<MoneyBill> retorno = service.findMoneyBillsType(MoneyBillsType.NOTAS_100);

        assertEquals(Optional.of(5), Optional.ofNullable(retorno.get().getQuantityAvailable()));

    }

    @Test
    public void deveRetornarCedulaVaziaQuandoNaoEncontrar() {

        Optional<MoneyBill> retorno = service.findMoneyBillsType(MoneyBillsType.NOTAS_10);

        assertFalse(retorno.isPresent());

    }

    @Test
    public void deveAtualizarRetiradaDeCedulasComSucesso() {

        try {
            service.updateWithdrawMoney(MoneyBillsType.NOTAS_100, 3);
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveLnacarExceptionAoAtualizarRetiradaDeCedulasQuandoRetiradaMaiorQueDisponivel() {

        try {
            service.updateWithdrawMoney(MoneyBillsType.NOTAS_100, 9);
        } catch (NumberOfCedulasUnavailableException e) {
            assertEquals("Número de notas indisponível!", e.getMessage());
        } catch (Exception e) {
            fail("Deve lançar NumeroDeNotasIndisponivelException!");
        }
    }

    @Test
    public void deveLancarExceptionAoAtualizarRetiradaDeCedulasQuandoCedulaNaoEncontrada() {

        try {
            service.updateWithdrawMoney(MoneyBillsType.NOTAS_10, 9);
        } catch (BallotUnavailableException e) {
            assertEquals("Cedula não encontrada!", e.getMessage());
        } catch (Exception e) {
            fail("Deve lançar CedulaIndisponivelException!");
        }
    }

    @Test
    public void deveBuscarNotasDoDispenserComSucesso() {

        List<MoneyBill> retorno = service.findCashDispenser();
        assertEquals(buildCedulasDispenser(), retorno);
    }

    private List<MoneyBill> buildCedulasDispenser() {
        List<MoneyBill> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new MoneyBill(5, MoneyBillsType.NOTAS_100));
        notasDisponiveis.add(new MoneyBill(7, MoneyBillsType.NOTAS_50));
        notasDisponiveis.add(new MoneyBill(2, MoneyBillsType.NOTAS_20));

        return notasDisponiveis;
    }

}
