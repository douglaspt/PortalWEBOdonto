package testes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.hibernate.Session;

import dao.CriticaHibernate;
import dao.DescontoHibernate;
import dao.HibernateUtil;
import dao.LoteHibernate;
import entidades.Desconto;
import entidades.Lote;
import entidades.Usuario;

public class TestaUpload {
	
	
	public static void main(String[] args) throws IOException {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String erro = "";
		String resultado = "";
    	Calendar data = new GregorianCalendar();
    	data.setLenient(false);
    	Calendar dataSistema = new GregorianCalendar();
    	ArrayList<File> files = new ArrayList<File>();
     	SimpleDateFormat sdfddMMyyyy_HHmmss = new SimpleDateFormat("ddMMyyyy_HHmm");
    	
    	//ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	//String destino = serveletContext.getRealPath("../ROOT/imasf_docs/odontologico/afiliados_"+sdfddMMyyyy_HHmmss.format(new Date())+".csv");
    	

    	//Date data = new Date();
    	
    	files.clear();
    	
    	File file = new File("C:/Abril 2012.csv");
    	try {
    	Scanner arq = new Scanner(file);
    	while (arq.hasNextLine()) {
    	String linha = arq.nextLine();
    	//String campos2[] = linha.split(";");
    	System.out.println(linha);
    	//System.out.println(campos2[0]+" - "+campos2[1]);
    	}
    	arq.close();
    	} catch (IOException ioe) {
    	ioe.printStackTrace();
    	}
        
        FileInputStream stream = new FileInputStream(file);  
        InputStreamReader streamReader = new InputStreamReader(stream);  
        BufferedReader reader = new BufferedReader(streamReader);     
        
      //  FileWriter fileWriter = new FileWriter(destino);  
      //  PrintWriter writer = new PrintWriter(fileWriter);
        
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        
        Lote l = new Lote();
        l.setId(dataSistema.getTimeInMillis());
        l.setDataEnvio(dataSistema.getTime());
        l.setIpOrigem("192.168.0.0");
        //l.setUsuarioEnvio(usuario);
        l.setNomeArquivo(file.getName());
        LoteHibernate lH = new LoteHibernate(session);
        lH.salva(l);
        
       // limparDescontos();
		new CriticaHibernate(session).limpa();
        new DescontoHibernate(session).limpa();
        
        DescontoHibernate dH = new DescontoHibernate(session);
               
        String line = null;
        int linha = 0;
        
        while(((line=reader.readLine())!=null)&&(erro.equals(""))) {  
            String campos[] = line.split(";");
            Desconto d = new Desconto();
            dH = new DescontoHibernate(session);
            d.setLinha(++linha);
            d.setLote(l);
            data.set(
            dataSistema.get(GregorianCalendar.YEAR) , dataSistema.get(GregorianCalendar.MONTH), 1);
            d.setReferencia(data.getTime());
            try {
            	System.out.println(linha + " -> "+ campos[0]+"  1-"+campos[1]+"  2-"+campos[2]+"  3-"+campos[3]+"  4-"+campos[4]+"  5-"+campos[5]+"  6-"+campos[6]+"  7-"+campos[7]+"  8-"+campos[8]+"  9-"+campos[9]);
            } 
            catch (java.lang.ArrayIndexOutOfBoundsException e) {
            	erro = "  Linha: "+linha + " - Número de Campos Inferior ao especificado no Lay-Out";
    			System.out.println(erro);
    		}
            
            d.setNome(campos[4]);

           	try{
           		d.setIdEmpresa(Integer.parseInt(campos[1]));
           		d.setMatricula(Integer.parseInt(campos[2]));
           	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Empresa / Matrícula  no formato 00 e 000000)";
    		}	

          	d.setIdOdontologico(campos[3]);
          	try{
          		d.setIdTipoCobertura(Integer.parseInt(campos[7]));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Tipo de Cobertura no formato 00)";
    		}
        	try{
        		d.setIdTipoDependente(Integer.parseInt(campos[8]));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Titularidade no formato 00)";
    		}
        	try{
        		d.setVlInformado(Float.parseFloat(campos[9].substring(0, 8)+'.'+campos[9].substring(8, 10)));
    		} catch (java.lang.StringIndexOutOfBoundsException e) {
    			erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Valor no formato 0000000000 )";
    		}         		
            try { 
            	data.set(Integer.parseInt(campos[0].substring(4, 8)) , Integer.parseInt(campos[0].substring(2, 4))-1, 1);
            	d.setOrigem(data.getTime());
            	data.set(Integer.parseInt(campos[5].substring(4, 8)) , Integer.parseInt(campos[5].substring(2, 4))-1, Integer.parseInt(campos[5].substring(0, 2)));
           		d.setNascimento(data.getTime());
            	data.set(Integer.parseInt(campos[6].substring(4, 8)) , Integer.parseInt(campos[6].substring(2, 4))-1, Integer.parseInt(campos[6].substring(0, 2)));
           		d.setAdesao(data.getTime());  	
    		} catch (java.lang.NumberFormatException e) {
    			erro = "  Linha: "+linha + " - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
    		} catch (java.lang.IllegalArgumentException e) {
    			erro = "  Linha: "+linha + " - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
    		} catch (java.lang.StringIndexOutOfBoundsException e) {
    			erro = "  Linha: "+linha + " - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
    		}
    		d.setCpf(campos[10]);
            d.setNomeMae(campos[11]);
            
            dH.salva(d);
              
        }
        if (!erro.isEmpty())
        	resultado = " Somente " + --linha + " registros foram lidos com sucesso.";
        else 
        	resultado = linha + " registros lidos com sucesso.";

       	files.add(file);
       // writer.close();  
       // fileWriter.close();  
        reader.close();  
        streamReader.close();  
        stream.close();  
        
        //mail.sendEmail("Sistema Odontológico - Carga de Arquivos", "Foi carregado o banco de dados de descontos odontológicos com "+linha+" registros.");
        


    	
	}
	
	
}
