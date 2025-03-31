package interfaces;

import java.util.Date;
import java.util.List;

import entidades.Desconto;

public interface DescontoDao {

	public Desconto buscarPorId(long id);
	
	public List<Desconto> buscarTodos();
	
	public List<Desconto> buscarPorParametros(String linha, String idOdontologico, String nome, String idEmpresa, String matricula, Date referencia);
		
	public void salva(Desconto c);
	
	public void exclui(Desconto c);

	public void limpa();	
	
}
