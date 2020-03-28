package br.edu.unipampa.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Pedro Sebastian on 15/05/2018.
 */
@Entity(name = "membros")
public class Membro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "reunioes_membros",
            joinColumns = @JoinColumn(name = "membros_id"),
            inverseJoinColumns = @JoinColumn(name = "reunioes_id")
    )
    private List<Reuniao> reunioes;

    @Column(unique = true)
    private String token;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome == "") {
            throw new IllegalArgumentException();
        } else {
            this.nome = nome;
        }
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public void setReunioes(List<Reuniao> reunioes) {
        this.reunioes = reunioes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
