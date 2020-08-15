package br.com.adriano.caixaeletronico.service;


import br.com.adriano.caixaeletronico.controller.TipoNotas;
import br.com.adriano.caixaeletronico.dto.response.NotasDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class NotasServiceTest {

    NotasService service;
    DispenserService dispenserService;

    @Before
    public void setup(){
        dispenserService = Mockito.mock(DispenserService.class);
        service = new NotasService(dispenserService);
    }

    @Test
    public void deveRetornarUmaNotaDeDezUmaDeVinteUmaDeCinquentaEDuasDeCem(){
        List<NotasDTO> notasDTO = new ArrayList<>();
        notasDTO.add(buildNotaDTO(2, TipoNotas.NOTAS_100));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_50));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_20));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_10));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_100))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_100));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_50))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_50));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_20))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_20));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_10))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_10));

        try {
            List<NotasDTO> retorno = service.distribuicaoNotas(280);
            assertArrayEquals(notasDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaNotaDeDezEUmaDeCinquenta(){
        List<NotasDTO> notasDTO = new ArrayList<>();
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_100));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_10));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_100))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_100));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_10))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_10));

        try {
            List<NotasDTO> retorno = service.distribuicaoNotas(110);
            assertArrayEquals(notasDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarDuasDeVinteEUmaDeCinquenta(){
        List<NotasDTO> notasDTO = new ArrayList<>();
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_50));
        notasDTO.add(buildNotaDTO(2, TipoNotas.NOTAS_20));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_50))
        .thenReturn(new Notas(2, TipoNotas.NOTAS_50));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_20))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_20));

        try {
            List<NotasDTO> retorno = service.distribuicaoNotas(90);
            assertArrayEquals(notasDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaDeVinteEUmaDeDez(){
        List<NotasDTO> notasDTO = new ArrayList<>();
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_20));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_10));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_20))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_20));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_10))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_10));

        try {
            List<NotasDTO> retorno = service.distribuicaoNotas(30);
            assertArrayEquals(notasDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaDeCinquentaUmaDeVinteEUmaDeDez(){
        List<NotasDTO> notasDTO = new ArrayList<>();
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_50));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_20));
        notasDTO.add(buildNotaDTO(1, TipoNotas.NOTAS_10));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_50))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_50));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_20))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_20));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_10))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_10));

        try {
            List<NotasDTO> retorno = service.distribuicaoNotas(80);
            assertArrayEquals(notasDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveGerarExceptionQuandoEncontrarValorMenorQueDez(){
        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_100))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_100));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_50))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_50));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_20))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_20));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_10))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_10));

        try {
            service.distribuicaoNotas(285);
            fail("Deve falhar!");
        } catch (Exception e) {
            assert(e.getMessage().equals("Não é permitido valor menor que 10 reais!"));
        }
    }

    @Test
    public void deveRetornarDuasDeCinquentaENoveDeDezQuandoDemiasNotasZeradas(){
        List<NotasDTO> notasDTO = new ArrayList<>();
        notasDTO.add(buildNotaDTO(2, TipoNotas.NOTAS_50));
        notasDTO.add(buildNotaDTO(9, TipoNotas.NOTAS_10));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_100))
                .thenReturn(new Notas(0, TipoNotas.NOTAS_100));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_50))
                .thenReturn(new Notas(2, TipoNotas.NOTAS_50));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_20))
                .thenReturn(new Notas(0, TipoNotas.NOTAS_20));

        Mockito.when(dispenserService.retornaNota(TipoNotas.NOTAS_10))
                .thenReturn(new Notas(9, TipoNotas.NOTAS_10));

        try {
            List<NotasDTO> retorno = service.distribuicaoNotas(190);
            assertArrayEquals(notasDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    private NotasDTO buildNotaDTO(Integer quantidade, TipoNotas tipo) {
        NotasDTO notasDTO = new NotasDTO();
        notasDTO.setQuantidade(quantidade);
        notasDTO.setNota(tipo);
        return notasDTO;
    }
}
