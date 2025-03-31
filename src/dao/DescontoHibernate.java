package dao;

import interfaces.DescontoDao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Desconto;

public class DescontoHibernate implements DescontoDao{

	private final Session session;	
	
	public DescontoHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public Desconto buscarPorId(long id) {
		return (Desconto) session.load(Desconto.class, id);
	}

	@Override
	public List<Desconto> buscarTodos() {
		return session.createCriteria(Desconto.class).addOrder(Order.asc("matricula")).list();
	}

	@Override
	public void exclui(Desconto c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}
	
	@Override
	public void limpa() {
		Transaction t = session.beginTransaction();
		try {
			//session.connection().prepareStatement("delete from Desconto").execute();
			session.createQuery("delete from Desconto").executeUpdate();
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}	

	@Override
	public void salva(Desconto c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public List<Desconto> buscarPorParametros(String linha, String idOdontologico,
			String nome, String idEmpresa, String matricula, Date referencia) {
		Criteria d = session.createCriteria(Desconto.class);
				
	    if (linha != null & linha != "") {
	    	d.add(Restrictions.eq("linha", Integer.parseInt(linha))) ;
	    }
	    if (idOdontologico != null & idOdontologico != "" ){
	    	d.add(Restrictions.eq("idOdontologico", idOdontologico));
	    }
	    if (nome != null & nome != ""){
	    	d.add(Restrictions.like("nome", "%"+nome+"%"));
	    }
	    if (idEmpresa != null & idEmpresa != ""){
	    	d.add(Restrictions.eq("idEmpresa", Integer.parseInt(idEmpresa)));
	    }
	    if (matricula != null & idEmpresa != ""){
	    	d.add(Restrictions.eq("matricula", Integer.parseInt(matricula)));
	    }
	    if (referencia != null){
	    	d.add(Restrictions.eq("referencia", referencia));
	    }
	    d.addOrder(Order.asc("linha"));
		return d.list();
		
	}

}
