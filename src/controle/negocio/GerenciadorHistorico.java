package controle.negocio;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.component.UIData;

import org.joda.time.DateTime;

import dao.HistoricoMovimentacaoDao;
import dao.HistoricoMovimentacaoHibernate;
import entidades.Desconto;
import entidades.HistoricoMovimentacao;


/**
 * @author bruno.teixeira
 *
 */

public class GerenciadorHistorico extends Gerenciador{
	
	private String titulo = "Historico de Contratos";
	
	private Desconto desconto = new Desconto();
	
	private Date agendaInicial, agendaFinal, baixaInicial, baixaFinal, canceladoInicial, canceladoFinal = null;
	private String filtroPeriodo = "não baixados";
//	private boolean acoesDoDia = false;
	
	
	private HistoricoMovimentacao historicoMovimentacao = new HistoricoMovimentacao();
	private UIData objDataTable;
	private List<HistoricoMovimentacao> historicoMovimentacoes = new ArrayList<HistoricoMovimentacao>();
	
	private HistoricoMovimentacaoDao historicoMovimentacaoDao = new HistoricoMovimentacaoHibernate(session);
		
	
	public String prepararNovo(){
		limpar();
		return null;
	}
	

	public void salvarNovoHistorico(Desconto desconto, String descricao){
		HistoricoMovimentacao historico = new HistoricoMovimentacao(desconto, getUsuarioLogado(), descricao);
//		logger.info("Sanvando HistÃ³rico Contrato N."+historico.getContrato().getIdSequencia() + " - "+historico.getDescricao());
		historicoMovimentacaoDao.salva(historico);
		
	}
	
	public String salvar(){
		logger.info("Sanvando HistÃ³rico Manual " + " - "+historicoMovimentacao.getDescricao());
		historicoMovimentacaoDao.salva(historicoMovimentacao);
		pesquisar();
		return "historicoMovimentacao";
	}
	
	
	public String salvarAcaoDoDia(){
		System.out.println("Sanvando Historico Manual "+historicoMovimentacao.getDescricao() + " - "+historicoMovimentacao.getUsuario().getLogin());
		logger.info("Sanvando Historico Manual "+historicoMovimentacao.getDescricao() + " - "+historicoMovimentacao.getUsuario().getLogin());
		historicoMovimentacaoDao.salva(historicoMovimentacao);
		pesquisar();
		return "index";
	}
	
	public String baixar(){
		editar();
		historicoMovimentacao.setBaixa(new GregorianCalendar().getTime());
		return salvar();
	}
	
	public String cancelar(){
		editar();
		historicoMovimentacao.setCancelamento(new GregorianCalendar().getTime());
		return salvar();
	}
	
	public String baixarAcaoDoDia(){
		baixar();
		return "index";
	}
	
	public String cancelarAcaoDoDia(){
		cancelar();
		return "index";
	}

	public String editar(){
		historicoMovimentacao = (HistoricoMovimentacao) objDataTable.getRowData();
		return null;
	}	
	
	public String editarNovo(){
		historicoMovimentacao = (HistoricoMovimentacao) objDataTable.getRowData();
		historicoMovimentacao = new HistoricoMovimentacao(historicoMovimentacao, getUsuarioLogado());
//		logger.info("Preparando Novo HistÃ³rico Manual "+historicoMovimentacao.getId());
		return null;
	}	
	
	public String abrirContrato(){
//		acoesDoDia = false;
		return pesquisar();
	}	
	
	public String abrirAcoesDoDia(){
//		acoesDoDia = true;
		//filtroPeriodo = "não baixados";
		atualizarPeriodo();
		return pesquisar();
	}
	
