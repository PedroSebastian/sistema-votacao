package br.edu.unipampa.app.application.controller;

import br.edu.unipampa.app.application.service.ItemDePautaService;
import br.edu.unipampa.app.application.service.ReuniaoService;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.domain.Reuniao;
import br.edu.unipampa.app.infrastructure.JSF.FacesMessageUtil;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@SessionScope
public class ItensDePautaBean implements Serializable {

    @Autowired
    private ItemDePautaService itemDePautaService;

    @Autowired
    private ReuniaoService reuniaoService;

    private Reuniao reuniao;

    private List<ItemDePauta> itens;

    private int primeiro = 0;

    private int ordem = 1;

    private List<Integer> ordens;
    private List<Integer> ordensEdicao;

    private ItemDePauta novoItemDePauta;

    private ItemDePauta itemSelecionado = new ItemDePauta();

    @PostConstruct
    public void init() {
        this.novoItemDePauta = new ItemDePauta();
        this.carregaItensDaReuniao();

        this.ordens = new ArrayList<>();

        this.verificaOrdensDisponíveis();
        this.verificaOrdensDisponíveisParaEdicao();
    }

    private void verificaOrdensDisponíveis() {
        this.ordens = new ArrayList<>();

        for (ItemDePauta itemDePauta : this.itens) {
            this.ordens.add(itemDePauta.getOrdem());
        }

        this.ordens.add(this.ordens.size() + 1);
    }

    private void verificaOrdensDisponíveisParaEdicao() {
        this.ordensEdicao = new ArrayList<>();

        for (ItemDePauta itemDePauta : this.itens) {
            this.ordensEdicao.add(itemDePauta.getOrdem());
        }
    }

    private void carregaItensDaReuniao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String itemDePautaId = paramMap.get("reuniao");

        this.reuniao = this.reuniaoService.pegaPeloId(Long.parseLong(itemDePautaId));

        this.itens = this.itemDePautaService.pegaPeloIdDaReuniao(Long.parseLong(itemDePautaId));
    }

    public List<ItemDePauta> getItens() {
        return itens;
    }

    public void setItens(List<ItemDePauta> itens) {
        this.itens = itens;
    }

    public int getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(int primeiro) {
        this.primeiro = primeiro;
    }

    public ItemDePauta getNovoItemDePauta() {
        return novoItemDePauta;
    }

    public void setNovoItemDePauta(ItemDePauta novoItemDePauta) {
        this.novoItemDePauta = novoItemDePauta;
    }

    public void salvarNovoITtem() {
        this.novoItemDePauta.setReuniao(this.reuniao);

        if (this.ordem != 0) {
            this.itens.add(this.ordem -1, this.novoItemDePauta);
        } else {
            this.itens.add(this.ordem, this.novoItemDePauta);
        }

        this.itens = this.itemDePautaService.salvaEOrdena(this.itens);
        this.verificaOrdensDisponíveis();

        this.ordem = 1;
        this.novoItemDePauta = new ItemDePauta();

        FacesMessageUtil.addInfoMessage("",
                "Item cadastrado com sucesso.");
    }

    public void editarItem() {
        System.out.println("aquii");
        int index = this.itens.indexOf(this.itemSelecionado);
        System.out.println(index);
        ItemDePauta itemDePauta = this.itens.get(index);
        this.itens.remove(index);

        if (this.itens.size() < itemDePauta.getOrdem() -1) {
            this.itens.add(itemDePauta.getOrdem(), itemDePauta);
        } else if (this.itens.size() == 1) {
            this.itens.add(itemDePauta.getOrdem(), itemDePauta);
        } else {
            this.itens.add(itemDePauta.getOrdem() -1, itemDePauta);
        }

        this.itens = this.itemDePautaService.salvaEOrdena(this.itens);

        if (this.itens.size() < itemDePauta.getOrdem() -1) {
            this.itens.add(itemDePauta.getOrdem(), itemDePauta);
            this.itemSelecionado = this.itens.get(itemDePauta.getOrdem());
        } else if (this.itens.size() == 1) {
            this.itemSelecionado = this.itens.get(itemDePauta.getOrdem());
        } else {
            this.itemSelecionado = this.itens.get(itemDePauta.getOrdem() -1);
        }

        this.verificaOrdensDisponíveisParaEdicao();

        FacesMessageUtil.addInfoMessage("",
                "Item editado com sucesso.");
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public List<Integer> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<Integer> ordens) {
        this.ordens = ordens;
    }

    public void onRowReorder(ReorderEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item reordenado com sucesso", " ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("From: " + event.getFromIndex() + ", To:" + event.getToIndex());
        System.out.println("________________________________");
        for (ItemDePauta itemDePauta : this.itens) {
            System.out.println(itemDePauta.getId());
        }

        this.itens = this.itemDePautaService.salvaEOrdena(this.itens);
    }

    public ItemDePauta getItemSelecionado() {
        return itemSelecionado;
    }

    public void deletaIttem(){
        itemDePautaService.deletarItemDePautaPelo(itemSelecionado.getId());
        this.itens = this.itemDePautaService.pegaPeloIdDaReuniao(this.reuniao.getId());
        this.itens = this.itemDePautaService.salvaEOrdena(this.itens);
        this.itemSelecionado = new ItemDePauta();
        this.verificaOrdensDisponíveis();
        this.verificaOrdensDisponíveisParaEdicao();

        FacesMessageUtil.addInfoMessage("",
                "Item deletado com sucesso.");
    }

    public void setItemSelecionado(ItemDePauta itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public void seleciona(ItemDePauta itemDePauta) {
        this.setItemSelecionado(itemDePauta);
    }

    public void onRowSelect(SelectEvent event) {
        this.setItemSelecionado(((ItemDePauta) event.getObject()));
    }

    public void onRowUnselect(UnselectEvent event) {
//        this.setItemSelecionado(new ItemDePauta());
    }

    public List<Integer> getOrdensEdicao() {
        return ordensEdicao;
    }

    public void setOrdensEdicao(List<Integer> ordensEdicao) {
        this.ordensEdicao = ordensEdicao;
    }

}
