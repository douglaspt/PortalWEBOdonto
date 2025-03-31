package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Pattern;

@Entity
public class Desconto {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	@Pattern(regex="[0-9]", message="Este campo deve ser preenchido somente com numeros")
	private int linha;	
	@Column
	@Temporal(TemporalType.DATE)
	private Date referencia;
	@Column
	@Temporal(TemporalType.DATE)
	private Date origem;
	@Column
	private int idEmpresa;
	@Column
	private int matricula;
	@Column
	private String idOdontologico;	
	@Column
	private String nome;
	@Column
	private Date nascimento;
	@Column
	private Date adesao;
	@Column
	private int idTipoCobertura;
	@Column
	private int idTipoDependente;
	@Column
	private float vlInformado;
	@Column
	private float vlPagar;	
	@ManyToOne
	private Lote lote;
	@OneToOne
	private StatusDesconto status;
	@Column
	private String cpf;
	@Column
	private String nomeMae;
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Date getAdesao() {
		return adesao;
	}
	public void setAdesao(Date adesao) {
		this.adesao = adesao;
	}
	public int getIdTipoCobertura() {
		return idTipoCobertura;
	}
	public void setIdTipoCobertura(int idTipoCobertura) {
		this.idTipoCobertura = idTipoCobertura;
	}
	public int getIdTipoDependente() {
		return idTipoDependente;
	}
	public void setIdTipoDependente(int idTipoDependente) {
		this.idTipoDependente = idTipoDependente;
	}
	public float getVlInformado() {
		return vlInformado;
	}
	public void setVlInformado(float vlInformado) {
		this.vlInformado = vlInformado;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public String getIdOdontologico() {
		return idOdontologico;
	}
	public void setIdOdontologico(String idOdontologico) {
		this.idOdontologico = idOdontologico;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public float getVlPagar() {
		return vlPagar;
	}
	public void setVlPagar(float vlPagar) {
		this.vlPagar = vlPagar;
	}
	public StatusDesconto getStatus() {
		return status;
	}
	public void setStatus(StatusDesconto status) {
		this.status = status;
	}
	public Date getOrigem() {
		return origem;
	}
	public void setOrigem(Date origem) {
		this.origem = origem;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	
	
}
