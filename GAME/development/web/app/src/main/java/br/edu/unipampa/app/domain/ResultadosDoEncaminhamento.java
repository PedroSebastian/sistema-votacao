package br.edu.unipampa.app.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "resultados_do_encaminhamento")
public class ResultadosDoEncaminhamento implements Comparable<ResultadosDoEncaminhamento> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Encaminhamento encaminhamento;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "resultados_do_encaminhamento_votantes", insertable = false, updatable = false)
    private List<Membro> votantes;

    private Integer quantidadeDeVotos = 0;

    private Double porcentagem = 0.0;

    public ResultadosDoEncaminhamento computaUmVoto() {
        setQuantidadeDeVotos();
        return this;
    }

    public ResultadosDoEncaminhamento eAdiciona(Membro votante) {
        if(votantes == null){
           votantes = new ArrayList<>();
        }
        this.votantes.add(votante);

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Encaminhamento getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(Encaminhamento encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    public List<Membro> getVotantes() {
        return votantes;
    }

    public void setVotantes(List<Membro> votantes) {
        this.votantes = votantes;
    }

    public Integer getQuantidadeDeVotos() {
        return quantidadeDeVotos;
    }

    private void setQuantidadeDeVotos() {
        this.quantidadeDeVotos++;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {

        this.porcentagem = porcentagem;
    }

    public int compareTo(ResultadosDoEncaminhamento outroResultado){
        if (this.quantidadeDeVotos > outroResultado.quantidadeDeVotos) {
            return -1;
        }
        if (this.quantidadeDeVotos < outroResultado.quantidadeDeVotos){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ResultadosDoEncaminhamento{" +
                "id=" + id +
                ", quantidadeDeVotos=" + quantidadeDeVotos +
                ", porcentagem=" + porcentagem +
                '}';
    }



}
