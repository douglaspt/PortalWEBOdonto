package controle.negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;

import org.joda.time.DateTime;

import dao.CriticaHibernate;
import dao.CriticaHistoricoHibernate;
import entidades.relatorios.Resumo;

public class GerenciadorResumo extends Gerenciador{
	
	private String titulo = "Resumo do Processamento de Criticas";
	private Calendar referencia = new GregorianCalendar();
	private String mesAno = "";
	private Resumo resumo = new Resumo();
	private List<Resumo> resumos = new ArrayList<Resumo>();
	private CriticaHibernate cH = new CriticaHibernate(session);
	private CriticaHistoricoHibernate chH = new CriticaHistoricoHibernate(session);
	private FacesContext facesContext = FacesContext.getCurrentInstance();  
	private GerenciadorCriticas gerenciadorCriticas = (GerenciadorCriticas) facesContext.getApplication().createValueBinding("#{gerenciadorCriticas}").getValue(facesContext); 
	private boolean processamentoCritica = false;
	
	public String verCriticas(){
		resumo = (Resumo) objDataTable.getRowData();
		return  gerenciadorCriticas.pesquisaPorParametro(mesAno, resumo.getClassificacaoCritica());
	}

	public String abrir(){
		resumos.clear();
		processamentoCritica = false;
		return "resumoCriticas";
	}
	
	public String abrirProcessamento(){
		resumos.clear();
		processamentoCritica = true;
		return "resumoCriticas";
	}
	
	public String pesquisar() {
		resumos.clear();
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		resumos = cH.buscaResumoCritica(referencia.getTime());
		System.out.println("Pesquisa Mes Atual, Total de Registros: "+resumos.size());
		if(resumos.size()==0||resumos==null) {
			resumos = chH.buscaResumoCritica(referencia.getTime());	
			System.out.println("Pesquisa Historico, Total de Registros: "+resumos.size());
		}
		return null;
	}

	public String processarCriticas(){
		cH.criticar();
		resumos.clear();
		DateTime dt = new DateTime();
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(), 1, 0, 0, 0, 0);
		//referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		referencia.set(dt.getYear(), dt.getMonthOfYear() - 1,1);
		System.out.println(referencia.getTime());
		resumos = cH.buscaResumoCritica(referencia.getTime());
		System.out.println("Pesquisa Mes Atual, Total de Registros: "+resumos.size());
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


	public Resumo getResumo() {
		return resumo;
	}


	public void setResumo(Resumo resumo) {
		this.resumo = resumo;
	}


	public List<Resumo> getResumos() {
		return resumos;
	}


	public void setResumos(List<Resumo> resumos) {
		this.resumos = resumos;
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

	public boolean isProcessamentoCritica() {
		return processamentoCritica;
	}

	public void setProcessamentoCritica(boolean processamentoCritica) {
		this.processamentoCritica = processamentoCritica;
	}
	
	
	
		
	
	
	
	
	
}
