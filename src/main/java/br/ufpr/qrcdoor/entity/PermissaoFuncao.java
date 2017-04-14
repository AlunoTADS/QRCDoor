package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "PermissaoFuncao" database table.
 * 
 */
@Entity
@Table(name="\"PermissaoFuncao\"")
@NamedQuery(name="PermissaoFuncao.findAll", query="SELECT p FROM PermissaoFuncao p")
public class PermissaoFuncao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idPermissaoFuncao\"")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataFim\"")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataInicio\"")
	private Date dataInicio;

	@Column(name="\"idFuncao\"")
	private Long idFuncao;

	@Column(name="\"idPermissao\"")
	private Long idPermissao;

	//bi-directional many-to-one association to Funcao
	@ManyToOne
	@JoinColumns({
		})
	private Funcao funcao;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumns({
		})
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

	public Long getIdFuncao() {
		return this.idFuncao;
	}

	public void setIdFuncao(Long idFuncao) {
		this.idFuncao = idFuncao;
	}

	public Long getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
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