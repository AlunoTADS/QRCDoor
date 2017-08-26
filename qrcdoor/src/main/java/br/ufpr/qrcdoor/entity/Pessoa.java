package br.ufpr.qrcdoor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the "Pessoa" database table.
 * 
 */
@Entity
@Table(name="pessoa")
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="pessoa_idpessoa_seq", sequenceName="pessoa_idpessoa_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_idpessoa_seq")
	@Column(name="idpessoa", unique=true, nullable=false)
	private Long id;

	@Column(length=32)
	private String cpf;

	@Lob
	private byte[] foto;

	@Column(name="fotoextensao", length=16)
	private String fotoExtensao;

	@Column(length=255, nullable=false)
	private String nome;

	@Column(length=32, nullable=false)
	private String rg;

	@Column(length=1, nullable=false)
	private String situacao;

	//bi-directional many-to-one association to Uf
	@ManyToOne
	@JoinColumn(name="idufrg")
	private Uf uf;

	public Pessoa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFotoExtensao() {
		return this.fotoExtensao;
	}

	public void setFotoExtensao(String fotoExtensao) {
		this.fotoExtensao = fotoExtensao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Uf getUf() {
		return this.uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}