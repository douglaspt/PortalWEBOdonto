package entidades.relatorios;

public class ResumoFinanceiro {
	private float valorInformado = 0;
	private float valorCobrar = 0;
	private float somaValorInformado = 0;
	private float somaValorCobrar = 0;
	private float somaValorPago = 0 ;
	private int qtde = 0;
	

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
	public float getValorInformado() {
		return valorInformado;
	}
	public void setValorInformado(float valorInformado) {
		this.valorInformado = valorInformado;
	}
	public float getValorCobrar() {
		return valorCobrar;
	}
	public void setValorCobrar(float valorCobrar) {
		this.valorCobrar = valorCobrar;
	}

	
	
}
