package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String login;
	
	@Column
	private String nome;
	
	@Column
	private String senha;
	
	@Column
	private int situacao;
	
	@Column	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column	@Temporal(TemporalType.DATE)
	private Date dataSistema;

	@Column
	private int usuarioAlteracao;
	
	@Column
	private Date dataUltimoLogin;
	
	@Column
	private String ipUltimoLogin;
	
	@Column
	private String perfilAcesso;

	public String getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(String perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public Date getDataUltimoLogin() {
		return dataUltimoLogin;
	}

	public void setDataUltimoLogin(Date dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}

	public String getIpUltimoLogin() {
		return ipUltimoLogin;
	}

	public void setIpUltimoLogin(String ipUltimoLogin) {
		this.ipUltimoLogin = ipUltimoLogin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataSistema() {
		return dataSistema;
	}

	public void setDataSistema(Date dataSistema) {
		this.dataSistema = dataSistema;
	}

	public int getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(int usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	
	public boolean isColaboradorPrestador(){
		return (perfilAcesso.equals("Colaborador Prestador"));
	}
	
	
}
