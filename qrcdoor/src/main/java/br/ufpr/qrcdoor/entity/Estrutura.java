package br.ufpr.qrcdoor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the "Estrutura" database table.
 * 
 */
@Entity
@Table(name="estrutura")
@NamedQuery(name="Estrutura.findAll", query="SELECT e FROM Estrutura e")
public class Estrutura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="estrutura_idestrutura_seq", sequenceName="estrutura_idestrutura_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="estrutura_idestrutura_seq")
	@Column(name="idestrutura", unique=true, nullable=false)
	private Long id;

	@Column(length=255, nullable=false)
	private String descricao;

	@JsonIgnore
    @Lob
    @Column(name="foto")
    @Type(type="org.hibernate.type.BinaryType")
	private byte[] foto;

	@Column(name="fotoextensao", length=16)
	private String fotoExtensao;

	@ManyToOne
	@JoinColumn(name="idestruturapai")
	@JsonBackReference
	private Estrutura estruturaPai;

	@Column(length=1, nullable=false)
	private String situacao;

	@Column(name="tempomaximoabertura", nullable=false)
	private Integer tempoMaximoAbertura;

	public Estrutura() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFotoExtensao() {
		return fotoExtensao;
	}

	public void setFotoExtensao(String fotoExtensao) {
		this.fotoExtensao = fotoExtensao;
	}

	public Estrutura getEstruturaPai() {
		return estruturaPai;
	}

	public void setEstruturaPai(Estrutura estruturaPai) {
		this.estruturaPai = estruturaPai;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Integer getTempoMaximoAbertura() {
		return tempoMaximoAbertura;
	}

	public void setTempoMaximoAbertura(Integer tempoMaximoAbertura) {
		this.tempoMaximoAbertura = tempoMaximoAbertura;
	}

}