package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "FuncaoPessoa" database table.
 * 
 */
@Entity
@Table(name="funcaopessoa")
@NamedQuery(name="FuncaoPessoa.findAll", query="SELECT f FROM FuncaoPessoa f")
public class FuncaoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="funcaopessoa_idfuncaopessoa_seq", sequenceName="funcaopessoa_idfuncaopessoa_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="funcaopessoa_idfuncaopessoa_seq")
	@Column(name="idfuncaopessoa", unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="datafim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="datainicio")
	private Date dataInicio;

	//bi-directional many-to-one association to Funcao
	@ManyToOne
	@JoinColumn(name="idfuncao")
	private Funcao funcao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="idpessoa")
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