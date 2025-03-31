package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lote {
	@Id 
	private long id;
	@Column
	private Date dataEnvio;
	@Column
	private Date critica;
	@Column
	private Date fechado;
	//@ManyToOne
	//private Usuario usuarioEnvio;
	@Column
	private String ipOrigem;
	@Column
	private String nomeArquivo;	
	@Column
	private Date baixaFolha;	
	@Column
	private Date envioFolha;	

	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Date getCritica() {
		return critica;
	}
	public void setCritica(Date critica) {
		this.critica = critica;
	}
	public Date getFechado() {
		return fechado;
	}
	public void setFechado(Date fechado) {
		this.fechado = fechado;
	}

	public String getIpOrigem() {
		return ipOrigem;
	}
	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Date getBaixaFolha() {
		return baixaFolha;
	}
	public void setBaixaFolha(Date baixaFolha) {
		this.baixaFolha = baixaFolha;
	}
	public Date getEnvioFolha() {
		return envioFolha;
	}
	public void setEnvioFolha(Date envioFolha) {
		this.envioFolha = envioFolha;
	}

	
	
	
	
}
