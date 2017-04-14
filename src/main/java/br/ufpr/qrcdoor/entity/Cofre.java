package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Cofre" database table.
 * 
 */
@Entity
@Table(name="\"Cofre\"")
@NamedQuery(name="Cofre.findAll", query="SELECT c FROM Cofre c")
public class Cofre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idEstrutura\"")
	private Long idEstrutura;

	//bi-directional one-to-one association to Estrutura
	@OneToOne
	@JoinColumns({
		})
	private Estrutura estrutura;

	public Cofre() {
	}

	public Long getIdEstrutura() {
		return this.idEstrutura;
	}

	public void setIdEstrutura(Long idEstrutura) {
		this.idEstrutura = idEstrutura;
	}

	public Estrutura getEstrutura() {
		return this.estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

}