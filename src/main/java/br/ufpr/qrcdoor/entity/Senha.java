package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Senha" database table.
 * 
 */
@Entity
@Table(name="\"Senha\"")
@NamedQuery(name="Senha.findAll", query="SELECT s FROM Senha s")
public class Senha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idChave\"")
	private Long idChave;

	//bi-directional one-to-one association to Pin
	@OneToOne(mappedBy="senha")
	private Pin pin;

	//bi-directional one-to-one association to Chave
	@OneToOne
	@JoinColumns({
		})
	private Chave chave;

	public Senha() {
	}

	public Long getIdChave() {
		return this.idChave;
	}

	public void setIdChave(Long idChave) {
		this.idChave = idChave;
	}

	public Pin getPin() {
		return this.pin;
	}

	public void setPin(Pin pin) {
		this.pin = pin;
	}

	public Chave getChave() {
		return this.chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

}