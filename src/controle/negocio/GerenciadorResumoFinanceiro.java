package controle.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.CriticaHibernate;
import dao.CriticaHistoricoHibernate;
import entidades.relatorios.Resumo;
import entidades.relatorios.ResumoFinanceiro;

public class GerenciadorResumoFinanceiro extends Gerenciador{
	
	private String titulo = "Resumo Financeiro";
	private Calendar referencia = new GregorianCalendar();
	private String mesAno = "";
	private List<ResumoFinanceiro> resumos = new ArrayList<ResumoFinanceiro>();
	private CriticaHibernate cH = new CriticaHibernate(session);
	private CriticaHistoricoHibernate chH = new CriticaHistoricoHibernate(session);

	
	public String pesquisar() {
		resumos.clear();
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		resumos = cH.buscaResumoFinanceiro(referencia.getTime());
		if(resumos.size()==0||resumos==null) {
			resumos = chH.buscaResumoFinanceiro(referencia.getTime());		
		}	
		return null;
	}

	
	
	public String goHome(){
		return "index";
	}
	

	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Calendar getReferencia() {
		return referencia;
	}


	public void setReferencia(Calendar referencia) {
		this.referencia = referencia;
	}


	public CriticaHibernate getcH() {
		return cH;
	}


	public void setcH(CriticaHibernate cH) {
		this.cH = cH;
	}


	public CriticaHistoricoHibernate getChH() {
		return chH;
	}


	public void setChH(CriticaHistoricoHibernate chH) {
		this.chH = chH;
	}



	public String getMesAno() {
		return mesAno;
	}



	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}



	public List<ResumoFinanceiro> getResumos() {
		return resumos;
	}



	public void setResumos(List<ResumoFinanceiro> resumos) {
		this.resumos = resumos;
	}
	
	
	
		
	
	
	
	
	
}
