package dao;

import interfaces.ClassificacaoCriticaDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import entidades.ClassificacaoCritica;

public class ClassificacaoCriticaHibernate implements ClassificacaoCriticaDao{

	private final Session session;	
	
	public ClassificacaoCriticaHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public ClassificacaoCritica buscarPorId(long id) {
		return (ClassificacaoCritica) session.load(ClassificacaoCritica.class, id);
	}

	@Override
	public List<ClassificacaoCritica> buscarTodos() {
		return session.createCriteria(ClassificacaoCritica.class).addOrder(Order.asc("id")).list();
	}

	@Override
	public void exclui(ClassificacaoCritica c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(ClassificacaoCritica c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}

}
