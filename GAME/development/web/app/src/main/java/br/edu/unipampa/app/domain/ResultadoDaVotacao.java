package br.edu.unipampa.app.domain;

import javax.persistence.*;
import java.util.*;

@Entity(name = "resultados")
public class ResultadoDaVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ResultadosDoEncaminhamento> resultadosDoEncaminhamentos;

    public ResultadoDaVotacao() {

    }

    public void adicionaUm(ResultadosDoEncaminhamento resultadosDoEncaminhamento){
        if(this.resultadosDoEncaminhamentos == null){
            this.resultadosDoEncaminhamentos = new ArrayList<>();
        }
        this.resultadosDoEncaminhamentos.add(resultadosDoEncaminhamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ResultadosDoEncaminhamento> getResultadosDoEncaminhamentos() {
        return resultadosDoEncaminhamentos;
    }

    public void setResultadosDoEncaminhamentos(List<ResultadosDoEncaminhamento> resultadosDoEncaminhamentos) {
        this.resultadosDoEncaminhamentos = resultadosDoEncaminhamentos;
    }

    public void ordenar(){
        Collections.sort(this.resultadosDoEncaminhamentos);
    }

    @Override
    public String toString() {
        return "ResultadoDaVotacao{" +
                "id=" + id +
                ", resultadosDoEncaminhamentos=" + resultadosDoEncaminhamentos +
                '}';
    }

}
