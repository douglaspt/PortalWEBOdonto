package testes;

import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.Session;

import dao.CriticaHistoricoHibernate;
import dao.HibernateUtil;


public class TestaJasperReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "C:/workspace/odontologico-processamento/src/testes/reportRelacaoDescontos.jasper";
		String outFileName = "C:/workspace/odontologico-processamento/src/testes/reportRelacaoDescontos.pdf";
		HashMap hm = new HashMap();
		//hm.put("", "");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List criticas = new CriticaHistoricoHibernate(session).buscarPorParametros(null, null, null, "5", "61273", null, null, null, null);
		System.out.println(criticas.size());
		session.close();
		
		JRBeanArrayDataSource jrdts = new JRBeanArrayDataSource(criticas.toArray());  
		
		try {
	           // Fill the report using an empty data source
            JasperPrint print = JasperFillManager.fillReport(fileName, hm, jrdts);
                       
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();
            
            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            
            // Export the PDF file
            exporter.exportReport();

        } catch (JRException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }


	}

}
