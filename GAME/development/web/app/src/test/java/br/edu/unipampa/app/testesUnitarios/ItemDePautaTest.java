package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.Encaminhamento;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.domain.Votacao;
import java.time.LocalDate;
import java.time.LocalTime;
import br.edu.unipampa.app.domain.Reuniao;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Testes unitários para a classe ItemDePauta
 */
public class ItemDePautaTest {

    /**
     * Testa se uma exceção é lançada quando um relator é nulo.
     *
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmRelatorNulo(){
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.setRelator(null);
    }

    /**
     * Testa se uma exceção é lançada quando o relator é vazio.
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmRelatorVazio(){
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.setRelator("");
    }

    /**
     * Testa se uma exceção é lançada quando a descrição do item de pauta é nula.
     *
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmaDescricaoDeItemDePautaNula(){
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.setDescricao(null);
    }

    /**
     * Testa se uma exceção é lançada quando a descricação do item de pauta é vazia.
     *
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmaDescricaoDeItemDePautaVazia(){
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.setDescricao("");
    }

    /**
     * Testa se uma exceção é lançada quando a reunião é nula.
     *
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmaReuniaoNula(){
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.setReuniao(null);
    }

    /**
     * Testa se é possível adicionar encaminhamentos.
     *
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void devePoderAdicionarEncaminhamentos() {
        Encaminhamento encaminhamento = new Encaminhamento();
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.adiciona(encaminhamento);

        Assert.assertEquals(encaminhamento, itemDePauta.getEncaminhamentos().get(0));
    }

    /**
     * Testa se é possível remover um encaminhamento
     *
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void devePermitirRemoverUmEncaminhamento() {
        Encaminhamento encaminhamento = new Encaminhamento();
        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.adiciona(encaminhamento);

        itemDePauta.removeEncaminhamento(encaminhamento);

        Assert.assertEquals(0, itemDePauta.getEncaminhamentos().size());
    }

    /**
     * Testa se uma exceção é lançada quando um encaminhamento é nulo.
     *
     * Teste Negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirAdicionarUmEncaminhamentoNulo() {
        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento = null;
        ItemDePauta itemDePauta = new ItemDePauta();

        itemDePauta.adiciona(encaminhamento);
    }

    /**
     * Testa se uma exceção é lançada ao tentar remover um encaminhamento de uma lista vazia.
     *
     *  Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void naoDeveAceitarRemoverEncaminhamentoDeListaVazia() {
        Encaminhamento encaminhamento = new Encaminhamento();
        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.removeEncaminhamento(encaminhamento);
    }

    /**
     * Testa se é possível definir uma votação como segundo turno.
     *
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void devePermitirDefinirVotacaoComSegundoTurno() {
        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.setTemSegundoTurno(true);

        Assert.assertEquals(true, itemDePauta.isTemSegundoTurno());
    }

    /**
     * Testa se é possível adicionar votação em primeiro turno.
     *
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void devePermitirAdicionarVotacaoEmPrimeiroTurno() {
        ItemDePauta itemDePauta = new ItemDePauta();
        Votacao votacao = new Votacao();
        itemDePauta.setPrimeiroTurno(votacao);

//        Assert.assertEquals();

    }

    /**
     * O Item de Pauta não pode permitir adicinar Votação em segundo turno caso esteja configurado
     * votação apenas com primeiro turno.
     *
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirAdicionarVotacaoEmSegundoTurnoSeConfiguradoApenasComoPrimeiro() {
        ItemDePauta itemDePauta = new ItemDePauta();
        itemDePauta.setTemSegundoTurno(false);
        Votacao votacao = new Votacao();
        itemDePauta.setPrimeiroTurno(votacao);

        Votacao segundoTurno = new Votacao();
        itemDePauta.setSegundoTurno(segundoTurno);
    }



    /**
     * O Item de Pauta deve permitir que o item de pauta seja criado
     *
     *
     * @author Amanda Lopes
     */

    public void deveCriarUmItemDePautaComTodosOsValores(){
        ItemDePauta itemDePauta = new ItemDePauta();

        Reuniao reuniao = new Reuniao();
        reuniao.setDescricao("Reunião2");
        reuniao.setData(LocalDate.now());
        reuniao.setHorario(LocalTime.now());
        reuniao.setEstaAberta(true);
        itemDePauta.setReuniao(reuniao);
        itemDePauta.setRelator("Amanda M. Mello");
        itemDePauta.setDescricao("O item de pauta da reunião é sobre acessibilidade");
        assertEquals("Reunião2", reuniao.getDescricao());
        assertEquals(false, itemDePauta.isTemSegundoTurno());
        assertEquals("Amanda M. Mello", itemDePauta.getRelator());
        assertEquals("O item de pauta da reunião é sobre acessibilidade", itemDePauta.getDescricao());


    }


    /**
     *Não deve criar o Item de Pauta com dados faltantes
     *
     *
     * @author Amanda Lopes
     */

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarUmaExcessaoParaItemDePautaComDadoFaltante() {
        ItemDePauta itemDePauta = new ItemDePauta();
        //cria um reunião e seta valores
        Reuniao reuniao = new Reuniao();
        reuniao.setDescricao("Reunião 1");

        reuniao.setData(LocalDate.now());
        reuniao.setHorario(LocalTime.now());
        reuniao.setEstaAberta(true);

        //Seta valores da reunião, descrição e seta o relator como nulll
        itemDePauta.setReuniao(reuniao);
        itemDePauta.setRelator(null);
        itemDePauta.setDescricao("O item de pauta da reunião é sobre a Copa");
        assertEquals("O item de pauta da reunião é sobre a Copa", itemDePauta.getDescricao());
        assertEquals(true, reuniao.getEstaAberta());
        assertEquals("Reunião 1", reuniao.getDescricao());


    }


}
