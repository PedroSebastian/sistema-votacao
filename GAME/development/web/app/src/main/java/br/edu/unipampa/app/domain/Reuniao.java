package br.edu.unipampa.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Pedro Sebastian on 15/05/2018.
 */
@Entity(name = "reunioes")
public class Reuniao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate data;
    private LocalTime horario;
    private Boolean estaAberta;

    @Enumerated
    private TipoDaReuniao tipoDaReuniao;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "reunioes", fetch = FetchType.LAZY)
    private List<Membro> membros;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Moderador moderador;

    @JsonIgnore
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ItemDePauta> itensDePauta;

    public Long getId() {
        return id;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(Moderador moderador) {
        if (moderador == null) {
            throw new IllegalArgumentException();
        } else {
            this.moderador = moderador;
        }
    }

    public List<ItemDePauta> getItensDePauta() {
        return itensDePauta;
    }

    public void setItensDePauta(List<ItemDePauta> itensDePauta) {
        this.itensDePauta = itensDePauta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao == "") {
            throw new IllegalArgumentException();
        } else {
            this.descricao = descricao;
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            this.data = data;
        }
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        if (horario == null) {
            throw new IllegalArgumentException();
        } else {
            this.horario = horario;
        }
    }

    public void setReuniaoOrdinaria(TipoDaReuniao tipoDaReuniao) {
        this.tipoDaReuniao = TipoDaReuniao.ORDINARIA;
    }

    public void setReuniaoExtraordinaria(TipoDaReuniao tipoDaReuniao) {
        this.tipoDaReuniao = TipoDaReuniao.EXTRAORDINARIA;
    }

    public Boolean getEstaAberta() {
        return estaAberta;
    }

    public void setEstaAberta(Boolean estaAberta) {
        this.estaAberta = estaAberta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDaReuniao getTipoDaReuniao() {
        return tipoDaReuniao;
    }

    public void setTipoDaReuniao(TipoDaReuniao tipoDaReuniao) {
        this.tipoDaReuniao = tipoDaReuniao;
    }

    @Override
    public String toString() {
        return "Reuniao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", horario=" + horario +
                ", estaAberta=" + estaAberta +
                ", moderador=" + moderador +
                '}';
    }

}
