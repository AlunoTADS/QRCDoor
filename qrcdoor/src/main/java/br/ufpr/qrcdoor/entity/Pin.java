package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "PIN" database table.
 * 
 */
@Entity
@Table(name="pin")
@NamedQuery(name="Pin.findAll", query="SELECT p FROM Pin p")
public class Pin extends Senha implements Serializable {
	private static final long serialVersionUID = 1L;

	public Pin() {
	}

}