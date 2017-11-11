package br.ufpr.qrcdoor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * The persistent class for the "Pessoa" database table.
 *
 */
@Entity
@Table(name = "pessoa")
@NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "pessoa_idpessoa_seq", sequenceName = "pessoa_idpessoa_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_idpessoa_seq")
    @Column(name = "idpessoa", unique = true, nullable = false)
    private Long id;

    @Column(length = 32)
    private String documento;

    @Column(name = "fisicajuridica", length = 1, nullable = false)
    private String fisicaJuridica;

    @Lob
    @Column(name = "foto")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] foto;

    @Column(name = "fotoextensao", length = 16)
    private String fotoExtensao;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "situacao", length = 1, nullable = false)
    private String situacao;

    @Column(name = "login", length = 32, unique = true)
    private String login;

    @Column(name = "senha", length = 255)
    private String senha;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "telefonefixo", length = 64)
    private String telefoneFixo;

    @Column(name = "telefonecelular", length = 64)
    private String telefoneCelular;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 32)
    private RoleEnum role;

    public Pessoa() {
    }

    public Pessoa(String login, String senha, RoleEnum role) {
        super();
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoExtensao() {
        return this.fotoExtensao;
    }

    public void setFotoExtensao(String fotoExtensao) {
        this.fotoExtensao = fotoExtensao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFisicaJuridica() {
        return fisicaJuridica;
    }

    public void setFisicaJuridica(String fisicaJuridica) {
        this.fisicaJuridica = fisicaJuridica;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

}
