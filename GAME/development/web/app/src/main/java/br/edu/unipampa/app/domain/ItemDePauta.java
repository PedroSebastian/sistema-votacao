package br.edu.unipampa.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "itens_de_pauta")
public class ItemDePauta implements Serializable, Comparable<ItemDePauta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Votacao primeiroTurno;

    @JsonIgnore
    @OneToOne(optional = true, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Votacao segundoTurno;

    @NotEmpty
    @Size(min=3)
    private String relator;

    @NotEmpty
    @Size(min=3)
    private String descricao;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Reuniao reuniao;

    @Column(nullable = true)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Encaminhamento> encaminhamentos;

    @NotNull
    private boolean temSegundoTurno;
    private Integer ordem;

    public void adiciona(Encaminhamento encaminhamento) {
        if (this.encaminhamentos == null) {
            this.encaminhamentos = new ArrayList<>();
        }
        if (encaminhamento == null) {
            throw new IllegalArgumentException("O encaminhamento não pode ser nulo!");
        }

        this.encaminhamentos.add(encaminhamento);
    }

    public void removeEncaminhamento(Encaminhamento encaminhamento) {
        if (this.encaminhamentos == null) {
            this.encaminhamentos = new ArrayList<>();
        }

        if (this.encaminhamentos.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("A lista de encaminhamentos está vazia!");
        }

        this.encaminhamentos.remove(encaminhamento);
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public void cancelaVotacao(){
        this.primeiroTurno = null;
        this.segundoTurno = null;
    }

    public Long getId() {
        return id;
    }

    public Votacao getPrimeiroTurno() {
        return primeiroTurno;
    }

    public void setPrimeiroTurno(Votacao primeiroTurno) {
        primeiroTurno.setTurno(Turno.PRIMEIRO);
        this.primeiroTurno = primeiroTurno;
    }

    public Votacao getSegundoTurno() {
        return segundoTurno;
    }

    public void setSegundoTurno(Votacao segundoTurno) {
        if (this.isTemSegundoTurno() == true) {
            this.segundoTurno = segundoTurno;
            this.segundoTurno.setTurno(Turno.SEGUNDO);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getRelator() {
        return relator;
    }

    public void setRelator(String relator) {
        if (relator == null || relator == "") {
            throw new IllegalArgumentException();
        } else {
            this.relator = relator;
        }
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

    public boolean isTemSegundoTurno() {
        return this.temSegundoTurno;
    }

    public void setTemSegundoTurno(boolean temSegundoTurno) {
        this.temSegundoTurno = temSegundoTurno;
    }

    public List<Encaminhamento> getEncaminhamentos() {
        return encaminhamentos;
    }

    public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
        this.encaminhamentos = encaminhamentos;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        if (reuniao == null) {
            throw new IllegalArgumentException();
        } else {
            this.reuniao = reuniao;
        }
    }

    @Override
    public String toString() {
        return "ItemDePauta{" +
                "id=" + id +
                ", relator='" + relator + '\'' +
                ", descricao='" + descricao + '\'' +
                ", encaminhamentos=" + encaminhamentos +
                ", temSegundoTurno=" + temSegundoTurno +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDePauta that = (ItemDePauta) o;
        return isTemSegundoTurno() == that.isTemSegundoTurno() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRelator(), that.getRelator()) &&
                Objects.equals(getDescricao(), that.getDescricao()) &&
                Objects.equals(getEncaminhamentos(), that.getEncaminhamentos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRelator(), getDescricao(), getEncaminhamentos(), isTemSegundoTurno());
    }

    @Override
    public int compareTo(ItemDePauta o) {
        if (this.getOrdem() > o.getOrdem()) {
            return 1;
        }
        if (this.getOrdem() < o.getOrdem()) {
            return -1;
        }
        return 0;
    }

}
