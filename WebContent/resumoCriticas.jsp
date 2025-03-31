<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://primefaces.prime.com.tr/ui" prefix="p" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional// EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/odontologico/resources/jquery.maskedinput-1.2.1.js"></script>
    </head>

    <body>
    <f:view >

<rich:panel  >
	<f:facet name="header">
		<h2 align="center"><h:outputText value="#{gerenciadorResumo.titulo}" styleClass="title" /></h2>
	</f:facet>
	
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorResumo.goHome}" /><br/><br/>
	</aj4:form>
	
	<aj4:form id="pesquisaForm" ajaxSubmit="true" rendered="#{not gerenciadorResumo.processamentoCritica}">
	    <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
	      <h:outputText value="Mês/Ano : "  />
		  <h:inputText id="mesAno" value="#{gerenciadorResumo.mesAno}" size="20" required="true" requiredMessage="Digite o Mês / Ano de Referência no Formato MM/AAAA Ex. 09/2009">
		    <rich:jQuery selector="#mesAno" query="mask('99/9999')" timing="onload"/>	
		  </h:inputText>
		  <h:message for="mesAno" style="color: darkred" />
		</h:panelGrid>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorResumo.pesquisar}" reRender="listaForm, pesquisaForm" />
	</aj4:form>
	<br/>

	<aj4:form id="criticaForm" rendered="#{gerenciadorResumo.processamentoCritica}">
	<rich:panel id="listagem" >
		<f:facet name="header" >
			<h:outputText value="Criticar Descontos" style="text-align: center" />
		</f:facet>
		<aj4:commandButton id="btnP" value="Realizar Criticas" action="#{gerenciadorResumo.processarCriticas}" reRender="listaForm, pesquisaForm" />
	</rich:panel>
	</aj4:form>	

	
<aj4:form id="listaForm" >
<rich:panel id="listagem" rendered="#{not empty gerenciadorResumo.resumos}">
	<f:facet name="header" >
		<h:outputText value="#{gerenciadorResumo.titulo}" style="text-align: center" />
	</f:facet>
	<rich:dataTable id="resumos" border="1" value="#{gerenciadorResumo.resumos}" var="d" binding="#{gerenciadorResumo.objDataTable}" rendered="#{not empty gerenciadorResumo.resumos}" rows="40" width="100%">
		<rich:column sortBy="#{d.descricao}">
			<f:facet name="header"><h:outputText value="Descrição"/></f:facet>
			<h:outputText value="#{d.descricao}" rendered="#{d.classificacaoCritica.id < 900}" style="color: darkred" />
			<h:outputText value="#{d.descricao}" rendered="#{d.classificacaoCritica.id > 900 && d.classificacaoCritica.id < 1000}"  style="color: darkblue" />
			<h:outputText value="#{d.descricao}" rendered="#{d.classificacaoCritica.id > 999}"  style="color: darkgreen" />			
		</rich:column>
		<rich:column sortBy="#{d.qtde}">
			<f:facet name="header"><h:outputText value="Qtde."/></f:facet>
			<h:outputText value="#{d.qtde}" />
		</rich:column>
		
		<rich:column sortBy="#{d.valorInformado}">
			<f:facet name="header"><h:outputText value="Valor Informado"/></f:facet>
			<h:outputText value="#{d.valorInformado}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.somaValorInformado}">
			<f:facet name="header"><h:outputText value="Total Valor Informado"/></f:facet>
			<h:outputText value="#{d.somaValorInformado}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.valorCobrar}">
			<f:facet name="header"><h:outputText value="Valor a Cobrar"/></f:facet>
			<h:outputText value="#{d.valorCobrar}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>			
		<rich:column sortBy="#{d.somaValorCobrar}">
			<f:facet name="header"><h:outputText value="Total Valor a Cobrar"/></f:facet>
			<h:outputText value="#{d.somaValorCobrar}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.somaValorPago}">
			<f:facet name="header"><h:outputText value="Total Valor a Pagar"/></f:facet>
			<h:outputText value="#{d.somaValorPago}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.descricao}">
			<f:facet name="header"><h:outputText value="Funções"/></f:facet>
			<aj4:commandButton id="filtro" action="#{gerenciadorResumo.verCriticas}" reRender="listaForm" image="images/5days.png"/>
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="filtro">
            	<span  style="white-space:nowrap">Clique para <strong>Acessar os Descontos</strong> desta Crtítica/Classificação.</span>
      		</rich:toolTip>
		</rich:column>		
		<f:facet name="footer">
			<rich:datascroller for="resumos"></rich:datascroller>
		</f:facet>		
	</rich:dataTable>
	<br/>
	
    <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkPDF">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong> PDF</strong>.	</span>
    </rich:toolTip>
    <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkExcel">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong> Excel</strong>.	</span>
    </rich:toolTip>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkCVS">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong>CVS</strong> (Arquivo TXT).	</span>
    </rich:toolTip>
        
	 <h:commandLink  id="linkExcel" rendered="#{not empty gerenciadorResumo.resumos}">  
	 	 <p:graphicImage value="images/excel.png"  style="border:none"/>  
    	 <p:dataExporter type="xls" target="resumos" fileName="resumos"/> 
	 </h:commandLink>	
	 <h:commandLink  id="linkCVS" rendered="#{not empty gerenciadorResumo.resumos}">  
	 	 <p:graphicImage value="images/csv.png"  style="border:none"/>  
    	 <p:dataExporter type="csv" target="resumos" fileName="resumos"/> 
	 </h:commandLink>		
    
</rich:panel>
</aj4:form>	
	

</rich:panel>             
        
	<aj4:status onstart="#{rich:component('wait')}.show()" onstop="#{rich:component('wait')}.hide()" />
	   
    <rich:modalPanel id="wait" autosized="true" width="200" height="120"
        moveable="false" resizeable="false">
        <f:facet name="header">
            <h:outputText value="Processando" />
        </f:facet>
        <h:outputText value="Por favor aguarde ..." />
        <br/><br/>
        <h:outputText value="Este processamento pode demorar alguns minutos." />
        <br/><br/><br/>
        <p align="center"><img src="images/ajax-loader.gif" align="top"/></p>
    </rich:modalPanel>        
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>        
    </f:view>
    </body>
</html>
