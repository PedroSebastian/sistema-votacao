package br.edu.unipampa.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "encaminhamentos")
public class Encaminhamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String descricao;

	private int somaVoto = 0;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itens_de_pauta_id")
	private ItemDePauta itemDePauta;

	public Encaminhamento() {

	}

	public Encaminhamento(String descricao, ItemDePauta itemDePauta){
	    this.setDescricao(descricao);
	    this.setItemDePauta(itemDePauta);
    }

	public Long getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if(descricao == null || descricao == "") {
			throw new IllegalArgumentException();
		} else {
			this.descricao = descricao;
		}
	}

	public ItemDePauta getItemDePauta() {
		return itemDePauta;
	}

	public void setItemDePauta(ItemDePauta itemDePauta) {
		this.itemDePauta = itemDePauta;
	}

	public void setSomaVoto() {
		somaVoto++;
	}

	public int pegaQuantidadeVoto() {
		return somaVoto;
	}

	@Override
	public String toString() {
		return "Encaminhamento{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Encaminhamento that = (Encaminhamento) o;
		return somaVoto == that.somaVoto &&
				Objects.equals(getId(), that.getId()) &&
				Objects.equals(getDescricao(), that.getDescricao());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(), getDescricao(), somaVoto, getItemDePauta());
	}

}
