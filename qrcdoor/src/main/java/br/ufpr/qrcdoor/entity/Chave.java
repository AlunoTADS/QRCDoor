package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "Chave" database table.
 * 
 */
@Entity
@Table(name="chave")
@NamedQuery(name="Chave.findAll", query="SELECT c FROM Chave c")
public class Chave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="chave_idchave_seq", sequenceName="chave_idchave_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="chave_idchave_seq")
	@Column(name="idchave", unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="datafim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="datainicio")
	private Date dataInicio;

	@Column(length=255)
	private String descricao;

	@Column(length=1, nullable=false)
	private String situacao;
	
	@Column(length=64)
	private String assinatura;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="idpessoa")
	private Pessoa pessoa;

	public Chave() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
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

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

}