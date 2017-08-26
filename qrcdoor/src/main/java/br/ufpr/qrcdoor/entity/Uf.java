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
 * The persistent class for the "Uf" database table.
 * 
 */
@Entity
@Table(name="uf")
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="uf_iduf_seq", sequenceName="uf_iduf_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="uf_iduf_seq")
	@Column(name="iduf", unique=true, nullable=false)
	private Long id;

	@Column(length=255, nullable=false)
	private String nome;

	@Column(length=2, nullable=false)
	private String sigla;

	@Column(length=1, nullable=false)
	private String situacao;

	public Uf() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}