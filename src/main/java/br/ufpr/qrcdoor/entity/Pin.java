package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "PIN" database table.
 * 
 */
@Entity
@Table(name="\"PIN\"")
@NamedQuery(name="Pin.findAll", query="SELECT p FROM Pin p")
public class Pin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idChave\"")
	private Long idChave;

	//bi-directional one-to-one association to Senha
	@OneToOne
	@JoinColumns({
		})
	private Senha senha;

	public Pin() {
	}

	public Long getIdChave() {
		return this.idChave;
	}

	public void setIdChave(Long idChave) {
		this.idChave = idChave;
	}

	public Senha getSenha() {
		return this.senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

}