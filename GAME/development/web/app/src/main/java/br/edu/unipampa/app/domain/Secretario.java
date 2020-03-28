package br.edu.unipampa.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Entity(name = "Secretarios")
public class Secretario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Long getId() {

        return id;

    }

    public String getNome() {

        return nome;

    }

    public void setNome(String nome) {

        if (nome == null) {
            throw new IllegalArgumentException();
        } else {
            this.nome = nome;
        }

    }

    public List<ItemDePauta> adicionaOrdemNos(List<ItemDePauta> itensDePautas) {

        for (int i = 0; i < itensDePautas.size(); i++) {
            itensDePautas.get(i).setOrdem(i + 1);
        }
        return itensDePautas;
    }

    public List<ItemDePauta> moveOrdemDosItens(List<ItemDePauta> itens, ItemDePauta itemASerMovido, ItemDePauta itemASerOrdenado) {

        Integer itemMover = itemASerMovido.getOrdem();
        Integer itemASerSubstituido = itemASerOrdenado.getOrdem();

        itens.get(itemASerSubstituido).setOrdem(itemMover);
        itens.get(itemMover).setOrdem(itemASerSubstituido);
        Collections.sort(itens);

        return itens;
    }
}

