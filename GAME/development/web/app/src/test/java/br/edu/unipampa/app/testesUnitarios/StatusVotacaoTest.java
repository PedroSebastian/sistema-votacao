package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.StatusVotacao;
import org.junit.Assert;
import org.junit.Test;

import static br.edu.unipampa.app.domain.StatusVotacao.FECHADA;

/**
 * Created by Esther Favero on 21/06/2018.
 */
public class StatusVotacaoTest {

    @Test
    public void deveTerStatusFECHADATest(){

        StatusVotacao s = StatusVotacao.FECHADA;

        Assert.assertEquals(StatusVotacao.valueOf("FECHADA"), s );
    }

    @Test
    public void deveTerStatusABERTATest(){

        StatusVotacao s = StatusVotacao.ABERTA;

        Assert.assertEquals(StatusVotacao.valueOf("ABERTA"), s );
    }

    @Test
    public void deveTerStatusFINALIZADATest(){

        StatusVotacao s = StatusVotacao.FINALIZADA;

        Assert.assertEquals(StatusVotacao.valueOf("FINALIZADA"), s );
    }


    /**Teste da Classe StatusVotacao
     * Objetivo: Testar com um Status válido e compara com algo que é Inexistente dentre as Opções
     * Entrada: StatusVotacao.FECHADA
     * Saída Esperada: Erro
     */
    @Test
    public void statusInexistenteTest(){
        StatusVotacao s = StatusVotacao.FECHADA;
        Assert.assertEquals(StatusVotacao.valueOf("Contrario"), s );
    }

    /**Teste da Classe StatusVotacao
     * Objetivo: Testar com um Status válido e compara com o status com nome em minusculo
     * Entrada: StatusVotacao.FECHADA
     * Saída Esperada: Erro
     */
    @Test
    public void statusComStatusEmMinusculoTest(){
        StatusVotacao s = StatusVotacao.ABERTA;
        Assert.assertEquals(StatusVotacao.valueOf("aBERTA"), s );
    }

}
