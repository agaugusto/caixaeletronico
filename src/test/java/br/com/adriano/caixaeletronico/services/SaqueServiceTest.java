package br.com.adriano.caixaeletronico.services;


import br.com.adriano.caixaeletronico.tipo.TipoNota;
import br.com.adriano.caixaeletronico.dto.CedulaDTO;
import org.junit.Before;
import org.junit.Test;

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
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_100));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 2);
        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.buscarDistribuicaoDeCedulas(280);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaNotaDeDezEUmaDeCinquenta(){
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_100));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.buscarDistribuicaoDeCedulas(110);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarDuasDeVinteEUmaDeCinquenta(){
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_20));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);

        try {
            List<CedulaDTO> retorno = service.buscarDistribuicaoDeCedulas(90);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaDeVinteEUmaDeDez(){
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.buscarDistribuicaoDeCedulas(30);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveRetornarUmaDeCinquentaUmaDeVinteEUmaDeDez(){
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.buscarDistribuicaoDeCedulas(80);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    @Test
    public void deveGerarExceptionQuandoEncontrarValorMenorQueDez(){
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 2);
        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            service.buscarDistribuicaoDeCedulas(285);
            fail("Deve falhar!");
        } catch (Exception e) {
            assert(e.getMessage().equals("Não é permitido valor menor que 10 reais!"));
        }
    }

    @Test
    public void deveRetornarDuasDeCinquentaENoveDeDezQuandoDemiasNotasZeradas(){
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(9, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 0);
        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 0);
        mockCedula(TipoNota.NOTAS_10, 9);
        try {
            List<CedulaDTO> retorno = service.buscarDistribuicaoDeCedulas(190);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Não deve falhar!");
        }
    }

    private CedulaDTO buildNotaDTO(Integer quantidade, TipoNota tipo) {
        CedulaDTO cedulaDTO = new CedulaDTO();
        cedulaDTO.setQuantidade(quantidade);
        cedulaDTO.setTipoNota(tipo);
        return cedulaDTO;
    }

    private List<Cedula> buildCedulasDispenser() {
        List<Cedula> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_100));
        notasDisponiveis.add(new Cedula(7, TipoNota.NOTAS_50));
        notasDisponiveis.add(new Cedula(2, TipoNota.NOTAS_20));
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_10));
        return notasDisponiveis;
    }

    private void mockCedula(TipoNota tipoNota, Integer quantidade){
        when(dispenserService.buscarCedulaDoTipo(tipoNota))
                .thenReturn(java.util.Optional.of(new Cedula(quantidade, tipoNota)));
    }
}
