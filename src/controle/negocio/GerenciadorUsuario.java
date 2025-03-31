package controle.negocio;

import interfaces.UsuarioDao;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioHibernate;
import entidades.Usuario;

public class GerenciadorUsuario extends Gerenciador{
	
    private String titulo = "Cadastro de Usuarios";
	private String senhaAntiga = "";
	private String senhaNova = "";
	private String senhaNovaValidacao = "";
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private UsuarioDao usuarioDao = new UsuarioHibernate(session);	
	
	
	public String prepararNovo(){
		limpar();	
		return null;
	}
	
	public String salvar(){
		usuarioDao.salva(usuario);
		pesquisar();
		return "usuario";
	}

	public String editar(){
		usuario = (Usuario) objDataTable.getRowData();
		return null;
	}	
	
	public String excluir(){
		usuario = (Usuario) objDataTable.getRowData();
		usuarioDao.exclui(usuario);
		pesquisar();
		return null;
	}
	
	public String mudarSenha(){
		usuario = getUsuarioLogado(); 
		if(senhaAntiga.equals(getUsuarioLogado().getSenha())){
			if(senhaNova.equals(senhaNovaValidacao)){
				if(senhaNova.length()>3){
					usuario.setSenha(senhaNova);
					usuarioDao.salva(usuario);
					return "index";
				} else {
					menssagemErro = "A Nova Senha deverá ter no Mínimo 4 caracteres";
					return null;
				}
			} else {
				menssagemErro = "Senha Nova Inválida! Verifique se digitou corretamente nas duas vezes a Senha Nova";
				return null;
			}
			
		} else {
			menssagemErro = "Senha Antiga Inválida! Verifique se digitou corretamente";
			return null;
		}
	}
	
	public String pesquisar() {
		usuarios = new UsuarioHibernate(session).buscaTodos();
		return null;
	}	
	
	public String limpar(){
		usuario = new Usuario();
		return null;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaNovaValidacao() {
		return senhaNovaValidacao;
	}

	public void setSenhaNovaValidacao(String senhaNovaValidacao) {
		this.senhaNovaValidacao = senhaNovaValidacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String goHome(){
		return "index";
	}
}
