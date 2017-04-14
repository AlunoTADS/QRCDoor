package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Uf" database table.
 * 
 */
@Entity
@Table(name="\"Uf\"")
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idUf\"")
	private Long id;

	private String nome;

	private String sigla;

	private String situacao;

	//bi-directional many-to-one association to Pessoa
	@OneToMany(mappedBy="uf")
	private List<Pessoa> pessoas;

	public Uf() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa addPessoa(Pessoa pessoa) {
		getPessoas().add(pessoa);
		pessoa.setUf(this);

		return pessoa;
	}

	public Pessoa removePessoa(Pessoa pessoa) {
		getPessoas().remove(pessoa);
		pessoa.setUf(null);

		return pessoa;
	}

}