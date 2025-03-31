package interfaces;

import java.util.List;

import entidades.Usuario;


public interface UsuarioDao {
	
	public abstract Usuario buscaPorId(Long id);
	
	public abstract Usuario buscaPorLogin(String login);
	
	public abstract List<Usuario> buscaTodos();
	
	public abstract void salva(Usuario u);
	
	public abstract void exclui(Usuario u);

}
