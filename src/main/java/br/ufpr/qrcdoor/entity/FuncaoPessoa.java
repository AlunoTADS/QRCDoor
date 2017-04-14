package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "FuncaoPessoa" database table.
 * 
 */
@Entity
@Table(name="\"FuncaoPessoa\"")
@NamedQuery(name="FuncaoPessoa.findAll", query="SELECT f FROM FuncaoPessoa f")
public class FuncaoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idFuncaoPessoa\"")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataFim\"")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataInicio\"")
	private Date dataInicio;

	@Column(name="\"idFuncao\"")
	private Long idFuncao;

	@Column(name="\"idPessoa\"")
	private Long idPessoa;

	//bi-directional many-to-one association to Funcao
	@ManyToOne
	@JoinColumns({
		})
	private Funcao funcao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumns({
		})
	private Pessoa pessoa;

	public FuncaoPessoa() {
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

	public Long getIdFuncao() {
		return this.idFuncao;
	}

	public void setIdFuncao(Long idFuncao) {
		this.idFuncao = idFuncao;
	}

	public Long getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Funcao getFuncao() {
		return this.funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}