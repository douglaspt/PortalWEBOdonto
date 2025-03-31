package interfaces;

import java.util.List;

import entidades.ClassificacaoCritica;

public interface ClassificacaoCriticaDao {

	public abstract ClassificacaoCritica buscarPorId(long id);
	
	public abstract List<ClassificacaoCritica> buscarTodos();
		
	public abstract void salva(ClassificacaoCritica c);
	
	public abstract void exclui(ClassificacaoCritica c);	
	
}
