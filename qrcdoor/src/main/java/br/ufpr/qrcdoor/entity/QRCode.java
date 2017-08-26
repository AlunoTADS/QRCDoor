package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "QRCode" database table.
 * 
 */
@Entity
@Table(name="qrcode")
@NamedQuery(name="QRCode.findAll", query="SELECT q FROM QRCode q")
public class QRCode extends Chave implements Serializable {
	private static final long serialVersionUID = 1L;

	public QRCode() {
	}

}