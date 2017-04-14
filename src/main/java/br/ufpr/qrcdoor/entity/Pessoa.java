package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Pessoa" database table.
 * 
 */
@Entity
@Table(name="\"Pessoa\"")
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idPessoa\"")
	private Long id;

	private String cpf;

	private byte[] foto;

	@Column(name="\"fotoExtensao\"")
	private String fotoExtensao;

	@Column(name="\"idUfRg\"")
	private Long idUfRg;

	private String nome;

	private String rg;

	private String situacao;

	//bi-directional many-to-one association to Acesso
	@OneToMany(mappedBy="pessoa")
	private List<Acesso> acessos;

	//bi-directional many-to-one association to Chave
	@OneToMany(mappedBy="pessoa")
	private List<Chave> chaves;

	//bi-directional many-to-one association to FuncaoPessoa
	@OneToMany(mappedBy="pessoa")
	private List<FuncaoPessoa> funcaoPessoas;

	//bi-directional many-to-one association to PermissaoPessoa
	@OneToMany(mappedBy="pessoa")
	private List<PermissaoPessoa> permissaoPessoas;

	//bi-directional many-to-one association to Uf
	@ManyToOne
	@JoinColumns({
		})
	private Uf uf;

	public Pessoa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Long getIdUfRg() {
		return this.idUfRg;
	}

	public void setIdUfRg(Long idUfRg) {
		this.idUfRg = idUfRg;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
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
		acesso.setPessoa(this);

		return acesso;
	}

	public Acesso removeAcesso(Acesso acesso) {
		getAcessos().remove(acesso);
		acesso.setPessoa(null);

		return acesso;
	}

	public List<Chave> getChaves() {
		return this.chaves;
	}

	public void setChaves(List<Chave> chaves) {
		this.chaves = chaves;
	}

	public Chave addChave(Chave chave) {
		getChaves().add(chave);
		chave.setPessoa(this);

		return chave;
	}

	public Chave removeChave(Chave chave) {
		getChaves().remove(chave);
		chave.setPessoa(null);

		return chave;
	}

	public List<FuncaoPessoa> getFuncaoPessoas() {
		return this.funcaoPessoas;
	}

	public void setFuncaoPessoas(List<FuncaoPessoa> funcaoPessoas) {
		this.funcaoPessoas = funcaoPessoas;
	}

	public FuncaoPessoa addFuncaoPessoa(FuncaoPessoa funcaoPessoa) {
		getFuncaoPessoas().add(funcaoPessoa);
		funcaoPessoa.setPessoa(this);

		return funcaoPessoa;
	}

	public FuncaoPessoa removeFuncaoPessoa(FuncaoPessoa funcaoPessoa) {
		getFuncaoPessoas().remove(funcaoPessoa);
		funcaoPessoa.setPessoa(null);

		return funcaoPessoa;
	}

	public List<PermissaoPessoa> getPermissaoPessoas() {
		return this.permissaoPessoas;
	}

	public void setPermissaoPessoas(List<PermissaoPessoa> permissaoPessoas) {
		this.permissaoPessoas = permissaoPessoas;
	}

	public PermissaoPessoa addPermissaoPessoa(PermissaoPessoa permissaoPessoa) {
		getPermissaoPessoas().add(permissaoPessoa);
		permissaoPessoa.setPessoa(this);

		return permissaoPessoa;
	}

	public PermissaoPessoa removePermissaoPessoa(PermissaoPessoa permissaoPessoa) {
		getPermissaoPessoas().remove(permissaoPessoa);
		permissaoPessoa.setPessoa(null);

		return permissaoPessoa;
	}

	public Uf getUf() {
		return this.uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}