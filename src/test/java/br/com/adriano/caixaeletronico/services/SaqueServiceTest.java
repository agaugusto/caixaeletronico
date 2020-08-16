package br.com.adriano.caixaeletronico.services;


import br.com.adriano.caixaeletronico.tipo.TipoNota;
import br.com.adriano.caixaeletronico.dto.NotaDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class SaqueServiceTest {

    SaqueService service;
    DispenserService dispenserService;

    @Before
    public void setup(){
        dispenserService = mock(DispenserService.class);
        service = new SaqueService(dispenserService);
    }

    @Test
    public void deveRetornarUmaNotaDeDezUmaDeVinteUmaDeCinquentaEDuasDeCem(){
        List<NotaDTO> notaDTO = new ArrayList<>();
        notaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_100));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_100))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_50))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_20))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_10))
                .thenReturn(2);

        try {
            List<NotaDTO> retorno = service.distribuicaoNotas(280);
            assertArrayEquals(notaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaNotaDeDezEUmaDeCinquenta(){
        List<NotaDTO> notaDTO = new ArrayList<>();
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_100));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_100))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_10))
                .thenReturn(2);

        try {
            List<NotaDTO> retorno = service.distribuicaoNotas(110);
            assertArrayEquals(notaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarDuasDeVinteEUmaDeCinquenta(){
        List<NotaDTO> notaDTO = new ArrayList<>();
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        notaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_20));
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_50))
        .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_20))
                .thenReturn(2);

        try {
            List<NotaDTO> retorno = service.distribuicaoNotas(90);
            assertArrayEquals(notaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaDeVinteEUmaDeDez(){
        List<NotaDTO> notaDTO = new ArrayList<>();
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_20))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_10))
                .thenReturn(2);

        try {
            List<NotaDTO> retorno = service.distribuicaoNotas(30);
            assertArrayEquals(notaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaDeCinquentaUmaDeVinteEUmaDeDez(){
        List<NotaDTO> notaDTO = new ArrayList<>();
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        notaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_50))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_20))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_10))
                .thenReturn(2);

        try {
            List<NotaDTO> retorno = service.distribuicaoNotas(80);
            assertArrayEquals(notaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveGerarExceptionQuandoEncontrarValorMenorQueDez(){
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_100))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_50))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_20))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_10))
                .thenReturn(2);

        try {
            service.distribuicaoNotas(285);
            fail("Deve falhar!");
        } catch (Exception e) {
            assert(e.getMessage().equals("Não é permitido valor menor que 10 reais!"));
        }
    }

    @Test
    public void deveRetornarDuasDeCinquentaENoveDeDezQuandoDemiasNotasZeradas(){
        List<NotaDTO> notaDTO = new ArrayList<>();
        notaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_50));
        notaDTO.add(buildNotaDTO(9, TipoNota.NOTAS_10));
        when(dispenserService.listarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_100))
                .thenReturn(0);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_50))
                .thenReturn(2);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_20))
                .thenReturn(0);

        when(dispenserService.retornaQuantidadeDeNotasDoTipo(TipoNota.NOTAS_10))
                .thenReturn(9);

        try {
            List<NotaDTO> retorno = service.distribuicaoNotas(190);
            assertArrayEquals(notaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    private NotaDTO buildNotaDTO(Integer quantidade, TipoNota tipo) {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setQuantidade(quantidade);
        notaDTO.setTipoNota(tipo);
        return notaDTO;
    }

    private List<Cedula> buildCedulasDispenser() {
        List<Cedula> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_100));
        notasDisponiveis.add(new Cedula(7, TipoNota.NOTAS_50));
        notasDisponiveis.add(new Cedula(2, TipoNota.NOTAS_20));
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_10));
        return notasDisponiveis;
    }
}
