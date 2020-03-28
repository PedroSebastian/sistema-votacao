package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.StatusParecer;
import br.edu.unipampa.app.domain.StatusVotacao;
import org.junit.Assert;
import org.junit.Test;

/**
 * Amanda Lopes
 */
public class StatusParecerTest {

    @Test
    public void testaStatusFAVORAVEL(){

        StatusParecer s = StatusParecer.FAVORAVEL;

        Assert.assertEquals(StatusParecer.valueOf("FAVORAVEL"), s );
    }

    @Test
    public void testaStatusCONTRARIO(){

        StatusParecer s = StatusParecer.CONTRARIO;

        Assert.assertEquals(StatusParecer.valueOf("CONTRARIO"), s );
    }

    @Test
    public void testaStatusSEMPARECER(){
        StatusParecer s = StatusParecer.SEMPARECER;


        Assert.assertEquals(StatusParecer.valueOf("SEMPARECER"), s );
    }
}
