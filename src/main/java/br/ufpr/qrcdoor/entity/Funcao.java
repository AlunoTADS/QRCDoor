package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Funcao" database table.
 * 
 */
@Entity
@Table(name="\"Funcao\"")
@NamedQuery(name="Funcao.findAll", query="SELECT f FROM Funcao f")
public class Funcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idFuncao\"")
	private Long id;

	private String descricao;

	private String situacao;

	//bi-directional many-to-one association to FuncaoPessoa
	@OneToMany(mappedBy="funcao")
	private List<FuncaoPessoa> funcaoPessoas;

	//bi-directional many-to-one association to PermissaoFuncao
	@OneToMany(mappedBy="funcao")
	private List<PermissaoFuncao> permissaoFuncaos;

	public Funcao() {
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

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<FuncaoPessoa> getFuncaoPessoas() {
		return this.funcaoPessoas;
	}

	public void setFuncaoPessoas(List<FuncaoPessoa> funcaoPessoas) {
		this.funcaoPessoas = funcaoPessoas;
	}

	public FuncaoPessoa addFuncaoPessoa(FuncaoPessoa funcaoPessoa) {
		getFuncaoPessoas().add(funcaoPessoa);
		funcaoPessoa.setFuncao(this);

		return funcaoPessoa;
	}

	public FuncaoPessoa removeFuncaoPessoa(FuncaoPessoa funcaoPessoa) {
		getFuncaoPessoas().remove(funcaoPessoa);
		funcaoPessoa.setFuncao(null);

		return funcaoPessoa;
	}

	public List<PermissaoFuncao> getPermissaoFuncaos() {
		return this.permissaoFuncaos;
	}

	public void setPermissaoFuncaos(List<PermissaoFuncao> permissaoFuncaos) {
		this.permissaoFuncaos = permissaoFuncaos;
	}

	public PermissaoFuncao addPermissaoFuncao(PermissaoFuncao permissaoFuncao) {
		getPermissaoFuncaos().add(permissaoFuncao);
		permissaoFuncao.setFuncao(this);

		return permissaoFuncao;
	}

	public PermissaoFuncao removePermissaoFuncao(PermissaoFuncao permissaoFuncao) {
		getPermissaoFuncaos().remove(permissaoFuncao);
		permissaoFuncao.setFuncao(null);

		return permissaoFuncao;
	}

}