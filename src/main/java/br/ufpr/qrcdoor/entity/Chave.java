package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lucas
 */
@Entity
public class Chave implements Serializable {
    	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idchave")
	private Long id;
	
	private String codigo;

    /**
     * @return the id
     */
    private Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    private String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    private void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
