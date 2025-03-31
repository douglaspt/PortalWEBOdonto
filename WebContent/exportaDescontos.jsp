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
		<h2 align="center"><h:outputText value="#{gerenciadorExportacao.titulo}" styleClass="title" /></h2>
	</f:facet>
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorExportacao.goHome}" /><br/><br/>
	</aj4:form>	
	<aj4:form id="pesquisaForm" ajaxSubmit="true" >
		<h:message for="mesAno" style="color: darkred" />
        <h:panelGrid columns="3" cellspacing="4" cellpadding="4">
			<h:outputText value="Mês/Ano : "  />
		  	<h:inputText id="mesAno" value="#{gerenciadorExportacao.mesAno}" size="20" required="true" requiredMessage="É Obrigatório o preenchimento do Mês / Ano de Referência no Formato MM/AAAA Ex. 09/2009">
		    	<rich:jQuery selector="#mesAno" query="mask('99/9999')" timing="onload"/>	
		  	</h:inputText>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorExportacao.pesquisar}" reRender="listaForm, pesquisaForm, controler, descontos"/>
		</h:panelGrid>  
	</aj4:form>
	<br/>
	
<aj4:form id="listaForm" >
<rich:panel id="listagem" rendered="#{not empty gerenciadorExportacao.descontos}">
	<f:facet name="header" >
		<h:outputText value="#{gerenciadorExportacao.titulo}" style="text-align: center" />
	</f:facet>
	<rich:dataTable id="descontos" border="1" value="#{gerenciadorExportacao.descontos}" var="c" binding="#{gerenciadorExportacao.objDataTable}" rendered="#{not empty gerenciadorExportacao.descontos}" rows="10" width="100%">
		<rich:column sortBy="#{c.origem}">
			<f:facet name="header"><h:outputText value="Referencia"/></f:facet>
			<h:outputText value="#{c.origem}" ></h:outputText>
		</rich:column>		
		<rich:column sortBy="#{c.idEmpresa}">
			<f:facet name="header"><h:outputText value="Emp."/></f:facet>
			<h:outputText value="#{c.idEmpresa}" />
		</rich:column>		
		<rich:column sortBy="#{c.matricula}">
			<f:facet name="header"><h:outputText value="Matricula"/></f:facet>
			<h:outputText value="#{c.matricula}" />
		</rich:column>
		<rich:column sortBy="#{c.idOdontologico}">
			<f:facet name="header"><h:outputText value="ID Odonto"/></f:facet>
			<h:outputText value="#{c.idOdontologico}" />
		</rich:column>	
		<rich:column sortBy="#{c.desconto.nome}">
			<f:facet name="header"><h:outputText value="Nome"/></f:facet>
			<h:outputText value="#{c.nome}" />
		</rich:column>
		<rich:column sortBy="#{c.nascimento}">
			<f:facet name="header"><h:outputText value="Nascimento"/></f:facet>
			<h:outputText value="#{c.nascimento}" ></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.adesao}">
			<f:facet name="header"><h:outputText value="Adesão"/></f:facet>
			<h:outputText value="#{c.adesao}" ></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.idTipoCobertura}">
			<f:facet name="header"><h:outputText value="Cobertura"/></f:facet>
			<h:outputText value="#{c.idTipoCobertura}" />
		</rich:column>
		<rich:column sortBy="#{c.idTipoDependente}">
			<f:facet name="header"><h:outputText value="Tit."/></f:facet>
			<h:outputText value="#{c.idTipoDependente}" />
		</rich:column>
		<rich:column sortBy="#{c.vlInformado}">
			<f:facet name="header"><h:outputText value="Informado"/></f:facet>
			<h:outputText value="#{c.vlInformado}" ></h:outputText>
		</rich:column>		
		<rich:column sortBy="#{c.cpf}">
			<f:facet name="header"><h:outputText value="CPF"/></f:facet>
			<h:outputText value="#{c.cpf}" />
		</rich:column>
		<rich:column sortBy="#{c.nomeMae}">
			<f:facet name="header"><h:outputText value="Nome da Mãe"/></f:facet>
			<h:outputText value="#{c.nomeMae}" />
		</rich:column>		

		<rich:column sortBy="#{c.linha}">
			<f:facet name="header"><h:outputText value="Linha"/></f:facet>
			<h:outputText value="#{c.linha}" />
		</rich:column>
		<rich:column sortBy="#{c.vlPagar}">
			<f:facet name="header"><h:outputText value="Valor Pagar"/></f:facet>
			<h:outputText value="#{c.vlPagar}" ></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.vlPago}">
			<f:facet name="header"><h:outputText value="Valor Pago"/></f:facet>
			<h:outputText value="#{c.vlPago}" ></h:outputText>
		</rich:column>	
		<rich:column sortBy="#{c.descricaoStatus}">
			<f:facet name="header"><h:outputText value="Status"/></f:facet>
			<h:outputText value="#{c.descricaoStatus}" />
		</rich:column>

		<f:facet name="footer">
			<rich:datascroller id="controler" for="descontos"></rich:datascroller>
		</f:facet>
				
	</rich:dataTable>
	<br/>
	

    <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkExcel">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong> Excel</strong>.	</span>
    </rich:toolTip>
        
	 <h:commandLink  id="linkExcel"  rendered="#{not empty gerenciadorExportacao.descontos}">  
	 	 <p:graphicImage value="images/excel.jpg"  style="border:none"/>  
    	 <p:dataExporter type="xls" target="descontos" fileName="criticas"/> 
	 </h:commandLink>	
	 
</rich:panel>
</aj4:form>		



</rich:panel>             
        
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>        
        
    </f:view>
    </body>
</html>
