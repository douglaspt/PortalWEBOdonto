package testes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;

import dao.CriticaHistoricoHibernate;
import dao.DescontoHistoricoHibernate;
import dao.HibernateUtil;
import entidades.CriticaHistorico;
import entidades.Desconto;
import entidades.DescontoHistorico;
import entidades.relatorios.ExportaDesconto;

public class TestaGeral {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//new CriticaHibernate(session).limpa();
        //new DescontoHibernate(session).limpa();
		
		//new CriticaHibernate(session).criticar();
		String mesAno = "04/2015";
		List<ExportaDesconto> descontos = new ArrayList<ExportaDesconto>();
		Calendar referencia = new GregorianCalendar();
		//referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		referencia.set(2015,3,1);
		List<CriticaHistorico> criticas = new CriticaHistoricoHibernate(session).buscarHistorico(referencia.getTime());
		System.out.println(criticas.size());
		System.out.println(referencia.getTime());
		int ultimaLinha = 0;
		
		List<Desconto> descontosB = new DescontoHistoricoHibernate(session).buscarPorParametros("", "", "", "", "", referencia.getTime());
		System.out.println(" Descontos  - "+descontosB.size());
		
		DescontoHistorico descontosC = new DescontoHistoricoHibernate(session).buscarPorId(4747656);
		System.out.println(" Descontos  - "+descontosC.getNome());
		/*
		for (CriticaHistorico critica : criticas) {
			
			ExportaDesconto e = new ExportaDesconto(critica);
			if (ultimaLinha != e.getLinha())
				descontos.add(e);
			ultimaLinha = e.getLinha();
			System.out.println(critica.getDesconto().getLinha());
			
		//}
		//}
		}
		*/
		
		
        System.out.println("ok");

	}

}
