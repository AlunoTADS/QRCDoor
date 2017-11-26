package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	public static class PermissaoEstruturaPK implements Serializable {
		private static final long serialVersionUID = 1L;

		// bi-directional many-to-one association to Estrutura
		@ManyToOne
		@JoinColumn(name="idestrutura")
		private Estrutura estrutura;

		// bi-directional many-to-one association to Permissao
		@ManyToOne
		@JoinColumn(name="idpermissao")
		private Permissao permissao;

		public PermissaoEstruturaPK() {
		}

		public PermissaoEstruturaPK(Long idPermissao, Long idEstrutura) {
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

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof PermissaoEstruturaPK)) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			PermissaoEstruturaPK castObj = (PermissaoEstruturaPK) obj;
			return (Objects.equals(this.getEstrutura().getId(), castObj.getEstrutura().getId()) &&
					Objects.equals(this.getPermissao().getId(), castObj.getPermissao().getId()));
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int hash = 17;
			hash = hash * prime + this.getEstrutura().getId().intValue();
			hash = hash * prime + this.getPermissao().getId().intValue();
			return hash;
		}

	}

	@Id
	@EmbeddedId
	public PermissaoEstruturaPK id;

	public PermissaoEstrutura() {
	}

	public PermissaoEstruturaPK getId() {
		return id;
	}

	public void setId(PermissaoEstruturaPK id) {
		this.id = id;
	}

}
