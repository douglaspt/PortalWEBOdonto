package interfaces;

import java.util.Date;
import java.util.List;

import entidades.ClassificacaoCritica;
import entidades.Critica;
import entidades.relatorios.Resumo;
import entidades.relatorios.ResumoFinanceiro;

public interface CriticaDao {

	public abstract Critica buscarPorId(long id);
	
	public abstract List<Critica> buscar(Date referencia);
	
	public abstract List<Critica> buscarPorParametros(String linha, String idOdontologico, 
			String nome, String idEmpresa, String matricula, Date referencia, 
			Date adesaoInicial, Date adesaoFinal, ClassificacaoCritica classificacaoCritica);
			
	public abstract List<Critica> buscarPorDesconto(long id);
	
	public abstract void salva(Critica c);
	
	public abstract void exclui(Critica c);

	List<Resumo> buscaResumoCritica(Date referencia);

	void limpa();

	void criticar();

	List<ResumoFinanceiro> buscaResumoFinanceiro(Date referencia);	
	
}
