package controle.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public class OperacoesArquivos {
	     public static synchronized void downloadFile(String filename, String fileLocation, String mimeType,    
			                                                  FacesContext facesContext) {    
			     
	    	 		System.out.println(fileLocation);
	    	 
			         ExternalContext context = facesContext.getExternalContext(); // Context    
			         String path = fileLocation; // Localizacao do arquivo    
			         
			         String fullFileName = path + filename;
			         System.out.println(fullFileName);
			         ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			         fullFileName = serveletContext.getRealPath(fullFileName);
			         System.out.println(fullFileName);
			           
			         File file = new File(fullFileName); // LINHA ALTERADA    
			         
			     
			         HttpServletResponse response = (HttpServletResponse) context.getResponse();    
			         response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\""); //aki eu seto o header e o nome q vai aparecer na hr do donwload    
			         response.setContentLength((int) file.length()); // O tamanho do arquivo    
			         response.setContentType(mimeType); // e obviamente o tipo    
			     
			         try {    
			             FileInputStream in = new FileInputStream(file);    
			             OutputStream out = response.getOutputStream();    
			     
			             byte[] buf = new byte[(int)file.length()];    
			             int count;    
			             while ((count = in.read(buf)) >= 0) {    
			                 out.write(buf, 0, count);    
			             }    
			             in.close();    
			             out.flush();    
			             out.close();    
			         facesContext.responseComplete();    
			         } catch (IOException ex) {    
			             System.out.println("Error in downloadFile: " + ex.getMessage());    
			             ex.printStackTrace();    
			         }    
			     }    
}
