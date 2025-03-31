package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Critica;
import entidades.Desconto;
import entidades.HistoricoMovimentacao;
import entidades.Usuario;


public class HistoricoMovimentacaoHibernate implements HistoricoMovimentacaoDao{
	
	private final Session session;	
	
	public HistoricoMovimentacaoHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public HistoricoMovimentacao busca(Long i) {
		return (HistoricoMovimentacao) session.load(Usuario.class, i);

	}

	@Override
	public void exclui(HistoricoMovimentacao u) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(u);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(HistoricoMovimentacao u) {
		Transaction t = session.beginTransaction();
		try {
			session.save(u);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}	
		
	}

	@Override
	public List<HistoricoMovimentacao> busca(Desconto desconto,
			Date agendaInicial, Date agendaFinal, boolean pesquisaAgendaNull,
			Date baixaInicial, Date baixaFinal, Date canceladoInicial,
			Date canceladoFinal) {
		// TODO Auto-generated method stub
		//session.createCriteria(HistoricoMovimentacao.class).addOrder(Order.desc("agenda")).list();
		Criteria c = session.createCriteria(HistoricoMovimentacao.class);
		if (agendaInicial!=null) 
			c.add(Restrictions.ge("agenda", agendaInicial));
		if (agendaFinal!=null)
			c.add(Restrictions.le("agenda", agendaFinal));
	    
		if (pesquisaAgendaNull)
			c.add(Restrictions.isNull("baixa")).add(Restrictions.isNull("cancelamento"));
		else {
			if (baixaInicial!=null) 
				c.add(Restrictions.ge("baixa", baixaInicial));
			if (baixaFinal!=null)
				c.add(Restrictions.le("baixa", baixaFinal));
			if (canceladoInicial!=null) 
				c.add(Restrictions.ge("cancelamento", canceladoInicial));
			if (canceladoFinal!=null)
				c.add(Restrictions.le("cancelamento", canceladoFinal));			
		}		
		if (desconto!=null)
			if (desconto.getId()>0)
				c.createCriteria("desconto").add(Restrictions.eq("id", desconto.getId()));


	    c.addOrder(Order.desc("agenda"));
		return c.list();
	}
	
	

}
