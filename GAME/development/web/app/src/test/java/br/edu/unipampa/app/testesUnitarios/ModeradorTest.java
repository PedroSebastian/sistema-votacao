package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.Moderador;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ModeradorTest {

    private Moderador moderador = new Moderador();

    @Test
    public void getNomeTest(){
        moderador.setNome("José");
        assertEquals("José", moderador.getNome());
    }

//    @Test
//    public void getNomeNegativoTest(){
//        moderador.setNome("José");
//        assertEquals("josé", moderador.getNome());
//    }

    /**
     * Testa se uma exceção é lançada quando um moderador é nulo.
     *
     * Teste negativo
     *
     * @author Esther Favero
     */
    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarParaNomeDeModeradorNulo(){
        Moderador moderador = new Moderador();

        moderador.setNome(null);
    }

    @Test
    public void deveRetornaroIDdoMembro(){
        moderador.setNome("João Pablo");
       Long idModerador = moderador.getId();
        assertEquals("João Pablo", moderador.getNome());
        assertEquals("O ID retornado",idModerador,moderador.getId());
    }


//    @Test(expected = IllegalArgumentException.class)
//    public void deveLancarExcessaoParaNomeDeModeradorVazio(){
//        moderador.setNome(" ");
//    }
//
//    @Test
//    public void deveRetornarNomeDiferentedoEsperado(){
//        moderador.setNome("Amanda Gobus");
//        assertEquals("amanda gobus", moderador.getNome());
//    }

}
