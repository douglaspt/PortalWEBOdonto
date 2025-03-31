package controle.negocio;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import dao.HibernateUtil;
import entidades.Usuario;

abstract public class Gerenciador {
	
	final Calendar data = new GregorianCalendar();
	
	String menssagemErro = "";
	String textoPesquisa = "";
	String campoPesquisa = "";
//	private Usuario usuarioLogado = new Usuario();
	
	UIData objDataTable;
	
	Logger logger = Logger.getLogger(this.getClass());
	final Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	private final HttpSession sessaoHttp = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
	Usuario usuarioLogado = (Usuario) sessaoHttp.getAttribute("usuario");
	
	public String getIp() {  
	       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
	       String ip = request.getRemoteAddr(); 
	  
	        return ip;  
	   }	
	
	public String goHome(){
		return "index";
	}

	public Calendar getData() {
		return data;
	}

	public String getMenssagemErro() {
		return menssagemErro;
	}

	public void setMenssagemErro(String menssagemErro) {
		this.menssagemErro = menssagemErro;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public UIData getObjDataTable() {
		return objDataTable;
	}

	public void setObjDataTable(UIData objDataTable) {
		this.objDataTable = objDataTable;
	}

	public Session getSession() {
		return session;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}




}
