package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Critica {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column	
	private Date dataCritica;
	@Column
	private float vlPagar;
	@Column
	private float vlPago;
	@Column
	private String descricaoCritica;
	@Column
	private String obsCritica;
	@ManyToOne
	private Desconto desconto;
	@OneToOne
	private ClassificacaoCritica classificacaoCritica;
	@OneToOne
	private Usuario usuario;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataCritica() {
		return dataCritica;
	}
	public void setDataCritica(Date dataCritica) {
		this.dataCritica = dataCritica;
	}
	public float getVlPagar() {
		return vlPagar;
	}
	public void setVlPagar(float vlPagar) {
		this.vlPagar = vlPagar;
	}
	public float getVlPago() {
		return vlPago;
	}
	public void setVlPago(float vlPago) {
		this.vlPago = vlPago;
	}
	public String getDescricaoCritica() {
		return descricaoCritica;
	}
	public void setDescricaoCritica(String descricaoCritica) {
		this.descricaoCritica = descricaoCritica;
	}
	public String getObsCritica() {
		return obsCritica;
	}
	public void setObsCritica(String obsCritica) {
		this.obsCritica = obsCritica;
	}
	public Desconto getDesconto() {
		return desconto;
	}
	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}
	public ClassificacaoCritica getClassificacaoCritica() {
		return classificacaoCritica;
	}
	public void setClassificacaoCritica(ClassificacaoCritica classificacaoCritica) {
		this.classificacaoCritica = classificacaoCritica;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
