package interfaces;

import java.util.Date;
import java.util.List;

import entidades.ClassificacaoCritica;
import entidades.Critica;
import entidades.CriticaHistorico;
import entidades.relatorios.Resumo;
import entidades.relatorios.ResumoFinanceiro;

public interface CriticaHistoricoDao {

	public abstract CriticaHistorico buscarPorId(long id);
	
	public abstract List<Critica> buscarPorParametros(String linha, String idOdontologico, 
			String nome, String idEmpresa, String matricula, Date referencia,
			Date adesaoInicial, Date adesaoFinal, ClassificacaoCritica classificacaoCritica);
		
	public abstract void salva(CriticaHistorico c);
	
	public abstract void exclui(CriticaHistorico c);

	public abstract List<Resumo> buscaResumoCritica(Date referencia);	
	
	public abstract List<ResumoFinanceiro> buscaResumoFinanceiro(Date referencia);

	public abstract List<CriticaHistorico> buscarHistorico(Date referencia);
	
}
