package interfaces;

import java.util.Date;
import java.util.List;

import entidades.Lote;

public interface LoteDao {

	public abstract Lote buscarPorId(long id);
	
	public abstract List<Lote> buscarTodos();
		
	public abstract void salva(Lote c);
	
	public abstract void exclui(Lote c);
	
	public abstract Lote buscaUltimo();
	
}
