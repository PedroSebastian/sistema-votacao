package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.Encaminhamento;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class EncaminhamentoTest {

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmEncominhamentoComUmaDescricaoEmBranco(){

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setDescricao("");

    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmEncaminhamentoComADescricaoNula(){

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setDescricao(null);

    }

    @Test
    public void deveTerUmVotoNoEncaminhamento(){

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setSomaVoto();
        Assert.assertEquals(1,encaminhamento.pegaQuantidadeVoto());

    }

    @Test
    public void deveTerZeroVotosNoEncaminhamento(){

        Encaminhamento encaminhamento = new Encaminhamento();
        Assert.assertEquals(0,encaminhamento.pegaQuantidadeVoto());

    }

    @Test
    public void deveTerCemVotosNoEncaminhamento(){

        Encaminhamento encaminhamento = new Encaminhamento();

        for (int i = 0; i < 100; i++) {
            encaminhamento.setSomaVoto();
        }

        Assert.assertEquals(100,encaminhamento.pegaQuantidadeVoto());

    }


}
