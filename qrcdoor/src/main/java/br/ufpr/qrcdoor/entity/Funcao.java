package br.ufpr.qrcdoor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the "Funcao" database table.
 * 
 */
@Entity
@Table(name="funcao")
@NamedQuery(name="Funcao.findAll", query="SELECT f FROM Funcao f")
public class Funcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="funcao_idfuncao_seq", sequenceName="funcao_idfuncao_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="funcao_idfuncao_seq")
	@Column(name="idfuncao", unique=true, nullable=false)
	private Long id;

	@Column(length=255, nullable=false)
	private String descricao;

	@Column(length=1, nullable=false)
	private String situacao;

	public Funcao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}