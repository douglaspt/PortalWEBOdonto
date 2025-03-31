package controle.negocio;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import dao.CriticaHibernate;
import dao.DescontoHibernate;
import dao.DescontoHistoricoHibernate;
import entidades.ClassificacaoCritica;
import entidades.Critica;
import entidades.Desconto;
import entidades.StatusDesconto;

/**
 * @author bruno.teixeira
 *
 */
public class GerenciadorDescontos extends Gerenciador{
		
	private String titulo = "Relação do Arquivo de Descontos";
	private String linha = "";
	private String idOdontologico = "";
	private String idEmpresa = "";
	private String matricula = "";
	private String nome = "";
	private String mesAno = "";
	private String descricaoCritica = "";
	private String obsCritica = "";
	private Calendar referencia = new GregorianCalendar();
	private Desconto desconto = new Desconto();
	private List<Desconto> descontos = new ArrayList<Desconto>();
	private DescontoHibernate dH = new DescontoHibernate(session);
	private DescontoHistoricoHibernate dhH = new DescontoHistoricoHibernate(session);
	private Critica critica = new Critica();
	private CriticaHibernate cH = new CriticaHibernate(session);
	

	public String download() throws IOException{ 
		System.out.println("Excel");
		ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	String destino = serveletContext.getRealPath("exportFiles/output1.csv");
    	
    	FileWriter fileWriter = new FileWriter( destino );
    	PrintWriter writer = new PrintWriter(fileWriter);
		for(Desconto d : descontos){  
			writer.println(d.getLinha()+";"+d.getReferencia()+";"+d.getIdEmpresa()+";"+d.getMatricula()+
			";"+d.getIdOdontologico()+";"+d.getNome()+";"+d.getNascimento()+";"+d.getAdesao()+
			";"+d.getIdTipoCobertura()+";"+d.getIdTipoDependente()+";"+d.getVlInformado());
		 }  
    	writer.close();  
    	fileWriter.close();
		
	     OperacoesArquivos.downloadFile("output1.csv", "/exportFiles/", "csv", FacesContext.getCurrentInstance());  
	    // deve ser passado o nome do arquivo+extensão  teste.txt   
	    return "";  
	}
	
	public String limpar(){
		descricaoCritica = "";
		obsCritica = "";
		desconto = new Desconto();
		return null;
	}
		
	public String editar(){
		obsCritica = "";
		descricaoCritica = "";
		desconto = (Desconto) objDataTable.getRowData();
		
		return null;
	}
	
	public String cancelaCritica(){	
		System.out.println(desconto.getNome());
		ClassificacaoCritica classificacaoCritica = new ClassificacaoCritica(900l); 
		List<Critica> criticas = new ArrayList<Critica>();
		criticas = cH.buscarPorDesconto(desconto.getId());
		for (Critica c : criticas){
		  c.setClassificacaoCritica(classificacaoCritica);	
		  System.out.println(c.getDesconto().getNome()+"  "+c.getClassificacaoCritica().getDescricao());
			
		}

		StatusDesconto s = new StatusDesconto(1l);
		desconto.setStatus(s);
		dH.salva(desconto);
		pesquisaDescontoPorParametro();
		return null;
	}
	
	public String salvaCritica(){
		System.out.println("salvar");
		ClassificacaoCritica classificacaoCritica = new ClassificacaoCritica(103l); 
		StatusDesconto s = new StatusDesconto(2l);
		critica.setDataCritica(data.getTime());
		critica.setDesconto(desconto);
		critica.setUsuario(getUsuarioLogado());
		critica.setClassificacaoCritica(classificacaoCritica);
		critica.setDescricaoCritica(descricaoCritica);
		critica.setObsCritica(obsCritica);
		cH.salva(critica);
		desconto.setStatus(s);
		dH.salva(desconto);
		pesquisaDescontoPorParametro();
		return "desconto";
	}
	
	public String pesquisaDescontoPorParametro() {
		Calendar d = new GregorianCalendar();
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
//		if ((referencia.get(referencia.MONTH) == d.get(d.MONTH)) & (referencia.get(referencia.YEAR) == d.get(d.YEAR))){
		descontos = dH.buscarPorParametros(linha, idOdontologico, nome, idEmpresa, matricula, referencia.getTime());	
		System.out.println("Atual");
		if (descontos.size()==0||descontos==null){
			descontos = dhH.buscarPorParametros(linha, idOdontologico, nome, idEmpresa, matricula, referencia.getTime());	
			System.out.println("HistoricoMovimentacao");
		}
		System.out.println(Integer.parseInt(mesAno.substring(3,7))+"   "+Integer.parseInt(mesAno.substring(0,2)));
		System.out.println(referencia.getTime()+"     "+ d.getTime());
		System.out.println(referencia.get(referencia.MONTH)+"  "+ d.get(d.MONTH)+"    "+referencia.get(referencia.YEAR)+"  "+ d.get(d.YEAR));
		return null;
	}
	

	public String pesquisar() {
		descontos = dH.buscarTodos();
		return null;
	}

	
	// getters e setters automaticos
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getIdOdontologico() {
		return idOdontologico;
	}

	public void setIdOdontologico(String idOdontologico) {
		this.idOdontologico = idOdontologico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Desconto getDesconto() {
		return desconto;
	}

	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}

	public List<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<Desconto> descontos) {
		this.descontos = descontos;
	}

	public DescontoHibernate getDH() {
		return dH;
	}

	public void setDH(DescontoHibernate dh) {
		dH = dh;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public DescontoHistoricoHibernate getDhH() {
		return dhH;
	}

	public void setDhH(DescontoHistoricoHibernate dhH) {
		this.dhH = dhH;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Calendar getReferencia() {
		return referencia;
	}

	public void setReferencia(Calendar referencia) {
		this.referencia = referencia;
	}

	public String getDescricaoCritica() {
		return descricaoCritica;
	}

	public void setDescricaoCritica(String descricaoCritica) {
		this.descricaoCritica = descricaoCritica;
	}

	public String getObsCritica() {
		return obsCritica;
	}

	public void setObsCritica(String obsCritica) {
		this.obsCritica = obsCritica;
	}

	public Critica getCritica() {
		return critica;
	}

	public void setCritica(Critica critica) {
		this.critica = critica;
	}

	public CriticaHibernate getCH() {
		return cH;
	}

	public void setCH(CriticaHibernate ch) {
		cH = ch;
	}

	public String goHome(){
		return "index";
	}

}
