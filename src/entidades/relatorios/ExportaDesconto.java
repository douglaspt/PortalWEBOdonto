package entidades.relatorios;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import entidades.Critica;
import entidades.CriticaHistorico;


public class ExportaDesconto {
	
	private int linha;	

	private String referencia;

	private String origem;

	private String idEmpresa;

	private String matricula;

	private String idOdontologico;	

	private String nome;

	private String nascimento;

	private String adesao;

	private String idTipoCobertura;

	private String idTipoDependente;

	private String vlInformado;
	
	private String vlPagar;	

	private String vlPago;	

	private String cpf;

	private String nomeMae;
	
	private String descricaoStatus;

	public ExportaDesconto() {
		
	}
	
	public ExportaDesconto(Critica critica) {
		DecimalFormat df10  = new DecimalFormat("0000000000");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		linha = critica.getDesconto().getLinha();
		referencia = sdf.format(critica.getDesconto().getReferencia());
		origem = sdf.format(critica.getDesconto().getOrigem());
		idEmpresa = Integer.toString(critica.getDesconto().getIdEmpresa());
		matricula = Integer.toString(critica.getDesconto().getMatricula());
		idOdontologico = critica.getDesconto().getIdOdontologico();
		nome = critica.getDesconto().getNome();
		nascimento = sdf.format(critica.getDesconto().getNascimento());
		adesao = sdf.format(critica.getDesconto().getAdesao());
		idTipoCobertura = Integer.toString(critica.getDesconto().getIdTipoCobertura());
		idTipoDependente = Integer.toString(critica.getDesconto().getIdTipoDependente());
		vlInformado = df10.format(critica.getDesconto().getVlInformado()*100);
		vlPagar = df10.format(critica.getVlPagar());
		vlPago = df10.format(critica.getVlPago());
		cpf = critica.getDesconto().getCpf();
		nomeMae = critica.getDesconto().getNomeMae();
		descricaoStatus = critica.getDescricaoCritica();
	}

	public ExportaDesconto(CriticaHistorico critica) {
		DecimalFormat df10  = new DecimalFormat("0000000000");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		linha = critica.getDesconto().getLinha();
		referencia = sdf.format(critica.getDesconto().getReferencia());
		origem = sdf.format(critica.getDesconto().getOrigem());
		idEmpresa = Integer.toString(critica.getDesconto().getIdEmpresa());
		matricula = Integer.toString(critica.getDesconto().getMatricula());
		idOdontologico = critica.getDesconto().getIdOdontologico();
		nome = critica.getDesconto().getNome();
		nascimento = sdf.format(critica.getDesconto().getNascimento());
		adesao = sdf.format(critica.getDesconto().getAdesao());
		idTipoCobertura = Integer.toString(critica.getDesconto().getIdTipoCobertura());
		idTipoDependente = Integer.toString(critica.getDesconto().getIdTipoDependente());
		vlInformado = df10.format(critica.getDesconto().getVlInformado()*100);
		vlPagar = df10.format(critica.getVlPagar()*100);
		vlPago = df10.format(critica.getVlPago()*100);
		cpf = critica.getDesconto().getCpf();
		nomeMae = critica.getDesconto().getNomeMae();
		descricaoStatus = critica.getDescricaoCritica();
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getIdOdontologico() {
		return idOdontologico;
	}

	public void setIdOdontologico(String idOdontologico) {
		this.idOdontologico = idOdontologico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getAdesao() {
		return adesao;
	}

	public void setAdesao(String adesao) {
		this.adesao = adesao;
	}

	public String getIdTipoCobertura() {
		return idTipoCobertura;
	}

	public void setIdTipoCobertura(String idTipoCobertura) {
		this.idTipoCobertura = idTipoCobertura;
	}

	public String getIdTipoDependente() {
		return idTipoDependente;
	}

	public void setIdTipoDependente(String idTipoDependente) {
		this.idTipoDependente = idTipoDependente;
	}

	public String getVlInformado() {
		return vlInformado;
	}

	public void setVlInformado(String vlInformado) {
		this.vlInformado = vlInformado;
	}

	public String getVlPago() {
		return vlPago;
	}

	public void setVlPago(String vlPago) {
		this.vlPago = vlPago;
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

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	public String getVlPagar() {
		return vlPagar;
	}

	public void setVlPagar(String vlPagar) {
		this.vlPagar = vlPagar;
	}	

	
	
}
