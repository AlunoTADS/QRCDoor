package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Estrutura" database table.
 * 
 */
@Entity
@Table(name="\"Estrutura\"")
@NamedQuery(name="Estrutura.findAll", query="SELECT e FROM Estrutura e")
public class Estrutura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idEstrutura\"")
	private Long id;

	private String descricao;

	private byte[] foto;

	@Column(name="\"fotoExtensao\"")
	private String fotoExtensao;

	@Column(name="\"idEstruturaPai\"")
	private Long idEstruturaPai;

	private String situacao;

	@Column(name="\"tempoMaximoAbertura\"")
	private Integer tempoMaximoAbertura;

	//bi-directional many-to-one association to Acesso
	@OneToMany(mappedBy="estrutura")
	private List<Acesso> acessos;

	//bi-directional one-to-one association to Ambiente
	@OneToOne(mappedBy="estrutura")
	private Ambiente ambiente;

	//bi-directional one-to-one association to Cofre
	@OneToOne(mappedBy="estrutura")
	private Cofre cofre;

	//bi-directional many-to-one association to Estrutura
	@ManyToOne
	@JoinColumns({
		})
	private Estrutura estrutura;

	//bi-directional many-to-one association to Estrutura
	@OneToMany(mappedBy="estrutura")
	private List<Estrutura> estruturas;

	//bi-directional many-to-one association to Permissao
	@OneToMany(mappedBy="estrutura")
	private List<Permissao> permissaos;

	public Estrutura() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFotoExtensao() {
		return this.fotoExtensao;
	}

	public void setFotoExtensao(String fotoExtensao) {
		this.fotoExtensao = fotoExtensao;
	}

	public Long getIdEstruturaPai() {
		return this.idEstruturaPai;
	}

	public void setIdEstruturaPai(Long idEstruturaPai) {
		this.idEstruturaPai = idEstruturaPai;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Integer getTempoMaximoAbertura() {
		return this.tempoMaximoAbertura;
	}

	public void setTempoMaximoAbertura(Integer tempoMaximoAbertura) {
		this.tempoMaximoAbertura = tempoMaximoAbertura;
	}

	public List<Acesso> getAcessos() {
		return this.acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	public Acesso addAcesso(Acesso acesso) {
		getAcessos().add(acesso);
		acesso.setEstrutura(this);

		return acesso;
	}

	public Acesso removeAcesso(Acesso acesso) {
		getAcessos().remove(acesso);
		acesso.setEstrutura(null);

		return acesso;
	}

	public Ambiente getAmbiente() {
		return this.ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Cofre getCofre() {
		return this.cofre;
	}

	public void setCofre(Cofre cofre) {
		this.cofre = cofre;
	}

	public Estrutura getEstrutura() {
		return this.estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public List<Estrutura> getEstruturas() {
		return this.estruturas;
	}

	public void setEstruturas(List<Estrutura> estruturas) {
		this.estruturas = estruturas;
	}

	public Estrutura addEstrutura(Estrutura estrutura) {
		getEstruturas().add(estrutura);
		estrutura.setEstrutura(this);

		return estrutura;
	}

	public Estrutura removeEstrutura(Estrutura estrutura) {
		getEstruturas().remove(estrutura);
		estrutura.setEstrutura(null);

		return estrutura;
	}

	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}

	public Permissao addPermissao(Permissao permissao) {
		getPermissaos().add(permissao);
		permissao.setEstrutura(this);

		return permissao;
	}

	public Permissao removePermissao(Permissao permissao) {
		getPermissaos().remove(permissao);
		permissao.setEstrutura(null);

		return permissao;
	}

}