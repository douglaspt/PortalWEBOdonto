package entidades;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HistoricoMovimentacao {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Usuario usuario;
	@Column
	private String descricao;
	@Column
	private Date evento = new GregorianCalendar().getTime();
	@Column
	private Date agenda;
	@Column
	private Date baixa;
	@Column
	private Date cancelamento;
	@Column
	private Long idOrigem;
	
	@Column
	private int linha;	
	@Column @Temporal(TemporalType.DATE)
	private Date referencia;
	@Column
	private int idEmpresa;
	@Column
	private int matricula;
	
	
	public HistoricoMovimentacao(){
		
	}
	
	public HistoricoMovimentacao(long i) {
		id = i;
	}
	
	public HistoricoMovimentacao(HistoricoMovimentacao historicoAntigo, Usuario usuario){
		this.setLinha(historicoAntigo.getLinha());
		this.setReferencia(historicoAntigo.getReferencia());
		this.setIdEmpresa(historicoAntigo.getIdEmpresa());
		this.setMatricula(historicoAntigo.getMatricula());	
		this.setUsuario(usuario);
		this.setAgenda(this.getEvento());
		this.setIdOrigem(historicoAntigo.getId());
	}
	
	public HistoricoMovimentacao(Desconto desconto, Usuario usuario, String descricao){
		this.setLinha(desconto.getLinha());
		this.setReferencia(desconto.getReferencia());
		this.setIdEmpresa(desconto.getIdEmpresa());
		this.setMatricula(desconto.getMatricula());
		this.setUsuario(usuario);
		this.setAgenda(this.getEvento());
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

	public Date getEvento() {
		return evento;
	}

	public void setEvento(Date evento) {
		this.evento = evento;
	}

	public Date getAgenda() {
		return agenda;
	}

	public void setAgenda(Date agenda) {
		this.agenda = agenda;
	}

	public Date getBaixa() {
		return baixa;
	}

	public void setBaixa(Date baixa) {
		this.baixa = baixa;
	}

	public Date getCancelamento() {
		return cancelamento;
	}

	public void setCancelamento(Date cancelamento) {
		this.cancelamento = cancelamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Long idOrigem) {
		this.idOrigem = idOrigem;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public Date getReferencia() {
		return referencia;
	}

	public void setReferencia(Date referencia) {
		this.referencia = referencia;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}


	
	

}
