package br.ufpr.qrcdoor.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "PermissaoEstrutura" database table.
 * 
 */
@Entity
@Table(name="permissaoestrutura")
@NamedQuery(name="PermissaoEstrutura.findAll", query="SELECT p FROM PermissaoEstrutura p")
public class PermissaoEstrutura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Embeddable
	public static class PermissaoEstruturaID implements Serializable {
		private static final long serialVersionUID = 1L;

		//bi-directional many-to-one association to Estrutura
		@ManyToOne
		@JoinColumn(name="idestrutura")
		private Estrutura estrutura;
		
		//bi-directional many-to-one association to Permissao
		@ManyToOne
		@JoinColumn(name="idpermissao")
		private Permissao permissao;
		
		public PermissaoEstruturaID(Long idPermissao, Long idEstrutura) {
			this.permissao = new Permissao();
			this.estrutura = new Estrutura();
			this.permissao.setId(idPermissao);
			this.estrutura.setId(idEstrutura);
		}

		public Estrutura getEstrutura() {
			return estrutura;
		}

		public void setEstrutura(Estrutura estrutura) {
			this.estrutura = estrutura;
		}

		public Permissao getPermissao() {
			return permissao;
		}

		public void setPermissao(Permissao permissao) {
			this.permissao = permissao;
		}
		
	}
	
	@EmbeddedId
	public PermissaoEstruturaID id;
	
	public PermissaoEstrutura() {
	}

	public PermissaoEstruturaID getId() {
		return id;
	}

	public void setId(PermissaoEstruturaID id) {
		this.id = id;
	}
	
}
