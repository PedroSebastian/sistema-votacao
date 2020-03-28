package br.edu.unipampa.app.application.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexBean implements Serializable {

    String titulo;
    String descricao;

    public void salvar() {
        System.out.println(this.titulo + ": " + this.descricao);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
