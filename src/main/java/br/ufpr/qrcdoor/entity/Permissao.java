package br.ufpr.qrcdoor.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Permissao" database table.
 * 
 */
@Entity
@Table(name="\"Permissao\"")
@NamedQuery(name="Permissao.findAll", query="SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"idPermissao\"")
	private Long id;

	private Boolean abre;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataFim\"")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dataInicio\"")
	private Date dataInicio;

	@Column(name="\"diasSemana\"")
	private String diasSemana;

	private Boolean fecha;

	private Boolean gerencia;

	private Boolean herda;

	@Column(name="\"horaFim\"")
	private Time horaFim;

	@Column(name="\"horaInicio\"")
	private Time horaInicio;

	@Column(name="\"idEstrutura\"")
	private Long idEstrutura;

	private String meses;

	private Boolean monitora;

	//bi-directional many-to-one association to Acesso
	@OneToMany(mappedBy="permissao")
	private List<Acesso> acessos;

	//bi-directional many-to-one association to Estrutura
	@ManyToOne
	@JoinColumns({
		})
	private Estrutura estrutura;

	//bi-directional many-to-one association to PermissaoFuncao
	@OneToMany(mappedBy="permissao")
	private List<PermissaoFuncao> permissaoFuncaos;

	//bi-directional many-to-one association to PermissaoPessoa
	@OneToMany(mappedBy="permissao")
	private List<PermissaoPessoa> permissaoPessoas;

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

	public Time getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getIdEstrutura() {
		return this.idEstrutura;
	}

	public void setIdEstrutura(Long idEstrutura) {
		this.idEstrutura = idEstrutura;
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

	public List<Acesso> getAcessos() {
		return this.acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	public Acesso addAcesso(Acesso acesso) {
		getAcessos().add(acesso);
		acesso.setPermissao(this);

		return acesso;
	}

	public Acesso removeAcesso(Acesso acesso) {
		getAcessos().remove(acesso);
		acesso.setPermissao(null);

		return acesso;
	}

	public Estrutura getEstrutura() {
		return this.estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public List<PermissaoFuncao> getPermissaoFuncaos() {
		return this.permissaoFuncaos;
	}

	public void setPermissaoFuncaos(List<PermissaoFuncao> permissaoFuncaos) {
		this.permissaoFuncaos = permissaoFuncaos;
	}

	public PermissaoFuncao addPermissaoFuncao(PermissaoFuncao permissaoFuncao) {
		getPermissaoFuncaos().add(permissaoFuncao);
		permissaoFuncao.setPermissao(this);

		return permissaoFuncao;
	}

	public PermissaoFuncao removePermissaoFuncao(PermissaoFuncao permissaoFuncao) {
		getPermissaoFuncaos().remove(permissaoFuncao);
		permissaoFuncao.setPermissao(null);

		return permissaoFuncao;
	}

	public List<PermissaoPessoa> getPermissaoPessoas() {
		return this.permissaoPessoas;
	}

	public void setPermissaoPessoas(List<PermissaoPessoa> permissaoPessoas) {
		this.permissaoPessoas = permissaoPessoas;
	}

	public PermissaoPessoa addPermissaoPessoa(PermissaoPessoa permissaoPessoa) {
		getPermissaoPessoas().add(permissaoPessoa);
		permissaoPessoa.setPermissao(this);

		return permissaoPessoa;
	}

	public PermissaoPessoa removePermissaoPessoa(PermissaoPessoa permissaoPessoa) {
		getPermissaoPessoas().remove(permissaoPessoa);
		permissaoPessoa.setPermissao(null);

		return permissaoPessoa;
	}

}