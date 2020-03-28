package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.*;
import org.junit.Assert;
import org.junit.Test;

public class VotacaoTest {

    @Test
    public void aVotacaoDeveIniciarFechada() {

        Votacao votacao = new Votacao();
        Assert.assertEquals("FECHADA",votacao.getStatus().toString());

    }

    @Test
    public void deveRegistraUmVoto(){

        Membro membro1 = new Membro();
        membro1.setNome("membro1");

        Encaminhamento encaminhamento1 = new Encaminhamento();
        encaminhamento1.setDescricao("encaminhamento1");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento1);

        Urna urna = new Urna();

        Voto voto = new Voto(membro1,encaminhamento1);

        Votacao votacao = new Votacao(urna,itemDePauta);

        votacao.registraVotoNaUrna(voto);

        Assert.assertEquals(1, urna.getVotosParaContagem().size());

    }

    @Test
    public void deveRegistrarDoisVotos(){

        Membro membro1 = new Membro();
        membro1.setNome("membro1");

        Membro membro2 = new Membro();
        membro2.setNome("membro2");

        Encaminhamento encaminhamento1 = new Encaminhamento();
        encaminhamento1.setDescricao("encaminhamento1");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento1);

        Urna urna = new Urna();

        Voto voto = new Voto(membro1,encaminhamento1);
        Voto voto2 = new Voto(membro2,encaminhamento1);

        Votacao votacao = new Votacao(urna,itemDePauta);

        votacao.registraVotoNaUrna(voto);
        votacao.registraVotoNaUrna(voto2);

//        ApuracaoDaVotacao p = new ApuracaoDaVotacao(urna.getVotosParaContagem());
//        p.realizaApuracao();

        //Assert.assertEquals(2, urna.getVotosParaContagem().size());

    }

    @Test
    public void naoDeveTerRegistroNaVotacao(){

        Membro membro1 = new Membro();
        membro1.setNome("membro1");

        Membro membro2 = new Membro();
        membro2.setNome("membro2");

        Encaminhamento encaminhamento1 = new Encaminhamento();
        encaminhamento1.setDescricao("encaminhamento1");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento1);

        Urna urna = new Urna();

        Voto voto = new Voto(membro1,encaminhamento1);
        Voto voto2 = new Voto(membro2,encaminhamento1);

        Votacao votacao = new Votacao(urna,itemDePauta);

        Assert.assertEquals(null, urna.getVotosParaContagem());

    }

    /**
     * Testa se uma exceção é lançada quando a urna é nula.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirAUrnaNula(){
        Votacao votacao = new Votacao();
        votacao.setUrna(null);
    }

    /**
     * Testa se uma exceção é lançada quando o item de pauta é nulo.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirOItemDePautaNulo(){
        Votacao votacao = new Votacao();
        votacao.setItemDePauta(null);
    }

    /**
     * Testa se uma exceção é lançada quando o item de pauta é nulo.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirOResultadoDaVotacaoNulo(){
        Votacao votacao = new Votacao();
        votacao.setResultado(null);
    }


}
