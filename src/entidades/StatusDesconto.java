package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatusDesconto {
	@Id 
	private long id;
	@Column
	private String descricao;
	
	public StatusDesconto() {
	}

	public StatusDesconto(long l) {
		id = l;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}
