package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "Acesso" database table.
 * 
 */
@Entity
@Table(name="\"Acesso\"")
@NamedQuery(name="Acesso.findAll", query="SELECT a FROM Acesso a")
public class Acesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idAcesso\"")
	private Long id;

	@Column(name="\"dataHoraAbertura\"")
	private Timestamp dataHoraAbertura;

	@Column(name="\"dataHoraFechamento\"")
	private Timestamp dataHoraFechamento;

	@Column(name="\"idChave\"")
	private Long idChave;

	@Column(name="\"idEstrutura\"")
	private Long idEstrutura;

	@Column(name="\"idPermissao\"")
	private Long idPermissao;

	@Column(name="\"idPessoa\"")
	private Long idPessoa;

	private String tipo;

	//bi-directional many-to-one association to Chave
	@ManyToOne
	@JoinColumns({
		})
	private Chave chave;

	//bi-directional many-to-one association to Estrutura
	@ManyToOne
	@JoinColumns({
		})
	private Estrutura estrutura;

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

	public Acesso() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataHoraAbertura() {
		return this.dataHoraAbertura;
	}

	public void setDataHoraAbertura(Timestamp dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public Timestamp getDataHoraFechamento() {
		return this.dataHoraFechamento;
	}

	public void setDataHoraFechamento(Timestamp dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}

	public Long getIdChave() {
		return this.idChave;
	}

	public void setIdChave(Long idChave) {
		this.idChave = idChave;
	}

	public Long getIdEstrutura() {
		return this.idEstrutura;
	}

	public void setIdEstrutura(Long idEstrutura) {
		this.idEstrutura = idEstrutura;
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