package entidades.relatorios;

import entidades.ClassificacaoCritica;


public class Resumo {
	private ClassificacaoCritica classificacaoCritica;
	private String descricao;
	private float valorInformado = 0;
	private float valorCobrar = 0;
	private float somaValorInformado = 0;
	private float somaValorCobrar = 0;
	private float somaValorPago = 0 ;
	private int qtde = 0;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValorInformado() {
		return valorInformado;
	}
	public void setValorInformado(float valorInformado) {
		this.valorInformado = valorInformado;
	}
	public float getSomaValorInformado() {
		return somaValorInformado;
	}
	public void setSomaValorInformado(float somaValorInformado) {
		this.somaValorInformado = somaValorInformado;
	}
	public float getSomaValorCobrar() {
		return somaValorCobrar;
	}
	public void setSomaValorCobrar(float somaValorCobrar) {
		this.somaValorCobrar = somaValorCobrar;
	}
	public float getSomaValorPago() {
		return somaValorPago;
	}
	public void setSomaValorPago(float somaValorPago) {
		this.somaValorPago = somaValorPago;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public float getValorCobrar() {
		return valorCobrar;
	}
	public void setValorCobrar(float valorCobrar) {
		this.valorCobrar = valorCobrar;
	}
	public ClassificacaoCritica getClassificacaoCritica() {
		return classificacaoCritica;
	}
	public void setClassificacaoCritica(ClassificacaoCritica classificacaoCritica) {
		this.classificacaoCritica = classificacaoCritica;
	}
	
	
}
