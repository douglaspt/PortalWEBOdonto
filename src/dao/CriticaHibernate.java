package dao;

import interfaces.CriticaDao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import entidades.ClassificacaoCritica;
import entidades.Critica;
import entidades.CriticaHistorico;
import entidades.relatorios.Resumo;
import entidades.relatorios.ResumoFinanceiro;

public class CriticaHibernate implements CriticaDao{

	private final Session session;	
	
	public CriticaHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public Critica buscarPorId(long id) {
		return (Critica) session.load(Critica.class, id);
	}

	@Override
	public List<Critica> buscar(Date referencia) {
		Criteria c = session.createCriteria(Critica.class);
		Criteria d = c.createCriteria("desconto");
		if (referencia != null ){
	    	d.add(Restrictions.eq("referencia", referencia));
	    }
	    d.addOrder(Order.desc("referencia"));
	    d.addOrder(Order.asc("linha"));
	    System.out.println(referencia.getTime()+" - A - "+c.list().size());
	    System.out.println(referencia.toString());
		return c.list();
		//return session.createCriteria(Critica.class).addOrder(Order.asc("id")).list();
	}
	
	
	

	@Override
	public void exclui(Critica c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(Critica c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}
	
	
	@Override
	public void limpa() {
		Transaction t = session.beginTransaction();
		try {
			session.createQuery("delete from Critica").executeUpdate();
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}
	
		
	@Override
	public void criticar() {
		Transaction t = session.beginTransaction();
		try {
			System.out.println("aguarde processando criticas");
			session.connection().prepareCall("{call spc_criticarDescontos}").execute();
			//st.execute();
			System.out.println("criticas processadas");
			//session.createQuery("exec spc_criticarDescontos").executeUpdate();
			t.commit();
		} catch (Exception e) {
			System.out.println("erro");
			e.printStackTrace();
			t.rollback();
		}
		
	}

	@Override
	public List<Critica> buscarPorParametros(String linha, String idOdontologico,
			String nome, String idEmpresa, String matricula, Date referencia,
			Date adesaoInicial, Date adesaoFinal, ClassificacaoCritica classificacaoCritica) {
		
		Criteria c = session.createCriteria(Critica.class);
		Criteria d = c.createCriteria("desconto");
		
	    if (linha != null & linha != "") {
	    	d.add(Restrictions.eq("linha", Integer.parseInt(linha))) ;
	    }
	    if (idOdontologico != null & idOdontologico != "" ){
	    	d.add(Restrictions.ilike("idOdontologico", "%"+idOdontologico+"%"));
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
	    if (referencia != null ){
	    	d.add(Restrictions.eq("referencia", referencia));
	    }
	    if (adesaoInicial != null & adesaoFinal != null) {
	    	d.add(Restrictions.between("adesao", adesaoInicial, adesaoFinal));	
	    }
	    if (classificacaoCritica != null && classificacaoCritica.getId()> 0){
	    	c.add(Restrictions.eq("classificacaoCritica", classificacaoCritica));
	    }
	    d.addOrder(Order.desc("referencia"));
	    d.addOrder(Order.asc("linha"));
		return c.list();

	}

	@Override
	public List<Critica> buscarPorDesconto(long id) {
		Criteria c = session.createCriteria(Critica.class);
		Criteria d = c.createCriteria("desconto");
		d.add(Restrictions.eq("id", id));
		return c.list();
	}

	@Override
	public List<Resumo> buscaResumoCritica(Date referencia) {
		Criteria c = session.createCriteria(Critica.class);
		c.createAlias("desconto", "d");
		c.add(Restrictions.eq("d.referencia", referencia));
  		ProjectionList proList = Projections.projectionList();
  		proList.add(Projections.groupProperty("classificacaoCritica").as("classificacaoCritica"));
  		proList.add(Projections.groupProperty("descricaoCritica").as("descricao"));
  		proList.add(Projections.groupProperty("d.vlPagar").as("valorCobrar"));
  		proList.add(Projections.groupProperty("d.vlInformado").as("valorInformado"));
  		proList.add(Projections.sum("d.vlInformado").as("somaValorInformado"));
  		proList.add(Projections.sum("d.vlPagar").as("somaValorCobrar"));
  		proList.add(Projections.sum("vlPago").as("somaValorPago"));
  		proList.add(Projections.count("desconto").as("qtde"));
  		c.addOrder(Order.asc("classificacaoCritica.id"));
  		c.addOrder(Order.asc("d.vlInformado"));
  		c.addOrder(Order.asc("d.vlPagar"));
  		c.setProjection(proList);
  		c.setResultTransformer(Transformers.aliasToBean(Resumo.class));
		return c.list();	
	}
	
	@Override
	public List<ResumoFinanceiro> buscaResumoFinanceiro(Date referencia) {
		Criteria c = session.createCriteria(Critica.class);
		c.createAlias("desconto", "d");
		c.add(Restrictions.eq("d.referencia", referencia));
  		ProjectionList proList = Projections.projectionList();
  		proList.add(Projections.groupProperty("d.vlPagar").as("valorCobrar"));
  		proList.add(Projections.groupProperty("d.vlInformado").as("valorInformado"));  		
  		proList.add(Projections.sum("d.vlInformado").as("somaValorInformado"));
  		proList.add(Projections.sum("d.vlPagar").as("somaValorCobrar"));
  		proList.add(Projections.sum("vlPago").as("somaValorPago"));
  		proList.add(Projections.count("desconto").as("qtde"));
  		c.addOrder(Order.asc("d.vlPagar"));
  		c.addOrder(Order.asc("d.vlInformado"));
  		c.setProjection(proList);
  		c.setResultTransformer(Transformers.aliasToBean(ResumoFinanceiro.class));
		return c.list();	
	}

}
