package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Cofre" database table.
 * 
 */
@Entity
@Table(name="cofre")
@NamedQuery(name="Cofre.findAll", query="SELECT c FROM Cofre c")
public class Cofre extends Estrutura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(length=255)
	private String pin;

	public Cofre() {
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}