package br.edu.unipampa.app.integracao;

import br.edu.unipampa.app.application.service.ItemDePautaService;
import br.edu.unipampa.app.domain.Encaminhamento;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.infrastructure.repository.ItemDePautaRepository;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Esther Favero on 26/04/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncaminhamentosTest {

    @Autowired
    private ItemDePautaService itemDePautaService;

    @Autowired
    private ItemDePautaRepository repository;

    private ItemDePauta itemDePauta;

    @Before
    public void init() {
        this.itemDePauta = new ItemDePauta();
    }

//    @Test
//    public void criaItemDePautaComPadraoTest() {
//        this.repository.deleteAll();
//        this.itemDePauta.setDescricao("Descrição do item de pauta");
//        this.itemDePauta.setRelator("Esther");
//
//        this.itemDePautaService.criaItemDePautaComPadrao(this.itemDePauta);
//        List<ItemDePauta> itens = this.repository.findAll();
//
//        Assert.assertEquals("Favorável", itens.get(0).getEncaminhamentos().get(0).getDescricao());
//    }
//
//    @Test
//    public void criaItemDePautaCustomizadoTest() {
//        this.repository.deleteAll();
//        this.itemDePauta.setDescricao("Descrição do item de pauta");
//        this.itemDePauta.setRelator("Maria");
//
//        Encaminhamento encaminhamento1 = new Encaminhamento("trocar de professor", this.itemDePauta);
//
//        this.itemDePautaService.criaItemDePautaCustomizado(this.itemDePauta);
//        List<ItemDePauta> itens = this.repository.findAll();
//
//        Assert.assertEquals(1, itens.get(0).getEncaminhamentos().size());
//    }

//    @Test
//    public void listaItensDePautaTest() {
//
//        this.repository.deleteAll();
//        this.itemDePauta.setDescricao("Descrição do item de pauta1");
//        this.itemDePauta.setRelator("Maria");
//
//        this.itemDePauta.setDescricao("Descrição do item de pauta2");
//        this.itemDePauta.setRelator("José");
//
//        this.itemDePauta.setDescricao("Descrição do item de pauta3");
//        this.itemDePauta.setRelator("João");
//
//        this.itemDePautaService.listaItensDePauta();
//        List<ItemDePauta> itens = new ArrayList<>();
//
//        Assert.assertEquals(itens, this.repository.findAll());
//
//    }

    @Test
    public void carregaItemDePautaPeloIdTest() {

        this.repository.deleteAll();
        this.itemDePauta.setDescricao("Descrição do item de pauta1");
        this.itemDePauta.setRelator("Maria");

        this.itemDePauta = this.repository.save(this.itemDePauta);

        Assert.assertNotNull(this.itemDePautaService.carregaItemDePautaPelo(this.itemDePauta.getId()));
    }

    @Test
    public void salvaItemDePautaTest() {

        this.repository.deleteAll();
        this.itemDePauta.setDescricao("Descrição do item de pauta1");
        this.itemDePauta.setRelator("Maria");

        this.itemDePautaService.salvaItemDePauta(this.itemDePauta);

        Assert.assertNotNull(this.itemDePauta);

    }

}
