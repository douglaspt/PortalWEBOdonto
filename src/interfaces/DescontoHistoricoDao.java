package interfaces;

import java.util.Date;
import java.util.List;

import entidades.Desconto;
import entidades.DescontoHistorico;

public interface DescontoHistoricoDao {

	public abstract DescontoHistorico buscarPorId(long id);
	
	public abstract List<Desconto> buscarPorParametros(String linha, String idOdontologico, String nome, String idEmpresa, String matricula, Date referencia);
		
	public abstract void salva(DescontoHistorico d);
	
	public abstract void exclui(DescontoHistorico d);	
	
}
