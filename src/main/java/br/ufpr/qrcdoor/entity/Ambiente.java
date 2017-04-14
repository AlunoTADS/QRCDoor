package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Ambiente" database table.
 * 
 */
@Entity
@Table(name="\"Ambiente\"")
@NamedQuery(name="Ambiente.findAll", query="SELECT a FROM Ambiente a")
public class Ambiente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idEstrutura\"")
	private Long idEstrutura;

	@Column(name="\"quantidadeMaximaPessoas\"")
	private Integer quantidadeMaximaPessoas;

	//bi-directional one-to-one association to Estrutura
	@OneToOne
	@JoinColumns({
		})
	private Estrutura estrutura;

	public Ambiente() {
	}

	public Long getIdEstrutura() {
		return this.idEstrutura;
	}

	public void setIdEstrutura(Long idEstrutura) {
		this.idEstrutura = idEstrutura;
	}

	public Integer getQuantidadeMaximaPessoas() {
		return this.quantidadeMaximaPessoas;
	}

	public void setQuantidadeMaximaPessoas(Integer quantidadeMaximaPessoas) {
		this.quantidadeMaximaPessoas = quantidadeMaximaPessoas;
	}

	public Estrutura getEstrutura() {
		return this.estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

}