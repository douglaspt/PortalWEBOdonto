package controle.negocio;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.Session;

import dao.CriticaHistoricoHibernate;
import dao.HibernateUtil;
import dao.UsuarioHibernate;
import entidades.Usuario;

/**
 * @author bruno.teixeira
 *
 */
public class OdontologicoBean extends Gerenciador{
	
	private String login = "";
	private String senha = "";
	private String ipCliente = "";
	private Date ultimoLogin;	
	
	
	public String goCritica(){
        return "criticas";
    }
	
	public String goDesconto(){
        return "descontos";
    }
	
	public String goPagamento(){
        return "pagamentos";
    }	
	
	public String envioArquivo(){
		return "upload";
	}

	public String doLogout(){
		HttpSession sessaoHttp = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
        if (sessaoHttp != null) {
        	sessaoHttp.invalidate();
            //session.setMaxInactiveInterval(3);
        }
        return "login";
    }
	
	public String pegarIp() {  
		try  
	      {   
	         return InetAddress.getLocalHost().getHostAddress();   
	      }   
	      catch(UnknownHostException e)   
	      {   
	         return new String("127.0.0.1");      
	      }   
	      // HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
	      // String ip = request.getRemoteAddr(); 
	  
	  //      return ip;  
	   }
	
	public String pesquisaUsuario() {
		HttpSession sessaoHttp = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String resultado = null;
		UsuarioHibernate uH = new UsuarioHibernate(session);
		//setUsuarioLogado(uH.buscaPorLogin(login));
		Usuario usuario = uH.buscaPorLogin(login);
		if (usuario == null){
			super.setMenssagemErro("Usuario não Encontrado");
			System.out.println("usuario não encontrado");
		} else {
			if (senha.equals(usuario.getSenha())) {
				super.setMenssagemErro("");
				usuarioLogado = usuario;
				sessaoHttp.setAttribute("usuario", usuario);
				confirgurarSistema();
				uH.salva(getUsuarioLogado());
				if(senha.equals("1234"))
					resultado = "mudancaSenha";
				else
					resultado = "index";
			} else {
				super.setMenssagemErro("Senha Inválida");
				System.out.println("senha invalida");
				resultado = null;
			}				
		}
		System.out.println(resultado);
		return resultado;
	}

	private void confirgurarSistema() {
		ipCliente = pegarIp();
		ultimoLogin = getUsuarioLogado().getDataUltimoLogin();
		getUsuarioLogado().setDataUltimoLogin(data.getTime());
		getUsuarioLogado().setIpUltimoLogin(ipCliente);	
	}
	
	public String pesquisarCriticas(){
		ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String fileName = serveletContext.getRealPath("reports/reportRelacaoDescontos.jasper");
//		String outFileName = serveletContext.getRealPath("reports/reportRelacaoDescontos.pdf");
		HashMap hm = new HashMap();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List criticas = new CriticaHistoricoHibernate(session).buscarPorParametros(null, null, null, "5", "61273", null, null, null, null);
		System.out.println(criticas.size());
		session.close();
		
		JRBeanArrayDataSource jrdts = new JRBeanArrayDataSource(criticas.toArray());  
		
		try {
	           // Fill the report using an empty data source
            JasperPrint print = JasperFillManager.fillReport(fileName, hm, jrdts);
            
            JasperViewer.viewReport(print,false);
            
            /*		EXPORTA COMO PDF
            
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();
            
            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            
            // Export the PDF file
            exporter.exportReport();

			*/
            
            
        } catch (JRException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return "";
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
	
	
}
