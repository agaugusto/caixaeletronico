package br.com.adriano.caixaeletronico.services;

import br.com.adriano.caixaeletronico.error.CedulaIndisponivelException;
import br.com.adriano.caixaeletronico.error.NumeroDeNotasIndisponivelException;
import br.com.adriano.caixaeletronico.repository.DispenserRepository;
import br.com.adriano.caixaeletronico.tipo.TipoNota;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DispenserServiceTest {

    private DispenserService service;
    DispenserRepository dispenserRepository;


    @Before

    public void setup(){
        dispenserRepository = mock(DispenserRepository.class);
        when(dispenserRepository.buscarNotasDispenser())
                .thenReturn(buildCedulasDispenser());
        service = new DispenserService(dispenserRepository);
    }

    @Test
    public void deveRetornarCedulasDoTipo100(){

        Optional<Cedula> retorno = service.buscarCedulaDoTipo(TipoNota.NOTAS_100);

        assertEquals(Optional.of(5), Optional.ofNullable(retorno.get().getQuantidadeDisponivel()));

    }

    @Test
    public void deveRetornarCedulaVaziaQuandoNaoEncontrar(){

        Optional<Cedula> retorno = service.buscarCedulaDoTipo(TipoNota.NOTAS_10);

        assertFalse(retorno.isPresent());

    }

    @Test
    public void deveAtualizarRetiradaDeCedulasComSucesso(){

        try {
            service.atualizarRetiraDeCedulas(TipoNota.NOTAS_100, 3);
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void naoDeveAtualizarRetiradaDeCedulasQuandoRetiradaMaiorQueDisponivel(){

        try {
            service.atualizarRetiraDeCedulas(TipoNota.NOTAS_100, 9);
        } catch (NumeroDeNotasIndisponivelException e) {
            assertEquals("Número de notas indisponível!", e.getMessage());
        } catch (Exception e){
            fail("Deve lançar NumeroDeNotasIndisponivelException!");
        }
    }

    @Test
    public void naoDeveAtualizarRetiradaDeCedulasQuandoCedulaNaoEncontrada(){

        try {
            service.atualizarRetiraDeCedulas(TipoNota.NOTAS_10, 9);
        } catch (CedulaIndisponivelException e) {
            assertEquals("Cedula não encontrada!", e.getMessage());
        } catch (Exception e){
            fail("Deve lançar CedulaIndisponivelException!");
        }
    }

    @Test
    public void deveBuscarNotasDoDispenser() {

        List<Cedula> retorno = service.buscarNotasEmEstoque();
        assertEquals(buildCedulasDispenser(), retorno);
    }

    private List<Cedula> buildCedulasDispenser() {
        List<Cedula> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_100));
        notasDisponiveis.add(new Cedula(7, TipoNota.NOTAS_50));
        notasDisponiveis.add(new Cedula(2, TipoNota.NOTAS_20));

        return notasDisponiveis;
    }

}
