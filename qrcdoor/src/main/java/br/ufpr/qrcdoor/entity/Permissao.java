package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the "Permissao" database table.
 * 
 */
@Entity
@Table(name="permissao")
@NamedQuery(name="Permissao.findAll", query="SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="permissao_idpermissao_seq", sequenceName="permissao_idpermissao_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissao_idpermissao_seq")
	@Column(name="idpermissao", unique=true, nullable=false)
	private Long id;
	
	@Column(length=255)
	private String descricao;

	@Type(type="true_false")
	@Column(nullable=false)
	private Boolean abre;

	@Temporal(TemporalType.DATE)
	@Column(name="datafim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="datainicio")
	private Date dataInicio;

	@Column(name="diassemana", length=7)
	private String diasSemana;

	@Type(type="true_false")
	@Column(nullable=false)
	private Boolean fecha;

	@Type(type="true_false")
	@Column(nullable=false)
	private Boolean gerencia;

	@Type(type="true_false")
	@Column(nullable=false)
	private Boolean herda;

	@Column(name="horafim")
	private String horaFim;

	@Column(name="horainicio")
	private String horaInicio;

	@Column(length=12)
	private String meses;

	@Type(type="true_false")
	@Column(nullable=false)
	private Boolean monitora;

	public Permissao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAbre() {
		return this.abre;
	}

	public void setAbre(Boolean abre) {
		this.abre = abre;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDiasSemana() {
		return this.diasSemana;
	}

	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}

	public Boolean getFecha() {
		return this.fecha;
	}

	public void setFecha(Boolean fecha) {
		this.fecha = fecha;
	}

	public Boolean getGerencia() {
		return this.gerencia;
	}

	public void setGerencia(Boolean gerencia) {
		this.gerencia = gerencia;
	}

	public Boolean getHerda() {
		return this.herda;
	}

	public void setHerda(Boolean herda) {
		this.herda = herda;
	}

	public String getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getMeses() {
		return this.meses;
	}

	public void setMeses(String meses) {
		this.meses = meses;
	}

	public Boolean getMonitora() {
		return this.monitora;
	}

	public void setMonitora(Boolean monitora) {
		this.monitora = monitora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}