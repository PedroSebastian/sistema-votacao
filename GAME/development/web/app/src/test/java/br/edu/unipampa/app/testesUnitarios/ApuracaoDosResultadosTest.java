package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ApuracaoDosResultadosTest {

    @Test
    public void deveExbirVotoDeCadaMembroQueVotou(){

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

        //ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(urna.getVotosParaContagem());

      //  Assert.assertEquals("membro1",apuracaoDaVotacao.exibeVotoMembros().get(0).getVotante().getNome());
        //Assert.assertEquals("membro2",apuracaoDaVotacao.exibeVotoMembros().get(1).getVotante().getNome());
        //Assert.assertEquals(2,apuracaoDaVotacao.exibeVotoMembros().size());

    }

    @Test
    public void deveRetornarUmEmpateEmUmaVotatacaoQueReceberamUmVotoCadaEncaminhamento(){

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
        urna.recebe(voto);
        urna.recebe(voto2);

        Votacao votacao = new Votacao(urna,itemDePauta);

       // ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(urna.getVotosParaContagem());

       // Assert.assertTrue(apuracaoDaVotacao.verificaEmpate());
    }

    @Test
    public void deveRetornarFalseParaUmaVotacaoQueNaoPossuiEmpate(){

        Membro membro1 = new Membro();
        membro1.setNome("membro1");

        Membro membro2 = new Membro();
        membro2.setNome("membro2");

        Encaminhamento encaminhamento1 = new Encaminhamento();
        encaminhamento1.setDescricao("encaminhamento1");

        Encaminhamento encaminhamento2 = new Encaminhamento();
        encaminhamento2.setDescricao("encaminhamento2");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento1);
        itemDePauta.adiciona(encaminhamento2);

        Urna urna = new Urna();

        Voto voto = new Voto(membro1,encaminhamento1);
        Voto voto2 = new Voto(membro2,encaminhamento2);

        urna.recebe(voto);
        urna.recebe(voto2);

        Votacao votacao = new Votacao(urna,itemDePauta);

       // ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(urna.getVotosParaContagem());

       // Assert.assertFalse(apuracaoDaVotacao.verificaEmpate());

    }

    @Test
    public void deveApresentarCemPorCentoDosVotos(){

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

       // ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(votacao.getUrna().getVotosParaContagem());

        //System.out.println(apuracaoDaVotacao.exibeVotoMembros().get(0).getVotante().getNome());
        //System.out.println(apuracaoDaVotacao.exibeVotoMembros().get(1).getVotante().getNome());
        //System.out.println("-----------------------------");
        //apuracaoDaVotacao.realizaApuracao();
        //System.out.println("0-------------------------");
        //apuracaoDaVotacao.porcentagemDeVotos();

    }

    @Test
    public void deveApresentarCinquantaPorCentoDosVotos(){

    }

    @Test
    public void deveApresentarZeroPorCentoDosVotos(){

    }

}
