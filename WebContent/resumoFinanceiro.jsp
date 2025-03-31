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
		<h2 align="center"><h:outputText value="#{gerenciadorResumoFinanceiro.titulo}" styleClass="title" /></h2>
	</f:facet>
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorResumoFinanceiro.goHome}" /><br/><br/>
	</aj4:form>
	<aj4:form id="pesquisaForm" ajaxSubmit="true" >
	    <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
	      <h:outputText value="Mês/Ano : "  />
		  <h:inputText id="mesAno" value="#{gerenciadorResumoFinanceiro.mesAno}" size="20" required="true" requiredMessage="Digite o Mês / Ano de Referência no Formato MM/AAAA Ex. 09/2009">
		    <rich:jQuery selector="#mesAno" query="mask('99/9999')" timing="onload"/>	
		  </h:inputText>
		  <h:message for="mesAno" style="color: darkred" />
		</h:panelGrid>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorResumoFinanceiro.pesquisar}" reRender="listaForm, pesquisaForm" />
	</aj4:form>
	<br/>
	
<aj4:form id="listaForm" >
<rich:panel id="listagem" rendered="#{not empty gerenciadorResumoFinanceiro.resumos}">
	<f:facet name="header" >
		<h:outputText value="#{gerenciadorResumoFinanceiro.titulo}" style="text-align: center" />
	</f:facet>
	<rich:dataTable id="resumos" border="1" value="#{gerenciadorResumoFinanceiro.resumos}" var="d" binding="#{gerenciadorResumoFinanceiro.objDataTable}" rendered="#{not empty gerenciadorResumoFinanceiro.resumos}" rows="10" width="100%">
		<rich:column >
			<f:facet name="header"><h:outputText value="Situação"/></f:facet>
			<h:outputText value="Desconto Recusado / Criticado" rendered="#{d.somaValorPago == 0 && d.valorCobrar == 0}" style="color: darkred" />
			<h:outputText value="Desconto em Folha / Pago"  rendered="#{d.somaValorPago > 0}"/>
			<h:outputText value="Desconto em Aberto"  rendered="#{d.somaValorPago == 0 && d.valorCobrar > 0}" />
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
			<h:outputText value="#{d.somaValorPago}"  style="color: darkblue" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
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
        
	 <h:commandLink id="linkPDF" rendered="#{not empty gerenciadorResumoFinanceiro.resumos}">  
	 	 <p:graphicImage value="images/pdf.png" style="border:none"/>  
    	 <p:dataExporter type="pdf" target="resumos" fileName="resumos"/> 
	 </h:commandLink> 
	 <h:commandLink  id="linkExcel" rendered="#{not empty gerenciadorResumoFinanceiro.resumos}">  
	 	 <p:graphicImage value="images/excel.png"  style="border:none"/>  
    	 <p:dataExporter type="xls" target="resumos" fileName="resumos"/> 
	 </h:commandLink>	
	 <h:commandLink  id="linkCVS" rendered="#{not empty gerenciadorResumoFinanceiro.resumos}">  
	 	 <p:graphicImage value="images/csv.png"  style="border:none"/>  
    	 <p:dataExporter type="csv" target="resumos" fileName="resumos"/> 
	 </h:commandLink>		
    
</rich:panel>
</aj4:form>	
	

</rich:panel>             
 <rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>       
    </f:view>
    </body>
</html>
