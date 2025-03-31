package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProcessamentoHibernate implements ProcessamentoDao{

	private final Session session;	
	
	public ProcessamentoHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public void enviarDescontosParaFolha() {
		Transaction t = session.beginTransaction();
		try {
			System.out.println("aguarde processando envio");
			session.connection().prepareCall("{call spc_enviaDescontosFolha}").execute();
			//st.execute();
			System.out.println("envio processado");
			//session.createQuery("exec spc_criticarDescontos").executeUpdate();
			t.commit();
		} catch (Exception e) {
			System.out.println("erro");
			e.printStackTrace();
			t.rollback();
		}
		
	}
	
}
