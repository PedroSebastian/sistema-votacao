package br.edu.unipampa.app.application.controller;

import br.edu.unipampa.app.application.service.ItemDePautaService;
import br.edu.unipampa.app.domain.Encaminhamento;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.infrastructure.JSF.FacesMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Named
@ViewScoped
public class EncaminhamentosBean {

    private ItemDePauta itemDePauta;

    private boolean selecionaPadrao = false;
    private boolean selecionaCustomizado = false;

    private String opcaoCustomizada = "";

    @Autowired
    private ItemDePautaService itemDePautaService;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();

        try {
            this.carregaItemDePautaPeloUsandoParamentroDaURL();
            if (this.itemDePauta.getEncaminhamentos() == null) {
                this.itemDePauta.setEncaminhamentos(new ArrayList<Encaminhamento>());
            }
        } catch (NumberFormatException | NullPointerException ex) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(contextPath  + "/reunioes.jsf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void carregaItemDePautaPeloUsandoParamentroDaURL() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String itemDePautaId = paramMap.get("item");

        this.itemDePauta = this.itemDePautaService.carregaItemDePautaPelo(Long.parseLong(itemDePautaId));
    }

    public boolean isSelecionaPadrao() {
        return selecionaPadrao;
    }

    public void setSelecionaPadrao(boolean selecionaPadrao) {
        this.selecionaPadrao = selecionaPadrao;
    }

    public boolean isSelecionaCustomizado() {
        return selecionaCustomizado;
    }

    public void setSelecionaCustomizado(boolean selecionaCustomizado) {
        this.selecionaCustomizado = selecionaCustomizado;
    }

    public void selecionaOpcaoPadrao() {
        this.selecionaPadrao = true;
        this.selecionaCustomizado = false;
    }

    public void selecionaOpcaoCustomizado() {
        this.selecionaPadrao = false;
        this.selecionaCustomizado = true;
    }

    public void encaminharItemDePauta() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();

        if (this.selecionaCustomizado == false && this.selecionaPadrao == false) {
            FacesMessageUtil.addErrorMessage("Oops!",
                    "Você deve selecionar os Tipos de Encaminhamento.");

            this.init();
        } else {
            if (this.selecionaPadrao == true && this.selecionaCustomizado == false) {
                ItemDePauta novoItem = this.itemDePautaService.criaItemDePautaComPadrao(this.itemDePauta);

                FacesMessageUtil.addInfoMessage("Sucesso",
                        "Item de Pauta disponibilizado com sucesso.");

                try {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(contextPath  + "/resultado-votacao.jsf?item=" + this.itemDePauta.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (this.selecionaPadrao == false && this.selecionaCustomizado == true) {
                if (this.itemDePauta.getEncaminhamentos().isEmpty()) {
                    FacesMessageUtil.addErrorMessage("Oops!",
                            "Você deve adicionar ao menos um encaminhamto ao selecionar Encaminhamento Customizado.");

                    this.init();
                } else {
                    ItemDePauta novoItem = this.itemDePautaService.criaItemDePautaCustomizado(this.itemDePauta);

                    FacesMessageUtil.addInfoMessage("Sucesso",
                            "Item de Pauta disponibilizado com sucesso.");

                    try {
                        FacesContext.getCurrentInstance().getExternalContext()
                                .redirect(contextPath  + "/resultado-votacao.jsf?item=" + this.itemDePauta.getId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public ItemDePauta getItemDePauta() {
        return itemDePauta;
    }

    public void setItemDePauta(ItemDePauta itemDePauta) {
        this.itemDePauta = itemDePauta;
    }

    public String getOpcaoCustomizada() {
        return opcaoCustomizada;
    }

    public void setOpcaoCustomizada(String opcaoCustomizada) {
        this.opcaoCustomizada = opcaoCustomizada;
    }

    public void addOpcao() {
        this.opcaoCustomizada = this.opcaoCustomizada.replaceAll("^\\s+", "");

        this.opcaoCustomizada = this.opcaoCustomizada.replaceAll("\\s+$", "");

        if (this.opcaoCustomizada.equals("") || this.opcaoCustomizada == null) {
            FacesMessageUtil.addErrorMessage("Oops!", "O Encaminhamento Customizado não pode ser vazio.");
        } else {
            Encaminhamento novoEncaminhamento = new Encaminhamento(this.opcaoCustomizada, this.itemDePauta);
            this.opcaoCustomizada = "";

            this.itemDePauta.adiciona(novoEncaminhamento);
        }
    }

    public void removeOpcao(Encaminhamento encaminhamento) {
        this.itemDePauta.removeEncaminhamento(encaminhamento);
    }

}
