package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.Membro;
import br.edu.unipampa.app.domain.Reuniao;
import net.bootsfaces.render.A;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MembroTest {
    Membro membro = new Membro();

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmNomeEmBranco() {

        Membro membro = new Membro();
        membro.setNome("");

    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarUmMembroComNomeNulo() {

        Membro membro = new Membro();
        membro.setNome(null);

    }

    /**
     * O Item de Pauta deve retornar o ID do membro
     *
     * @author Amanda Lopes
     */
    @Test
    public void deveRetornaroIDdoMembroTest() {
        membro.setNome("João Pablo");
        Long idModerador = membro.getId();
        assertEquals("João Pablo", membro.getNome());
        assertEquals("O ID retornado", idModerador, membro.getId());
    }

//
//    /**
//     * Deve lançar a excessao pois o nome setado foi vazio
//     *
//     *
//     * @author Amanda Lopes
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void deveLancarExcessaoParaNomeDeModeradorVazio(){
//        membro.setNome(" ");
//        assertEquals(" ", membro.getNome());
//    }
//
//
//    /**
//     * Esse teste tem que falhar, pois espera algo diferente do que foi setado
//     *
//     *
//     * @author Amanda Lopes
//     */
//    @Test
//    public void deveRetornarNomeDiferentedoEsperado(){
//        membro.setNome("Amanda Gobus");
//        assertEquals("amanda gobus", membro.getNome());
//    }

    /**
     * Deve retornar o nome do membro corretamente
     *
     * @author Amanda Lopes
     */
    @Test
    public void deveRetornaroonomedoMembroTest() {
        membro.setNome("Amanda");
        assertEquals("Amanda", membro.getNome());
    }


    /**
     * Esse teste tem que falhar, pois espera algo diferente do que foi setado
     *
     * @author Amanda Lopes
     */
    @Test
    public void deveRetornarNomeDiferentedoEsperado() {
        membro.setNome("Amanda Gobus");
        assertEquals("amanda gobus", membro.getNome());
    }

    /**
     * Teste com membros com o mesmo nome
     */
    @Test
    public void membroComMesmoNomeTest() {
        Membro m = new Membro();
        m.setNome("Ana");
        membro.setNome("Ana");
        assertEquals("Ana", membro.getNome());
        assertEquals("Ana", m.getNome());
        assertEquals(membro.getNome(),m.getNome());
    }



    /**
     * Testa se é possível setar um nome com mais de um nome.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveAceitarMaisDeUmNome() {
        Membro membro = new Membro();

        membro.setNome("Maria Joaquina dos Santos");

        Assert.assertEquals("Maria Joaquina dos Santos", membro.getNome());
    }

    /**
     * Testa se é possível adicionar reuniões na lista.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveAdicionarReunioesNaLista() {
        Membro membro = new Membro();

        Reuniao reuniao1 = new Reuniao();

        Reuniao reuniao2 = new Reuniao();

        List<Reuniao> reunioes = new ArrayList<>();
        reunioes.add(reuniao1);
        reunioes.add(reuniao2);

        membro.setReunioes(reunioes);

        Assert.assertEquals(2, reunioes.size());
    }

    /**
     * Testa se é possível remover reuniões da lista.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveRemoverReunioesDaLista() {
        Membro membro = new Membro();

        Reuniao reuniao1 = new Reuniao();

        Reuniao reuniao2 = new Reuniao();

        List<Reuniao> reunioes = new ArrayList<>();
        reunioes.add(reuniao1);
        reunioes.add(reuniao2);

        reunioes.remove(reuniao1);

        membro.setReunioes(reunioes);

        Assert.assertEquals(1, reunioes.size());
    }

    /**
     * Testa se uma exceção é lançada ao setar uma lista de reunião nula.
     *
     * teste negativo
     *
     * @author Pedro Sebastian
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void naoDeveAceitarUmaListaDeReunioesVazia(){
        Membro membro = new Membro();

        membro.setReunioes(null);
    }

    /**
     * Testa se é a lista de reuniões está vazia quando não são adicionados objetos nela.
     *
     * teste positivo
     *
     * @author Pedro Sebastian
     */
    @Test
    public void deveExibirUmaListaDeReunioesVazia(){
        Membro membro = new Membro();

        Assert.assertNull(membro.getReunioes());
    }


}
