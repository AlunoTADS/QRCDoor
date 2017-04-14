package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "PermissaoPessoa" database table.
 * 
 */
@Entity
@Table(name="\"PermissaoPessoa\"")
@NamedQuery(name="PermissaoPessoa.findAll", query="SELECT p FROM PermissaoPessoa p")
public class PermissaoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idPermissaoPessoa\"")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataFim\"")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataInicio\"")
	private Date dataInicio;

	@Column(name="\"idPermissao\"")
	private Long idPermissao;

	@Column(name="\"idPessoa\"")
	private Long idPessoa;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumns({
		})
	private Permissao permissao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumns({
		})
	private Pessoa pessoa;

	public PermissaoPessoa() {
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

	public Long getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
	}

	public Long getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
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