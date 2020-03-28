package br.edu.unipampa.app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "votos")
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Membro votante;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Encaminhamento escolha;

    public Voto() {

    }

    public Voto(Membro membro, Encaminhamento encaminhamento) {
        this.setVotante(membro);
        this.setEscolha(encaminhamento);
    }

    public Long getId() {
        return id;
    }

    public Membro getVotante() {
        return votante;
    }

    private void setVotante(Membro membro) {
        if(membro == null || membro.getNome() == null) {
            throw new IllegalArgumentException();
        } else {
            this.votante = membro;
        }
    }

    public Encaminhamento pegaItemDeVoto() {
        return escolha;
    }

    public Encaminhamento getEscolha() {
        return escolha;
    }

    private void setEscolha(Encaminhamento escolha) {
        if(escolha == null){
            throw new IllegalArgumentException();
        } else {
            this.escolha = escolha;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        if(((Voto) o).getVotante() == this.getVotante()) return true;
        return Objects.equals(votante, voto.votante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, votante);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", votante=" + votante +
                ", escolha=" + escolha +
                '}';
    }

}
