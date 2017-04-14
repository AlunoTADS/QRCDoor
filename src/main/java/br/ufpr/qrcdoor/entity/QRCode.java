package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "QRCode" database table.
 * 
 */
@Entity
@Table(name="\"QRCode\"")
@NamedQuery(name="QRCode.findAll", query="SELECT q FROM QRCode q")
public class QRCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idChave\"")
	private Long idChave;

	//bi-directional one-to-one association to Chave
	@OneToOne
	@JoinColumns({
		})
	private Chave chave;

	public QRCode() {
	}

	public Long getIdChave() {
		return this.idChave;
	}

	public void setIdChave(Long idChave) {
		this.idChave = idChave;
	}

	public Chave getChave() {
		return this.chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

}