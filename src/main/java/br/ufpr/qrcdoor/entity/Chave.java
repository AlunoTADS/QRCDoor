package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Chave" database table.
 * 
 */
@Entity
@Table(name="\"Chave\"")
@NamedQuery(name="Chave.findAll", query="SELECT c FROM Chave c")
public class Chave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idChave\"")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataFim\"")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataInicio\"")
	private Date dataInicio;

	private String descricao;

	@Column(name="\"idPessoa\"")
	private Long idPessoa;

	private String situacao;

	//bi-directional many-to-one association to Acesso
	@OneToMany(mappedBy="chave")
	private List<Acesso> acessos;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumns({
		})
	private Pessoa pessoa;

	//bi-directional one-to-one association to QRCode
	@OneToOne(mappedBy="chave")
	private QRCode qrcode;

	//bi-directional one-to-one association to Senha
	@OneToOne(mappedBy="chave")
	private Senha senha;

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

	public Long getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Acesso> getAcessos() {
		return this.acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	public Acesso addAcesso(Acesso acesso) {
		getAcessos().add(acesso);
		acesso.setChave(this);

		return acesso;
	}

	public Acesso removeAcesso(Acesso acesso) {
		getAcessos().remove(acesso);
		acesso.setChave(null);

		return acesso;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public QRCode getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(QRCode qrcode) {
		this.qrcode = qrcode;
	}

	public Senha getSenha() {
		return this.senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

}