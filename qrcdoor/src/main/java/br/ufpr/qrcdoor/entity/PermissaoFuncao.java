package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "PermissaoFuncao" database table.
 * 
 */
@Entity
@Table(name="permissaofuncao")
@NamedQuery(name="PermissaoFuncao.findAll", query="SELECT p FROM PermissaoFuncao p")
public class PermissaoFuncao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="permissaofuncao_idpermissaofuncao_seq", sequenceName="permissaofuncao_idpermissaofuncao_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissaofuncao_idpermissaofuncao_seq")
	@Column(name="idpermissaofuncao", unique=true, nullable=false)
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

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumn(name="idpermissao")
	private Permissao permissao;

	public PermissaoFuncao() {
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

	public Permissao getPermissao() {
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

}