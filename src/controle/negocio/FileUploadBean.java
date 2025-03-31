package controle.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import dao.CriticaHibernate;
import dao.DescontoHibernate;
import dao.HibernateUtil;
import dao.LoteHibernate;
import entidades.Desconto;
import entidades.Lote;
import entidades.Usuario;

public class FileUploadBean {
	
	private ArrayList<UploadItem> files = new ArrayList<UploadItem>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = false;
	private String erro = "";
	private String resultado = ""; 

	private Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	
	private GerenciadorEMail mail = new GerenciadorEMail();
	
	private void limparDescontos(){
		new CriticaHibernate(session).limpa();
        new DescontoHibernate(session).limpa();
	}

	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public FileUploadBean() {
	}

	public void listener(UploadEvent event) throws Exception{
		erro = "";
		resultado = "";
    	Calendar data = new GregorianCalendar();
    	data.setLenient(false);
    	Calendar dataSistema = new GregorianCalendar();
    	SimpleDateFormat sdfddMMyyyy_HHmmss = new SimpleDateFormat("ddMMyyyy_HHmm");
		
		ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	//String destino = serveletContext.getRealPath("../ROOT/imasf_docs/odontologico/afiliados_"+sdfddMMyyyy_HHmmss.format(new Date())+".csv");
		String destino = serveletContext.getRealPath("teste.csv");
		
    	//Date data = new Date();
    	
    	files.clear();
    	int linha = 0;
    	
        UploadItem item = event.getUploadItem();
        File file = item.getFile();
        
        FileInputStream stream = new FileInputStream(file);  
        InputStreamReader streamReader = new InputStreamReader(stream);  
        BufferedReader reader = new BufferedReader(streamReader);     
        
        FileWriter fileWriter = new FileWriter(destino);  
        PrintWriter writer = new PrintWriter(fileWriter);
        
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        LoteHibernate lH = new LoteHibernate(session);
        Date dataAtual = new Date();
        Date dataFolha = lH.buscaUltimo().getEnvioFolha();
        if (dataFolha != null && dataAtual.getMonth() == dataFolha.getMonth()
				&& dataAtual.getYear() == dataFolha.getYear()) {
			erro = " Não é possivel carregar arquivo pois a folha desse mês ja foi enviada";
			System.out.println(erro);
		} else {
			Lote l = new Lote();
			l.setId(dataSistema.getTimeInMillis());
			l.setDataEnvio(dataSistema.getTime());
			l.setIpOrigem("192.168.0.0");
			// l.setUsuarioEnvio(usuario);
			l.setNomeArquivo(item.getFileName());
			lH.salva(l);
			limparDescontos();
			DescontoHibernate dH = new DescontoHibernate(session);
			String nomeMae;
			String line = null;

			while (((line = reader.readLine()) != null) && (erro.equals(""))) {
				String campos[] = line.split(";");
				Desconto d = new Desconto();
				dH = new DescontoHibernate(session);
				d.setLinha(++linha);
				d.setLote(l);
				data.set(dataSistema.get(GregorianCalendar.YEAR),
						dataSistema.get(GregorianCalendar.MONTH), 1);
				d.setReferencia(data.getTime());

				try {
					System.out.println(linha + " -> " + campos[0] + "  1-"
							+ campos[1] + "  2-" + campos[2] + "  3-"
							+ campos[3] + "  4-" + campos[4] + "  5-"
							+ campos[5] + "  6-" + campos[6] + "  7-"
							+ campos[7] + "  8-" + campos[8] + "  9-"
							+ campos[9] + " 10-" + campos[10] + " 11-"
							+ campos[11]);
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					erro = "  Linha: "
							+ linha
							+ " - Número de Campos Inferior ao especificado no Lay-Out";
					System.out.println(erro);

				}

				d.setNome(campos[4]);

				try {
					d.setIdEmpresa(Integer.parseInt(campos[1]));
					d.setMatricula(Integer.parseInt(campos[2]));
				} catch (java.lang.NumberFormatException e) {
					erro = "  Linha: "
							+ linha
							+ " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Empresa / Matrícula  no formato 00 e 000000)";
				}

				d.setIdOdontologico(campos[3]);
				try {
					d.setIdTipoCobertura(Integer.parseInt(campos[7]));
				} catch (java.lang.NumberFormatException e) {
					erro = "  Linha: "
							+ linha
							+ " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Tipo de Cobertura no formato 00)";
				}
				try {
					d.setIdTipoDependente(Integer.parseInt(campos[8]));
				} catch (java.lang.NumberFormatException e) {
					erro = "  Linha: "
							+ linha
							+ " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Titularidade no formato 00)";
				}
				try {
					d.setVlInformado(Float.parseFloat(campos[9].substring(0, 8)
							+ '.' + campos[9].substring(8, 10)));
				} catch (java.lang.StringIndexOutOfBoundsException e) {
					erro = "  Linha: "
							+ linha
							+ " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Valor no formato 0000000000 )";
				}
				try {
					data.set(Integer.parseInt(campos[0].substring(4, 8)),
							Integer.parseInt(campos[0].substring(2, 4)) - 1, 1);
					d.setOrigem(data.getTime());

				} catch (java.lang.NumberFormatException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 0 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				} catch (java.lang.IllegalArgumentException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 0 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				} catch (java.lang.StringIndexOutOfBoundsException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 0 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				}

				try {
					data.set(Integer.parseInt(campos[5].substring(4, 8)),
							Integer.parseInt(campos[5].substring(2, 4)) - 1,
							Integer.parseInt(campos[5].substring(0, 2)));
					d.setNascimento(data.getTime());
				} catch (java.lang.NumberFormatException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 5 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				} catch (java.lang.IllegalArgumentException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 5 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				} catch (java.lang.StringIndexOutOfBoundsException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 5 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				}

				try {
					data.set(Integer.parseInt(campos[6].substring(4, 8)),
							Integer.parseInt(campos[6].substring(2, 4)) - 1,
							Integer.parseInt(campos[6].substring(0, 2)));
					d.setAdesao(data.getTime());
				} catch (java.lang.NumberFormatException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 6 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				} catch (java.lang.IllegalArgumentException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 6 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				} catch (java.lang.StringIndexOutOfBoundsException e) {
					erro = "  Linha: "
							+ linha
							+ " - Campo 6 - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DDMMYYYY - Ex. 01022009 )";
				}

				d.setCpf(campos[10]);
				try {
					d.setNomeMae(campos[11]);
				} catch (java.lang.Exception e) {
					erro = "  Linha: " + linha
							+ " - Campo Nome Mãe não preenchido";
				}
				dH.salva(d);
			}

		}
        
        if (!erro.isEmpty())
        	resultado = " Somente " + --linha + " registros foram lidos com sucesso.";
        else 
        	resultado = linha + " registros lidos com sucesso.";

       	files.add(item);
        writer.close();  
        fileWriter.close();  
        reader.close();  
        streamReader.close();  
        stream.close();  
        
        //mail.sendEmail("Sistema Odontológico - Carga de Arquivos", "Foi carregado o banco de dados de descontos odontológicos com "+linha+" registros.");
        
        uploadsAvailable--;
        
    }
	
	public String doUpload(){
		System.out.println("Fazendo Upload");
		return null;
	}
	
	public String doStop(){
		System.out.println("Stop Upload");
		return null;
	}
	
	public String doCancel(){
		System.out.println("Upload Cancelado");
		return null;
	}
	
	
	public String doClear(){
		System.out.println("Upload Limpo");
		return null;
	}
	
	public String clearUploadData() {
		erro = "";
		resultado = "";
        DescontoHibernate dH = new DescontoHibernate(session);
        dH.limpa();
		files.clear();
		setUploadsAvailable(5);
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public ArrayList<UploadItem> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadItem> files) {
		this.files = files;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	
	
}
