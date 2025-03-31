package dao;

import interfaces.UsuarioDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Usuario;


public class UsuarioHibernate implements UsuarioDao{
	
	private final Session session;	
	
	public UsuarioHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public Usuario buscaPorId(Long i) {
		return (Usuario) session.load(Usuario.class, i);

	}

	@Override
	public Usuario buscaPorLogin(String login) {
		return (Usuario) session.createCriteria(Usuario.class).add(
				Restrictions.eq("login", login)).uniqueResult();
		
	}

	@Override
	public List<Usuario> buscaTodos() {
		return session.createCriteria(Usuario.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public void exclui(Usuario u) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(u);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(Usuario u) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(u);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}	
		
	}
	
	

}
