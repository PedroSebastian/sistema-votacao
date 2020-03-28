package br.edu.unipampa.app.application.service;

import br.edu.unipampa.app.domain.Encaminhamento;
import br.edu.unipampa.app.domain.ItemDePauta;
import br.edu.unipampa.app.domain.Secretario;
import br.edu.unipampa.app.infrastructure.repository.ItemDePautaRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDePautaService {

    @Autowired
    private ItemDePautaRepository repository;

    @Autowired
    public ApplicationEventPublisher eventPublisher;

    private ItemDePauta itemDePauta;

    public ItemDePauta criaItemDePautaComPadrao(ItemDePauta itemDePauta) {
        this.itemDePauta = itemDePauta;
        this.itemDePauta.setTemSegundoTurno(false);
        List<Encaminhamento> encaminhamentosPadroes = new ArrayList<>();

        encaminhamentosPadroes.add(new Encaminhamento("Favorável", this.itemDePauta));
        encaminhamentosPadroes.add(new Encaminhamento("Contrário", this.itemDePauta));
        encaminhamentosPadroes.add(new Encaminhamento("Abstenção", this.itemDePauta));

        this.itemDePauta.setEncaminhamentos(encaminhamentosPadroes);

        System.out.println(this.itemDePauta.toString());

        ItemDePauta itemSalvo = this.salvaItemDePauta(this.itemDePauta);

        this.eventPublisher.publishEvent(itemSalvo);
        return this.carregaItemDePautaPelo(itemSalvo.getId());
    }

    public ItemDePauta criaItemDePautaCustomizado(ItemDePauta itemDePauta) {
        this.itemDePauta = itemDePauta;

        this.itemDePauta.getEncaminhamentos();

        this.itemDePauta.adiciona(new Encaminhamento("Abstenção", this.itemDePauta));

        ItemDePauta itemSalvo = this.salvaItemDePauta(this.itemDePauta);

        this.eventPublisher.publishEvent(itemSalvo);
        return this.carregaItemDePautaPelo(itemSalvo.getId());
    }

    public List<ItemDePauta> listaItensDePauta(String termo, Long id) {
        return this.repository.findAllByDescricaoContainingOrRelatorContainingAndReuniaoId(termo, termo, id);
    }

    public ItemDePauta carregaItemDePautaPelo(Long id) {
        return this.repository.findById(id).get();
    }

    public ItemDePauta salvaItemDePauta(ItemDePauta itemDePauta) {
        return this.repository.save(itemDePauta);
    }

    public List<ItemDePauta> pegaPeloIdDaReuniao(Long id) {
        return this.repository.getItemDePautaByReuniaoIdOrderByOrdem(id);
    }

    public ItemDePauta editarUmItemDePautaPelo(Long id, String descricao, String relator) {
        ItemDePauta item = this.repository.findById(id).get();
        item.setDescricao(descricao);
        item.setRelator(relator);
        return this.repository.save(item);
    }

    public void deletarItemDePautaPelo(Long id) {
        ItemDePauta item = this.repository.findById(id).get();
        this.repository.delete(item);
    }

    public List<ItemDePauta> aoSelecionar(List<ItemDePauta> itemDePautas, long id) {

        return itemDePautas;
    }

    public void aoDesselecionar() {

    }

    public void onReordenar() {

    }

    public List<ItemDePauta> salvaEOrdena(List<ItemDePauta> itensDePautas) {
        Secretario secretario = new Secretario();
        itensDePautas = secretario.adicionaOrdemNos(itensDePautas);

        return this.repository.saveAll(itensDePautas);
    }

}
