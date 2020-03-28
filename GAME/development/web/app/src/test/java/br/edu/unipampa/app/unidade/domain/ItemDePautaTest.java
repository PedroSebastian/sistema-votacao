package br.edu.unipampa.app.unidade.domain;

import br.edu.unipampa.app.domain.Encaminhamento;
import br.edu.unipampa.app.domain.ItemDePauta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemDePautaTest {

    private ItemDePauta itemDePauta;

    @Before
    public void init() {
        this.itemDePauta = new ItemDePauta();
    }

    @Test
    public void segundoTurnoDeveIniciarFalse() {
        Assert.assertFalse(this.itemDePauta.isTemSegundoTurno());
    }

    @Test
    public void devePermitirSegundoTurno() {
        this.itemDePauta.setTemSegundoTurno(true);
        Assert.assertTrue(this.itemDePauta.isTemSegundoTurno());
    }

    @Test
    public void deveAceitarAddEncaminhamento (){
        this.itemDePauta.adiciona(new Encaminhamento("Encaminhamento Teste", this.itemDePauta));
        Assert.assertEquals(1, this.itemDePauta.getEncaminhamentos().size());
    }
    @Test
    public void verificaSeEncaminhamentoAddCorretamente(){
        this.itemDePauta.adiciona(new Encaminhamento("Encaminhamento Teste", this.itemDePauta));
        Assert.assertTrue(this.itemDePauta.getEncaminhamentos().get(0).getDescricao().equals("Encaminhamento Teste"));
    }
   @Test
    public void verificaSeEncaminhamentoIniciaNulo (){
        Assert.assertNull(this.itemDePauta.getEncaminhamentos());
   }

   @Test
    public void deveRemoverEncaminhamento (){
        Encaminhamento encaminhamento = new Encaminhamento("Encaminhamento Teste", this.itemDePauta);
        this.itemDePauta.adiciona(encaminhamento);
        this.itemDePauta.removeEncaminhamento(encaminhamento);
        Assert.assertTrue(this.itemDePauta.getEncaminhamentos().isEmpty());
    }
    @Test
    public void itemDePautaDeveAceitarUmaListaEncaminhamentos(){
        List<Encaminhamento> encaminhamentos= new ArrayList<>();
        encaminhamentos.add(new Encaminhamento("Encacaminhamento Teste1", this.itemDePauta));
        encaminhamentos.add(new Encaminhamento("Encacaminhamento Teste2", this.itemDePauta));
        encaminhamentos.add(new Encaminhamento("Encacaminhamento Teste3", this.itemDePauta));
        encaminhamentos.add(new Encaminhamento("Encacaminhamento Teste4", this.itemDePauta));
        encaminhamentos.add(new Encaminhamento("Encacaminhamento Teste5", this.itemDePauta));
        this.itemDePauta.setEncaminhamentos(encaminhamentos);
        Assert.assertEquals(5,this.itemDePauta.getEncaminhamentos().size());
    }

}
