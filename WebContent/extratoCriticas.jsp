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
		<h2 align="center"><h:outputText value="#{gerenciadorCriticas.titulo}" styleClass="title" /></h2>
	</f:facet>
	<aj4:form id="menuForm" >
		<aj4:commandLink value="Home" action="index" /><br/><br/>
	</aj4:form>	
	<aj4:form id="pesquisaForm" ajaxSubmit="true" >
	    <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
	      <h:outputText value="Empresa: "  />
		  <h:inputText id="empresa" value="#{gerenciadorCriticas.extratoIdEmpresa}" size="20"></h:inputText>
		  <h:outputText value="Matricula: "  />
		  <h:inputText id="matricula" value="#{gerenciadorCriticas.extratoMatricula}" size="20"></h:inputText>
		  <br/>
	    </h:panelGrid>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorCriticas.gerarExtratoPorMatricula}" onclick="this.form.target='_blank'" type="submit"/>
	</aj4:form>
	<br/>
	


</rich:panel>             
        
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>        
        
    </f:view>
    </body>
</html>
