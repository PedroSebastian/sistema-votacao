package br.edu.unipampa.app.domain;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "pareceres")
public class Parecer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descricaoParecer;

    @Enumerated
    private StatusParecer statusParecer = StatusParecer.SEMPARECER;

    //@OneToOne
   // private ItemDePauta itemDePauta;

    public Parecer() {

    }
    public String getDescricaoParecer() {
        return descricaoParecer;
    }


    public void setDescricaoParecer(String descricaoParecer) {
        if (descricaoParecer == null || descricaoParecer == "") {
            throw new IllegalArgumentException();
        } else {
            this.descricaoParecer = descricaoParecer;
        }
    }

    public StatusParecer getStatusParecer() {
        return statusParecer;
    }

    public void setStatusParecer(StatusParecer statusParecer) {
        this.statusParecer = statusParecer;
    }








}
