package dao;

import interfaces.DescontoHistoricoDao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Desconto;
import entidades.DescontoHistorico;

public class DescontoHistoricoHibernate implements DescontoHistoricoDao{

	private final Session session;	
	
	public DescontoHistoricoHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public DescontoHistorico buscarPorId(long id) {
		return (DescontoHistorico) session.load(DescontoHistorico.class, id);
	}

	@Override
	public void exclui(DescontoHistorico d) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(d);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(DescontoHistorico d) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(d);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public List<Desconto> buscarPorParametros(String linha, String idOdontologico,
			String nome, String idEmpresa, String matricula, Date referencia) {
		Criteria d = session.createCriteria(DescontoHistorico.class);
				
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
	    if (matricula != null & matricula != ""){
	    	d.add(Restrictions.eq("matricula", Integer.parseInt(matricula)));
	    }
	    if (referencia != null){
	   	d.add(Restrictions.eq("referencia", referencia));
	    }
	    d.addOrder(Order.asc("linha"));
		return d.list();
		
	}

}
