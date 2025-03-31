package dao;

import java.util.Date;
import java.util.List;

import entidades.Desconto;
import entidades.HistoricoMovimentacao;


public interface HistoricoMovimentacaoDao {
	
	public abstract HistoricoMovimentacao busca(Long id);
	
	public abstract void salva(HistoricoMovimentacao u);
	
	public abstract void exclui(HistoricoMovimentacao u);

	List<HistoricoMovimentacao> busca(Desconto desconto, Date agendaInicial,
			Date agendaFinal, boolean pesquisaAgendaNull, Date baixaInicial,
			Date baixaFinal, Date canceladoInicial, Date canceladoFinal);



}
