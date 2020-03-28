package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
/**
 * Created by Amanda Lopes
 */
public class ParecerTest {

    /**
     * Teste que não  cria o parecer quando o campo Descrição for nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveaceitarParecercomCampoDescricaoNulo(){
        Parecer parecer = new Parecer();

        parecer.setDescricaoParecer(null);
        assertEquals(null ,parecer.getDescricaoParecer());

    }

    /**
     * Teste que cria um parecer completo
     */
    @Test
    public void criaUmParecerCompleto(){
        Parecer parecer = new Parecer();
        parecer.setDescricaoParecer("Parecer Do Item de Pauta já Votado");
        StatusParecer s = StatusParecer.CONTRARIO;

        Assert.assertEquals(StatusParecer.valueOf("CONTRARIO"), s );
        assertEquals("Parecer Do Item de Pauta já Votado" ,parecer.getDescricaoParecer());
    }







}
