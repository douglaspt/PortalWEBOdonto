package testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import dao.CriticaHibernate;
import dao.HibernateUtil;
import entidades.Critica;
import entidades.relatorios.Resumo;

public class TestaResumo {

	/**
	 * @param args
	 */
	int rodar(){
		  return  1;
		}
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Critica> criticas = new ArrayList<Critica>();
		Date data = new Date();

		
	//	CriticaHibernate c = new CriticaHibernate(session);
	//	c.limpa();
		
	//	c.criticar();

		List<Resumo> r = new CriticaHibernate(session).buscaResumoCritica(data);
		System.out.println(r.size());
	
		for (Resumo resumo : r) {
			System.out.println(resumo.getClassificacaoCritica().getId() + " - " + resumo.getDescricao() + " - QTDE:"+resumo.getQtde() + " - VI:"+resumo.getValorInformado() + " - VC:"+resumo.getValorCobrar() + " - SC:"+resumo.getSomaValorCobrar() + " - SI:"+resumo.getSomaValorInformado() + " - SP:"+resumo.getSomaValorPago());
		}
	/*
		Calendar referencia = new GregorianCalendar();
		referencia.set(2010,0,1);
		List<Resumo> rh = new CriticaHistoricoHibernate(session).buscaResumoCritica(referencia.getTime());
		System.out.println(rh.size());
		
		for (Resumo resumo : rh) {
			System.out.println(resumo.getClassificacaoCritica().getId() + " - " + resumo.getDescricao() + " - QTDE:"+resumo.getQtde() + " - VI:"+resumo.getValorInformado() + " - VC:"+resumo.getValorCobrar() + " - SC:"+resumo.getSomaValorCobrar() + " - SI:"+resumo.getSomaValorInformado() + " - SP:"+resumo.getSomaValorPago());
		}
		
		
		List<ResumoFinanceiro> rf = new CriticaHibernate(session).buscaResumoFinanceiro();
		System.out.println(rf.size());
	
		for (ResumoFinanceiro resumo : rf) {
			System.out.println(" - QTDE:"+resumo.getQtde() + " - VI:"+resumo.getValorInformado() + " - SI:"+resumo.getSomaValorInformado() + " - VC:"+resumo.getValorCobrar() + " - SC:"+resumo.getSomaValorCobrar() + " - SP:"+resumo.getSomaValorPago());
		}		
*/
	}

}
