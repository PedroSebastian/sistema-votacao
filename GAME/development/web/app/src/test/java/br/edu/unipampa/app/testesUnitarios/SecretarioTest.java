package br.edu.unipampa.app.testesUnitarios;

import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.domain.Secretario;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SecretarioTest {

    @Test
    public void deveAdicionarUmaOrdemNosItensDePautas(){

        ItemDePauta itemDePauta0 = new ItemDePauta();
        ItemDePauta itemDePauta1 = new ItemDePauta();
        ItemDePauta itemDePauta2 = new ItemDePauta();

        ArrayList<ItemDePauta> itemDePautas = new ArrayList<>();

        itemDePautas.add(itemDePauta0);
        itemDePautas.add(itemDePauta1);
        itemDePautas.add(itemDePauta2);

        Secretario secretario = new Secretario();

        secretario.adicionaOrdemNos(itemDePautas);
            Integer aux = 1;
            for (ItemDePauta item : itemDePautas) {
                Assert.assertEquals(aux,item.getOrdem());
                aux++;
            }
        }

    }
