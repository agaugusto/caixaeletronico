package br.com.adriano.caixaeletronico.service;

import br.com.adriano.caixaeletronico.controller.TipoNotas;
import org.junit.Before;
import org.junit.Test;

public class DispenserServiceTest {

    DispenserService service;

    @Before
    public void init(){
        service = new DispenserService();
    }

    @Test
    public void deveIncluirNotaComSucesso(){

        Notas notas = new Notas(5, TipoNotas.NOTAS_10);
        service.incluirNota(notas);

        notas = new Notas(15, TipoNotas.NOTAS_50);
        service.incluirNota(notas);

        notas = new Notas(15, TipoNotas.NOTAS_100);
        service.incluirNota(notas);

        notas = new Notas(15, TipoNotas.NOTAS_20);
        service.incluirNota(notas);

        notas = new Notas(25, TipoNotas.NOTAS_100);
        service.incluirNota(notas);


    }
}
