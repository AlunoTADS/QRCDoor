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
 * The persistent class for the "Acesso" database table.
 * 
 */
@Entity
@Table(name="acesso")
@NamedQuery(name="Acesso.findAll", query="SELECT a FROM Acesso a")
public class Acesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="acesso_idacesso_seq", sequenceName="acesso_idacesso_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="acesso_idacesso_seq")
	@Column(name="idacesso", unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahoraabertura")
	private Date dataHoraAbertura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahorafechamento")
	private Date dataHoraFechamento;

	@Column(length=1)
	private String tipo;

	//bi-directional many-to-one association to Chave
	@ManyToOne
	@JoinColumn(name="idchave")
	private Chave chave;

	//bi-directional many-to-one association to Estrutura
	@ManyToOne
	@JoinColumn(name="idestrutura")
	private Estrutura estrutura;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumn(name="idpermissao")
	private Permissao permissao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="idpessoa")
	private Pessoa pessoa;

	public Acesso() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHoraAbertura() {
		return this.dataHoraAbertura;
	}

	public void setDataHoraAbertura(Date dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public Date getDataHoraFechamento() {
		return this.dataHoraFechamento;
	}

	public void setDataHoraFechamento(Date dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Chave getChave() {
		return this.chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public Estrutura getEstrutura() {
		return this.estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public Permissao getPermissao() {
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}