	public void atualizarPeriodo() {
		DateTime d = new DateTime(new Date());
		if (filtroPeriodo.equals("não baixados")||filtroPeriodo.equals("")){
			agendaInicial = null;
			agendaFinal = null;
		}
		if (filtroPeriodo.equals("dia")){
			d = d.minusHours(d.getHourOfDay()).minusMinutes(d.getMinuteOfHour());
			agendaInicial = d.toDate();
			d = d.plusHours(23).plusMinutes(59);
			agendaFinal = d.toDate();
		}
		if (filtroPeriodo.equals("semana atual")){
			d = d.withDayOfWeek(1);
			d = d.minusHours(d.getHourOfDay()).minusMinutes(d.getMinuteOfHour());
			agendaInicial = d.toDate();
			d = d.withDayOfWeek(7);
			d = d.plusHours(23).plusMinutes(59);
			agendaFinal = d.toDate();
		}
		if (filtroPeriodo.equals("mês atual")){
			d = d.minusHours(d.getHourOfDay()).minusMinutes(d.getMinuteOfHour()).minusDays(d.getDayOfMonth()-1) ;
			agendaInicial = d.toDate();
			d = d.plusHours(23).plusMinutes(59).plusMonths(1).minusDays(1);
			agendaFinal = d.toDate();
		}
		if (filtroPeriodo.equals("próximos 5 dias")){
			d = d.minusHours(d.getHourOfDay()).minusMinutes(d.getMinuteOfHour());
			agendaInicial = d.toDate();
			d = d.plusHours(23).plusMinutes(59).plusDays(5);
			agendaFinal = d.toDate();
		}
		if (filtroPeriodo.equals("próximos 30 dias")){
			d = d.minusHours(d.getHourOfDay()).minusMinutes(d.getMinuteOfHour());
			agendaInicial = d.toDate();
			d = d.plusHours(23).plusMinutes(59).plusDays(30);
			agendaFinal = d.toDate();
		}
		logger.info("Atualizando Periodo "+ filtroPeriodo+ " - "+ agendaInicial+" - "+agendaFinal);		
	}


	public String pesquisar(Desconto desconto) {
		filtroPeriodo = "";
		atualizarPeriodo();
		this.desconto = desconto;
		return pesquisar();
	}

	
	public String pesquisar() {
		System.out.println("Pesquisando Historico - "+filtroPeriodo.equals("não baixados"));		
		historicoMovimentacoes = historicoMovimentacaoDao.busca(desconto, agendaInicial, agendaFinal, filtroPeriodo.equals("não baixados"), baixaInicial, baixaFinal, canceladoInicial, canceladoFinal);
		return "historicoMovimentacao";
	}	
	
	
	public String limpar(){
		historicoMovimentacao = new HistoricoMovimentacao();
		historicoMovimentacao.setUsuario(getUsuarioLogado());
		return null;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Desconto getDesconto() {
		return desconto;
	}


	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}


	public Date getAgendaInicial() {
		return agendaInicial;
	}


	public void setAgendaInicial(Date agendaInicial) {
		this.agendaInicial = agendaInicial;
	}


	public Date getAgendaFinal() {
		return agendaFinal;
	}


	public void setAgendaFinal(Date agendaFinal) {
		this.agendaFinal = agendaFinal;
	}


	public Date getBaixaInicial() {
		return baixaInicial;
	}


	public void setBaixaInicial(Date baixaInicial) {
		this.baixaInicial = baixaInicial;
	}


	public Date getBaixaFinal() {
		return baixaFinal;
	}


	public void setBaixaFinal(Date baixaFinal) {
		this.baixaFinal = baixaFinal;
	}


	public Date getCanceladoInicial() {
		return canceladoInicial;
	}


	public void setCanceladoInicial(Date canceladoInicial) {
		this.canceladoInicial = canceladoInicial;
	}


	public Date getCanceladoFinal() {
		return canceladoFinal;
	}


	public void setCanceladoFinal(Date canceladoFinal) {
		this.canceladoFinal = canceladoFinal;
	}


	public String getFiltroPeriodo() {
		return filtroPeriodo;
	}


	public void setFiltroPeriodo(String filtroPeriodo) {
		this.filtroPeriodo = filtroPeriodo;
	}


	public HistoricoMovimentacao getHistoricoMovimentacao() {
		return historicoMovimentacao;
	}


	public void setHistoricoMovimentacao(HistoricoMovimentacao historicoMovimentacao) {
		this.historicoMovimentacao = historicoMovimentacao;
	}


	public UIData getObjDataTable() {
		return objDataTable;
	}


	public void setObjDataTable(UIData objDataTable) {
		this.objDataTable = objDataTable;
	}


	public List<HistoricoMovimentacao> getHistoricoMovimentacoes() {
		return historicoMovimentacoes;
	}


	public void setHistoricoMovimentacoes(
			List<HistoricoMovimentacao> historicoMovimentacoes) {
		this.historicoMovimentacoes = historicoMovimentacoes;
	}







	

	

}
