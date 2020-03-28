package br.edu.unipampa.app.application.controller;

import br.edu.unipampa.app.application.service.ItemDePautaService;
import br.edu.unipampa.app.application.service.VotacaoService;
import br.edu.unipampa.app.domain.Votacao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Esther Favero on 30/05/2018.
 */
@Named
@ViewScoped
public class VotacaoBean implements Serializable {

    @Autowired
    private VotacaoService votacaoService;

    @Autowired
    private ItemDePautaService itemDePautaService;

    private Votacao votacao;

    private String titulo = "Resultados da Votação";

    private Long idItem;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();

        try {
            this.inicializaVotacaoParaOItem(this.pegaParametroDoItem());
        } catch (NumberFormatException | NullPointerException ex) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(contextPath + "/reunioes.jsf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Long pegaParametroDoItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String itemDePautaId = paramMap.get("item");

        this.idItem = Long.parseLong(itemDePautaId);

        return Long.parseLong(itemDePautaId);
    }

    void inicializaVotacaoParaOItem(Long id) {
        this.votacao = this.votacaoService.iniciaVotacao(id);
    }

    public void cancelarVotacao() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        this.votacaoService.cancelaVotacao(this.idItem);

        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath  + "/itens-de-pauta.jsf?reuniao=" +
                            this.itemDePautaService.carregaItemDePautaPelo(this.idItem).getReuniao().getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void encerraVotacao() {
        this.votacaoService.encerraVotacao();
    }

    public void encaminhaParaSegundoTurno() {
        this.votacaoService.encaminhaParaVotacaoEmSegundoTurno(this.idItem);
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void exibeResultado() {
        this.votacao = this.votacaoService.mostraResultados();
    }

}
