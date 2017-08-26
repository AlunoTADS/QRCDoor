package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Ambiente" database table.
 * 
 */
@Entity
@Table(name="ambiente")
@NamedQuery(name="Ambiente.findAll", query="SELECT a FROM Ambiente a")
public class Ambiente extends Estrutura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="quantidademaximapessoas", nullable=false)
	private Integer quantidadeMaximaPessoas;

	public Ambiente() {
	}

	public Integer getQuantidadeMaximaPessoas() {
		return this.quantidadeMaximaPessoas;
	}

	public void setQuantidadeMaximaPessoas(Integer quantidadeMaximaPessoas) {
		this.quantidadeMaximaPessoas = quantidadeMaximaPessoas;
	}

}