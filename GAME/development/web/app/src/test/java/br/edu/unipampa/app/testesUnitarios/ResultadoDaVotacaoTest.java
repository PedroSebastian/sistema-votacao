package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.*;
import org.junit.Assert;
import org.junit.Test;

public class ResultadoDaVotacaoTest {

    /**
     * Testa se uma exceção é lançada ao tentar criar uma lista vazia de resultados de encaminhamentos.
     *
     * @author Pedro Sebastian
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void aListaDeResultadosDoEncaminhamentosNaoPodeSerNula(){
        ResultadoDaVotacao resultadoDaVotacao = new ResultadoDaVotacao();

        resultadoDaVotacao.setResultadosDoEncaminhamentos(null);
    }


}
