package br.edu.unipampa.app.application.controller;

import br.edu.unipampa.app.application.service.ReuniaoService;
import br.edu.unipampa.app.domain.Reuniao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ReuniaoBean {

    @Autowired
    private ReuniaoService reuniaoService;

    List<Reuniao> reunioes;
    private int primeiro = 0;

    @PostConstruct
    public void init() {
        this.reunioes = this.reuniaoService.reunioesAbertas();
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public void setReunioes(List<Reuniao> reunioes) {
        this.reunioes = reunioes;
    }

    public int getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(int primeiro) {
        this.primeiro = primeiro;
    }

}
