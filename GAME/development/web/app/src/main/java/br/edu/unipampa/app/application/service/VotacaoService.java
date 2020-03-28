package br.edu.unipampa.app.application.service;

import br.edu.unipampa.app.application.resources.ReuniaoResource;
import br.edu.unipampa.app.domain.*;
import br.edu.unipampa.app.infrastructure.components.ApplicationContextProvider;
import br.edu.unipampa.app.infrastructure.repository.VotacaoRepository;
import br.edu.unipampa.app.infrastructure.repository.VotoRepository;
import org.atmosphere.util.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class VotacaoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private ItemDePautaService itemDePautaService;

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private ApplicationContextProvider context;

    @Autowired
    public ApplicationEventPublisher eventPublisher;

    private Votacao primeiroTurno;
    private Votacao segundoTurno;

    private List<Membro> conectados;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Voto recebeNovo(Voto voto) {
        final Voto[] novoVoto = new Voto[1];

        this.conectados.forEach(membro -> {
            if (membro.getId() == voto.getVotante().getId()) {
                novoVoto[0] = this.votoRepository.save(voto);

                if (this.primeiroTurno.getTurno() == Turno.PRIMEIRO &&
                        this.primeiroTurno.getStatus() == StatusVotacao.ABERTA) {
                    this.primeiroTurno.registraVotoNaUrna(novoVoto[0]);
                    this.primeiroTurno = this.votacaoRepository.save(this.primeiroTurno);
                } else if (this.primeiroTurno.getItemDePauta().isTemSegundoTurno()) {
                    if (this.segundoTurno.getStatus() == StatusVotacao.ABERTA) {
                        this.segundoTurno.registraVotoNaUrna(novoVoto[0]);
                        this.segundoTurno = this.votacaoRepository.save(this.segundoTurno);
                    }
                }

                this.verificaSeTodosVotaram();
            }
        });

        return novoVoto[0];
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Votacao iniciaVotacao(Long itemDePautaId) {
//        if (this.primeiroTurno == null) {
            ItemDePauta item = this.itemDePautaService.carregaItemDePautaPelo(itemDePautaId);
            this.primeiroTurno = new Votacao(new Urna(), item);
            item.setPrimeiroTurno(this.primeiroTurno);
            this.itemDePautaService.salvaItemDePauta(item);
            this.primeiroTurno.setStatus(StatusVotacao.ABERTA);
            this.primeiroTurno.setVotacaoAberta(true);

            this.primeiroTurno = this.votacaoRepository.save(this.primeiroTurno);

            this.conectados = this.pegaMembrosConectados();
//        } else {
//
//        }

        return this.primeiroTurno;
    }

    public void cancelaVotacao(Long itemDePautaId) {

        ItemDePauta itemDePauta = this.itemDePautaService.carregaItemDePautaPelo(itemDePautaId);
        itemDePauta.cancelaVotacao();
        this.itemDePautaService.salvaItemDePauta(itemDePauta);

    }

    public void encerraVotacao() {

    }

    public void encaminhaParaVotacaoEmSegundoTurno(Long id) {
        ItemDePauta itemDePauta = this.itemDePautaService.carregaItemDePautaPelo(id);

        this.segundoTurno = new Votacao(new Urna(), itemDePauta);
        this.segundoTurno.setStatus(StatusVotacao.ABERTA);

        System.out.println("Tem? " + itemDePauta.isTemSegundoTurno());

        itemDePauta.setSegundoTurno(this.segundoTurno);
        itemDePauta = this.itemDePautaService.salvaItemDePauta(itemDePauta);

        List<Encaminhamento> encaminhamentos = new ArrayList<>();
        encaminhamentos.add(itemDePauta.getEncaminhamentos().get(0));
        encaminhamentos.add(itemDePauta.getEncaminhamentos().get(1));

        itemDePauta.setDescricao(itemDePauta.getDescricao() + " (Segundo Turno).");
        itemDePauta.setEncaminhamentos(encaminhamentos);

        this.eventPublisher.publishEvent(itemDePauta);
    }


    public void notificarResultadoViaPush() throws Exception {
        String summary = "Resultado";
        String detail = "Votação encerrada.";
        String CHANNEL = "/votacao-finalizada";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeJavaScript(summary), StringEscapeUtils.escapeJavaScript(detail)));
    }

    private void verificaSeTodosVotaram() {
        if (this.primeiroTurno.getStatus() != StatusVotacao.FINALIZADA) {
            if (this.conectados.size() == this.primeiroTurno.getUrna().getVotosParaContagem().size()) {
                System.out.println("TODOS VOTARAM");
                try {
                    this.primeiroTurno = this.processaResultadoDaVotacao(this.primeiroTurno);

                    this.notificarResultadoViaPush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (this.primeiroTurno.getItemDePauta().isTemSegundoTurno()) {
            if (this.segundoTurno.getStatus() == StatusVotacao.ABERTA) {
                System.out.println("TODOS VOTARAM - SEGUNDO TURNO");
                try {
                    this.segundoTurno = this.processaResultadoDaVotacao(this.segundoTurno);

                    this.notificarResultadoViaPush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private List<Membro> pegaMembrosConectados() {
        List<Membro> membros = new ArrayList();

        Object reuniaoBean = (ReuniaoResource) this.context.getContext().getBean(ReuniaoResource.class);
        ((ReuniaoResource) reuniaoBean).getReunioes().forEach(reuniao -> {
            if (reuniao.getReuniaoId() == this.primeiroTurno.getItemDePauta().getReuniao().getId()) {
                membros.add(reuniao.getMembro());
            }
        });
        
        return membros;
    }

    public Votacao mostraResultados() {
        if (this.primeiroTurno.getStatus() != StatusVotacao.FINALIZADA) {
            return null;
        }
        return this.primeiroTurno;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    protected Votacao processaResultadoDaVotacao(Votacao votacao) {
        votacao.setStatus(StatusVotacao.FINALIZADA);
        ApuracaoDaVotacao apuracaoDaVotacao = new ApuracaoDaVotacao(votacao);
        votacao.setResultado(apuracaoDaVotacao.realizaApuracao());

        System.out.println(votacao);

        return this.votacaoRepository.save(votacao);
    }

}
