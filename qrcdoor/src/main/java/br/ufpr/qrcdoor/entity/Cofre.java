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

	public Cofre() {
	}

}