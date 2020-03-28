package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ResultadosDoEncaminhamentoTest {

    /**
     * Testa se uma exceção é lançada ao tentar setar um encaminhamento nulo.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = IllegalArgumentException.class)
    public void oEncaminhamentoNaoPodeSerNulo(){
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();

        resultadosDoEncaminhamento.setEncaminhamento(null);
    }

    /**
     * Testa se uma exceção é lançada ao tentar setar uma lista de votantes nula.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void aListaDeVotantesNaoPodeSerNula(){
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();

        resultadosDoEncaminhamento.setVotantes(null);
    }

    /**
     * Testa se é possível adicionar membros votantes na lista.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveAdicionarVotantesNaLista() {
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();

        Membro membro1 = new Membro();

        Membro membro2 = new Membro();

        List<Membro> membros = new ArrayList<>();
        membros.add(membro1);
        membros.add(membro2);

        resultadosDoEncaminhamento.setVotantes(membros);

        Assert.assertEquals(2, membros.size());
    }

    /**
     * Testa se é possível remover reuniões da lista.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveRemoverVotantesDaLista() {
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();

        Membro membro1 = new Membro();

        Membro membro2 = new Membro();

        List<Membro> membros = new ArrayList<>();
        membros.add(membro1);
        membros.remove(membro1);
        membros.add(membro2);

        resultadosDoEncaminhamento.setVotantes(membros);

        Assert.assertEquals(1, membros.size());
    }

    /**
     * Testa se é a lista de votantes está vazia quando não são adicionados objetos nela.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveExibirUmaListaDeVotantesVazia(){
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();

        Assert.assertNull(resultadosDoEncaminhamento.getVotantes());
    }

    @Test
    public void deveExibirZeroPorcentoDosVotos() {

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setDescricao("encaminhamento1");
        Encaminhamento encaminhamentoDois = new Encaminhamento();
        encaminhamentoDois.setDescricao("encaminhamento2");
        Encaminhamento encaminhamentoTres = new Encaminhamento();
        encaminhamentoTres.setDescricao("encaminhamento3");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento);
        itemDePauta.adiciona(encaminhamentoDois);

        Membro joao = new Membro();
        joao.setNome("João");

        Urna urna = new Urna();

        Votacao votacao = new Votacao(urna, itemDePauta);

        Voto voto = new Voto(joao, encaminhamento);
        votacao.registraVotoNaUrna(voto);

        ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(votacao);

        ResultadoDaVotacao resultadoDaVotacao;
        resultadoDaVotacao = apuracaoDaVotacao.realizaApuracao();

        double porcentagem = resultadoDaVotacao.getResultadosDoEncaminhamentos().get(1).getPorcentagem();

        Assert.assertTrue(0.0 == porcentagem);

    }

    @Test
    public void deveExibirCemPorcentoDosVotos() {

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setDescricao("encaminhamento1");
        Encaminhamento encaminhamentoDois = new Encaminhamento();
        encaminhamentoDois.setDescricao("encaminhamento2");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento);
        itemDePauta.adiciona(encaminhamentoDois);

        Membro joao = new Membro();
        joao.setNome("João");

        Urna urna = new Urna();

        Votacao votacao = new Votacao(urna, itemDePauta);

        Voto voto = new Voto(joao, encaminhamento);
        votacao.registraVotoNaUrna(voto);

        ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(votacao);

        ResultadoDaVotacao resultadoDaVotacao;
        resultadoDaVotacao = apuracaoDaVotacao.realizaApuracao();

        double porcentagem = resultadoDaVotacao.getResultadosDoEncaminhamentos().get(0).getPorcentagem();

        Assert.assertTrue(100.0 == porcentagem);

    }

    @Test
    public void deveExibirZeroQuantidadeDeVotos() {

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setDescricao("encaminhamento1");
        Encaminhamento encaminhamentoDois = new Encaminhamento();
        encaminhamentoDois.setDescricao("encaminhamento2");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento);
        itemDePauta.adiciona(encaminhamentoDois);

        Membro joao = new Membro();
        joao.setNome("João");

        Urna urna = new Urna();

        Votacao votacao = new Votacao(urna, itemDePauta);

        Voto voto = new Voto(joao, encaminhamento);
        votacao.registraVotoNaUrna(voto);

        ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(votacao);

        ResultadoDaVotacao resultadoDaVotacao;
        resultadoDaVotacao = apuracaoDaVotacao.realizaApuracao();

        Integer quantidadeDeVotos = resultadoDaVotacao.getResultadosDoEncaminhamentos().get(1).getQuantidadeDeVotos();

        Assert.assertTrue(0 == quantidadeDeVotos);

    }

    @Test
    public void deveExibirDezVotos(){

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setDescricao("encaminhamento1");

        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento);

        Membro joao = new Membro();
        joao.setNome("João");

        Urna urna = new Urna();

        Votacao votacao = new Votacao(urna, itemDePauta);

        for(int i = 0; i < 10; i++) {
            Voto voto = new Voto(joao, encaminhamento);
            votacao.registraVotoNaUrna(voto);
        }

        ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(votacao);

        ResultadoDaVotacao resultadoDaVotacao;
        resultadoDaVotacao = apuracaoDaVotacao.realizaApuracao();

        Integer quantidadeDeVotos = resultadoDaVotacao.getResultadosDoEncaminhamentos().get(0).getQuantidadeDeVotos();

        Assert.assertTrue(10 == quantidadeDeVotos);

    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarValorNegativoNaPorcentagemDosVotos() {
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();
        resultadosDoEncaminhamento.setPorcentagem(-0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarValorCentoEUmNaPorcentagemDosVotos() {
        ResultadosDoEncaminhamento resultadosDoEncaminhamento = new ResultadosDoEncaminhamento();
        resultadosDoEncaminhamento.setPorcentagem(100.1);
    }
}
