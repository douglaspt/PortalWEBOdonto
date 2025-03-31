package testes;

import java.util.Date;

import org.hibernate.Session;

import dao.HibernateUtil;
import dao.LoteHibernate;

public class TesteLote {
	public static void main(String[] args) {
		long id = 1435778216439l;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		LoteHibernate lH = new LoteHibernate(session);
		Date data = new Date();
		Date dataFolha = lH.buscaUltimo().getEnvioFolha();
		System.out.println(data);
		System.out.println(dataFolha);

		if (dataFolha != null && data.getMonth() == dataFolha.getMonth()
				&& data.getYear() == dataFolha.getYear()) {
			System.out
					.println("Não é possivel carregar arquivo pois a folha desse mes ja foi enviada");
		} else {
			System.out.println("Pode carregar");
		}

		/*
		 * String sql =
		 * "select id, dataEnvio, critica, fechado, ipOrigem, nomeArquivo, baixaFolha, envioFolha from Lote l where dataEnvio = (select max(dataEnvio) from Lote)"
		 * ; SQLQuery q = session.createSQLQuery(sql); q.addEntity(Lote.class);
		 * 
		 * //System.out.println("resultado: "+session.createQuery(sql).uniqueResult
		 * ()); Lote lote = (Lote) q.uniqueResult();
		 * System.out.println(lote.getId
		 * ()+" - "+lote.getNomeArquivo()+" - "+lote.getEnvioFolha());
		 */

	}

}
