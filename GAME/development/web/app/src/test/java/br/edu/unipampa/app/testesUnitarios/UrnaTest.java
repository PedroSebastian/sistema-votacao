package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.*;
import org.junit.Assert;
import org.junit.Test;

public class UrnaTest {

    @Test
    public void deveExibirAListaDeVotosParaAContagem(){

        Encaminhamento item1 = new Encaminhamento();
        item1.setDescricao("item1");

        Encaminhamento item2 = new Encaminhamento();
        item1.setDescricao("item2");

        Encaminhamento item3 = new Encaminhamento();
        item1.setDescricao("item3");

        Membro pessoa1 = new Membro();
        pessoa1.setNome("pessoa1");

        Membro pessoa2 = new Membro();
        pessoa2.setNome("pessoa2");

        Voto voto1 = new Voto(pessoa1,item1);
        Voto voto2 = new Voto(pessoa2,item3);

        Urna urna = new Urna();
        urna.recebe(voto1);
        urna.recebe(voto2);

        Assert.assertEquals(voto1, urna.getVotosParaContagem().get(0));
        Assert.assertEquals(voto2, urna.getVotosParaContagem().get(1));
    }

    @Test
    public void oTamanhoDaListaDeVotosDeveSerAQuantidadeDeVotos(){

        Encaminhamento item1 = new Encaminhamento();
        item1.setDescricao("item1");

        Encaminhamento item2 = new Encaminhamento();
        item1.setDescricao("item2");

        Encaminhamento item3 = new Encaminhamento();
        item1.setDescricao("item3");

        Membro pessoa1 = new Membro();
        pessoa1.setNome("pessoa1");

        Membro pessoa2 = new Membro();
        pessoa2.setNome("pessoa2");

        Voto voto1 = new Voto(pessoa1,item1);
        Voto voto2 = new Voto(pessoa2,item3);

        Urna urna = new Urna();
        urna.recebe(voto1);
        urna.recebe(voto2);

        Assert.assertEquals(2,urna.getVotosParaContagem().size());
        Assert.assertFalse(urna.getVotosParaContagem().size() > 2 || urna.getVotosParaContagem().size() < 2);
    }

    /**
     * Testa se uma exceção é lançada ao tentar setar uma lista de votos para contagem nula.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void aListaDeVotosParaContagemNaoPodeSerNula(){
        Urna urna = new Urna();

        urna.setVotosParaContagem(null);
    }
}
