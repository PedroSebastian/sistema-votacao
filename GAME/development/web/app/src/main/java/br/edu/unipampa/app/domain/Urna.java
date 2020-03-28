package br.edu.unipampa.app.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "urnas")
public class Urna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Voto> votosParaContagem = new ArrayList<>();

    public void recebe(Voto voto) {
        if (this.votosParaContagem == null) {
            this.votosParaContagem = new ArrayList<>();
        }

        this.votosParaContagem.add(voto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Voto> getVotosParaContagem() {
        return votosParaContagem;
    }

    public void setVotosParaContagem(List<Voto> votosParaContagem) {
        this.votosParaContagem = votosParaContagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Urna urna = (Urna) o;
        return Objects.equals(getId(), urna.getId()) &&
                Objects.equals(getVotosParaContagem(), urna.getVotosParaContagem());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getVotosParaContagem());
    }

}
