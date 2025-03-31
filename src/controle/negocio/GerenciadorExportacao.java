package controle.negocio;

import interfaces.CriticaDao;
import interfaces.CriticaHistoricoDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.CriticaHibernate;
import dao.CriticaHistoricoHibernate;
import entidades.Critica;
import entidades.CriticaHistorico;
import entidades.relatorios.ExportaDesconto;

public class GerenciadorExportacao extends Gerenciador{
	
	private String titulo = "Exportar Descontos";
	private List<ExportaDesconto> descontos = new ArrayList<ExportaDesconto>();
	private CriticaDao criticaDao = new CriticaHibernate(session);
	private CriticaHistoricoDao criticaHistoricoDao = new CriticaHistoricoHibernate(session);
	private List<Critica> criticas = new ArrayList<Critica>();
	private String mesAno = "";
	

	public String abrir(){
		descontos.clear();
		criticas.clear();
		return "exportaDescontos";
	}
	

	public String pesquisar() {
		descontos.clear();
		criticas.clear();
		//Calendar hoje = new GregorianCalendar();
		Calendar referencia = new GregorianCalendar();
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		int ultimaLinha = 0;
		
		//if ((referencia.get(Calendar.MONTH) == hoje.get(Calendar.MONTH)) && (referencia.get(Calendar.YEAR) == hoje.get(Calendar.YEAR))){
			criticas = criticaDao.buscar(referencia.getTime());
			System.out.println(referencia.getTime()+" - 01 - "+criticas.size());
			for (Critica critica : criticas) {
				//if (descontos.size() < 5000){
				ExportaDesconto e = new ExportaDesconto(critica);
				if (ultimaLinha != e.getLinha())
					descontos.add(e);
				ultimaLinha = e.getLinha();
				//}
			}
		//} else {
			if(criticas.size()==0||criticas==null) {
				List<CriticaHistorico> criticas = criticaHistoricoDao.buscarHistorico(referencia.getTime());
				System.out.println(referencia.getTime()+" - 02 - "+criticas.size());
				for (CriticaHistorico critica : criticas) {
					//if (descontos.size() < 5000){
					ExportaDesconto e = new ExportaDesconto(critica);
					if (ultimaLinha != e.getLinha())
						descontos.add(e);
					ultimaLinha = e.getLinha();
				//}
				}
			}	
		//}	
			
		return null;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public List<ExportaDesconto> getDescontos() {
		return descontos;
	}


	public void setDescontos(List<ExportaDesconto> descontos) {
		this.descontos = descontos;
	}


	public String getMesAno() {
		return mesAno;
	}


	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	
	
	
	
}
