package br.edu.unipampa.app.unidade.domain;

import br.edu.unipampa.app.domain.Encaminhamento;
import br.edu.unipampa.app.domain.ItemDePauta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EncaminhamentoTest {

    private Encaminhamento encaminhamento;

    @Before
    public void init() {
        this.encaminhamento = new Encaminhamento();
    }
    @Test
    public void encaminhamentoDeveTerDescricao (){
        this.encaminhamento.setDescricao("Descrição Teste");
        Assert.assertEquals("Descrição Teste",this.encaminhamento.getDescricao());

    }


    }