//package br.edu.unipampa.app.testesUnitarios;
//import br.edu.unipampa.app.domain.*;
//import java.util.Random;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AppApplicationTests {
//
//    @Test
//    public void testSet() {
//
//    }
//
//    @Test
//    public void criaPauta() {
//        Pauta pauta = new Pauta(4l, "Pauta-1 ", "Qualquer Coisa ");
//        Pauta pauta1 = new Pauta(0l, "Pauta2 ", "Pauta da ES ");
//        assertTrue(pauta1.getId().equals(null));
//        assertTrue(pauta.getId().equals(4l));
//        assertTrue(pauta.getTitulo().equals("Pauta-1 "));
//        assertTrue(pauta.getDescricao().equals("Qualquer Coisa"));
//
//    }
//
//    @Test
//    public void pautavazia() {
//        Pauta pauta2 = new Pauta(25l, null, null);
//        assertNotNull(pauta2);
//    }
//
//    @Test
//    public void ItemDePauta() {
//        ItemDePauta i = new ItemDePauta(2l, "João Silva", "Pauta da Ciência da computação", null, true);
//        ItemDePauta a = new ItemDePauta(45l, "Jean", "Pauta da es", null, false);
//        ItemDePauta k = new ItemDePauta(45l, "Jean", "Pauta da es", null, true);
//        ItemDePauta c = new ItemDePauta(null, null, null, null, true);
//        assertSame(k, a);
//        assertNotNull(c);
//
//    }
//
//    @Test
//    public void AdicionaEncaminhamentos() {
//
//        Random Rand = new Random();
//        Encaminhamento enc = new Encaminhamento(Rand.nextLong(), "lllllllll");
//        ItemDePauta ip = new ItemDePauta();
//        for (int i = 0; i < 5; i++) {
//            ip.adiciona(enc);
//        }
//        assertEquals(enc, this);
//    }
//
//
//    @Test
//    public void removeEncaminhamentos() {
//
//
//
//    }
//
//}