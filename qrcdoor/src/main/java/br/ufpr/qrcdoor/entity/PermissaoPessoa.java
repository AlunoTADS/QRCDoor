package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "PermissaoPessoa" database table.
 * 
 */
@Entity
@Table(name="permissaopessoa")
@NamedQuery(name="PermissaoPessoa.findAll", query="SELECT p FROM PermissaoPessoa p")
public class PermissaoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="permissaopessoa_idpermissaopessoa_seq", sequenceName="permissaopessoa_idpermissaopessoa_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissaopessoa_idpermissaopessoa_seq")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="datafim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="datainicio")
	private Date dataInicio;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumn(name="idpermissao")
	private Permissao permissao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="idpessoa")
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