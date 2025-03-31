<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional// EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

     <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/estilo.css" />
    </head>

    <body>


	<aj4:form id="formAcoesDoDia" >
	
	<rich:simpleTogglePanel switchType="client" label="Ações do dia ..." >
	<h:panelGrid columns="3">
		<h:outputText value="Visualizar: "  />
		<rich:comboBox id="tipoPesquisa" value="#{gerenciadorHistorico.filtroPeriodo}" required="false" requiredMessage="Selecione o Tipo de Pesquisa" >
		<aj4:support action="#{gerenciadorHistorico.atualizarPeriodo}" event="onselect" reRender="dvi, dvf" ajaxSingle="true" />
			<f:selectItem itemValue="não baixados"/>
            <f:selectItem itemValue="dia"/>
            <f:selectItem itemValue="semana atual"/>	
            <f:selectItem itemValue="mês atual"/>
 			<f:selectItem itemValue="próximos 5 dias"/>
 			<f:selectItem itemValue="próximos 30 dias"/>
            <rich:ajaxValidator event="onblur"/>
		</rich:comboBox>
		<aj4:commandButton id="atualizar" value="Consultar" action="#{gerenciadorHistorico.abrirAcoesDoDia}" reRender="formAcoesDoDia"/>
	</h:panelGrid>

       <rich:dataTable id="historicos" border="1" value="#{gerenciadorHistorico.historicoMovimentacoes}" var="c" binding="#{gerenciadorHistorico.objDataTable}" rendered="#{not empty gerenciadorHistorico.historicoMovimentacoes}" rows="8" width="100%">
		<rich:column sortBy="#{c.agenda}">
			<f:facet name="header"><h:outputText value="Agenda"/></f:facet>
			<h:outputText value="#{c.agenda}" ><f:convertDateTime pattern = "dd/MM/yyyy HH:mm:ss"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.usuario.login}">
			<f:facet name="header"><h:outputText value="Usuario"/></f:facet>
			<h:outputText value="#{c.usuario.login}" />
		</rich:column>
		<rich:column sortBy="#{c.descricao}">
			<f:facet name="header"><h:outputText value="Descrição / Observação"/></f:facet>
			<h:outputText value="#{c.descricao}" />
		</rich:column>
		<rich:column sortBy="#{c.baixa}">
			<f:facet name="header"><h:outputText value="Baixa"/></f:facet>
			<h:outputText value="#{c.baixa}" ><f:convertDateTime pattern = "dd/MM/yyyy HH:mm:ss"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.cancelamento}">
			<f:facet name="header"><h:outputText value="Cancelamento"/></f:facet>
			<h:outputText value="#{c.cancelamento}" ><f:convertDateTime pattern = "dd/MM/yyyy HH:mm:ss"/></h:outputText>
		</rich:column>
		<h:column>
			<f:facet name="header"><h:outputText value="Funções"  /></f:facet>
			<aj4:commandButton id="editarButton" action="#{gerenciadorHistorico.editarNovo}" reRender="edicaoAcoesForm" image="images/alarmd.png"/>
			<rich:componentControl for="panelHistorico" attachTo="editarButton" operation="show" event="onclick"   />
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="editarButton">
            	<span  style="white-space:nowrap">Clique para CRIAR uma nova<strong>Agenda / Alerta / Histórico</strong> a partir deste.</span>
        	</rich:toolTip>
        	<aj4:commandButton id="baixarButton" action="#{gerenciadorHistorico.baixarAcaoDoDia}" reRender="formAcoesDoDia" image="images/compfile.png"  onclick="alert('Histórico Baixado com Sucesso!')" rendered="#{empty c.baixa && empty c.cancelamento}"/>
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="baixarButton">
            	<span  style="white-space:nowrap">Clique para Confirmar a <strong>Baixa</strong> deste Evento.</span>
        	</rich:toolTip>
        	<aj4:commandButton id="cancelaButton" action="#{gerenciadorHistorico.cancelarAcaoDoDia}" reRender="formAcoesDoDia" image="images/delete.png"  onclick="alert('Atenção! Histórico CANCELADO com Sucesso!')" rendered="#{empty c.baixa && empty c.cancelamento}"/>
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="cancelaButton">
            	<span  style="white-space:nowrap">Clique para Confirmar o <strong>Cancelamento</strong> deste Evento.</span>
        	</rich:toolTip>  
		</h:column>			
		
		<f:facet name="footer">
			<rich:datascroller for="historicos"></rich:datascroller>
		</f:facet>		
	</rich:dataTable>	

		<aj4:commandButton id="btnNovo" value="Cadastrar Nova Ação" action="#{gerenciadorHistorico.prepararNovo}" reRender="edicaoAcoesForm"/>
		<rich:componentControl for="panelHistorico" attachTo="btnNovo" operation="show" event="onclick"   />
		<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="btnNovo">
            <span  style="white-space:nowrap">Clique para <strong>Cadastrar uma Nova</strong>Ação do Dia / Lembrete / Histórico / Follow up ...</span>
        </rich:toolTip>


	</rich:simpleTogglePanel>

	</aj4:form>
	
	
<rich:modalPanel id="panelHistorico" width="500" height="350">
<f:facet name="header" >
<h:panelGroup  style="text-align: center">
   	<h:outputText value="#{gerenciadorHistorico.titulo}"/>
</h:panelGroup>
</f:facet> 
<aj4:form id="edicaoAcoesForm" ajaxSubmit="true" oncomplete="">	
    <h:panelGrid columns="2" >
    	    
		<h:outputText value="Descrição/Observação: "  />
			<h:inputTextarea id="nome" value="#{gerenciadorHistorico.historicoMovimentacao.descricao}" rows="6" cols="60" required="true" requiredMessage="Digite o Texto do Histórico/Agenda"  >
			<rich:ajaxValidator event="onblur"/>
			</h:inputTextarea>		
		
    	<h:outputText value="Agendar/Alertar para: "  />
		<rich:calendar id="dpp" datePattern="dd/MM/yyyy HH:mm" value="#{gerenciadorHistorico.historicoMovimentacao.agenda}" required="true" requiredMessage="Digite a Data para Alerta da Agenda"  enableManualInput="true">
			<rich:ajaxValidator event="onblur"/>
		</rich:calendar>
			
	</h:panelGrid>
	<br/>	
	<rich:message for="nome" style="color: darkred" />
	<rich:message for="dpp" style="color: darkred" />	
	<br/>
	<br/>
	<br/>
	<h:panelGrid columns="2"cellspacing="8" >
		<aj4:commandButton id="ConfirmarSalvarAcaoDoDia" value="Confirmar" action="#{gerenciadorHistorico.salvarAcaoDoDia}" reRender="edicaoAcoesForm, formAcoesDoDia" />
		<aj4:commandButton id="CancelarEdicao" value="Cancelar" reRender="edicaoAcoesForm"/>  
		<rich:componentControl for="panelHistorico" attachTo="CancelarEdicao" operation="hide" event="onclick" />
		  
	</h:panelGrid>
	
	  
</aj4:form>    
</rich:modalPanel>   	

    </body>
</html>
