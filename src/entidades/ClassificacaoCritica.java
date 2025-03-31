package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ClassificacaoCritica {
	@Id 
	private Long id;
	@Column
	private String descricao;	
	@OneToOne
	private TipoCritica tipoCritica;
	
	public ClassificacaoCritica() {
		
	}
	
	public ClassificacaoCritica(Long l) {
		id = l;
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
	public TipoCritica getTipoCritica() {
		return tipoCritica;
	}
	public void setTipoCritica(TipoCritica tipoCritica) {
		this.tipoCritica = tipoCritica;
	}
	
}
