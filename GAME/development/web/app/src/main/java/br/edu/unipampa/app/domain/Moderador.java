package br.edu.unipampa.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Esther Favero on 15/05/2018.
 */
@Entity(name = "moderadores")
public class Moderador {

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
        if(nome == null){
            throw new IllegalArgumentException();
        } else {
            this.nome = nome;
        }
    }
}
