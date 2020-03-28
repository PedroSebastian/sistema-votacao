package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReuniaoTest {

    /**
     * Testa se é possível criar uma reunião com a descrição nula.
     * <p>
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirDescricaoNula() {
        Reuniao reuniao = new Reuniao();
        reuniao.setDescricao(null);
    }

    /**
     * Testa se é possível criar uma reunião com a data nula.
     * <p>
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirADataNula() {
        Reuniao reuniao = new Reuniao();
        reuniao.setData(null);
    }

    /**
     * Testa se é possível criar uma reunião com o horário nulo.
     * <p>
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDevePermitirHorarioNulo() {
        Reuniao reuniao = new Reuniao();
        reuniao.setHorario(null);
    }

    /**
     * Testa se é possível definir uma reunião como aberta.
     * <p>
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void reuniaoDevePoderSerDefinidaComoAberta() {
        Reuniao reuniao = new Reuniao();

        reuniao.setEstaAberta(true);

        Assert.assertEquals(true, reuniao.getEstaAberta());
    }

    /**
     * Testa se é possível definir uma reunião como fechada.
     * <p>
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void reuniaoDevePoderSerDefinidaComoFechada() {
        Reuniao reuniao = new Reuniao();

        reuniao.setEstaAberta(false);

        Assert.assertEquals(false, reuniao.getEstaAberta());
    }

    /**
     * Testa se é possível definir uma data com o formato yyyy-mm-dd.
     * <p>
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void deveAceitarDataNoFormatoAnoMesDia() {
        LocalDate localDate = LocalDate.now();
        Reuniao reuniao = new Reuniao();

        reuniao.setData(localDate);

        LocalDate localDateNovo = LocalDate.of(2018, 6, 25);

        Assert.assertEquals(localDateNovo, localDate);
    }

    /**
     * Testa se é possível atribuir a menor data possível.
     * <p>
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void deveAceitarAMenorData() {
        Reuniao reuniao = new Reuniao();

        LocalDate menorData = LocalDate.MIN;

        LocalDate data = LocalDate.of(-999999999, 01, 01);

        reuniao.setData(menorData);

        Assert.assertEquals(data, menorData);
    }

    /**
     * Testa se é possível atribuir a maior data possível.
     * <p>
     * Teste positivo
     *
     * @author Esther Favero
     */
    @Test
    public void deveAceitarAMaiorData() {
        Reuniao reuniao = new Reuniao();

        LocalDate maiorData = LocalDate.MAX;

        LocalDate data = LocalDate.of(+999999999, 12, 31);

        reuniao.setData(maiorData);

        Assert.assertEquals(data, maiorData);
    }

    /**
     * Testa se uma exceção é lançada ao setar um moderador vazio.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarModeradorNulo() {
        Reuniao reuniao = new Reuniao();

        reuniao.setModerador(null);
    }

    /**
     * Testa se é possível adicionar membros na lista.
     * <p>
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveAdicionarMembrosNaLista() {
        Reuniao reuniao = new Reuniao();

        Membro membro1 = new Membro();

        Membro membro2 = new Membro();

        List<Membro> membros = new ArrayList<>();
        membros.add(membro1);
        membros.add(membro2);

        reuniao.setMembros(membros);

        Assert.assertEquals(2, membros.size());
    }

    /**
     * Testa se é a lista de membros está vazia quando não são adicionados objetos nela.
     * <p>
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveExibirUmaListaDeMembrosVazia() {
        Reuniao reuniao = new Reuniao();

        Assert.assertNull(reuniao.getMembros());

    }

    /**
     * Testa se é possível remover membros da lista.
     * <p>
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveRemoverMembrosDaLista() {
        Reuniao reuniao = new Reuniao();

        Membro membro1 = new Membro();

        Membro membro2 = new Membro();

        List<Membro> membros = new ArrayList<>();
        membros.add(membro1);
        membros.remove(membro1);
        membros.add(membro2);

        reuniao.setMembros(membros);

        Assert.assertEquals(1, membros.size());
    }

}
