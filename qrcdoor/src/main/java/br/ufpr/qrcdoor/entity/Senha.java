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
public class Senha extends Chave implements Serializable {
	private static final long serialVersionUID = 1L;

	public Senha() {
	}

}