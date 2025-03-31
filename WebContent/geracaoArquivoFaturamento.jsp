<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://primefaces.prime.com.tr/ui" prefix="p" %>
<f:loadBundle basename="com.imasf.resources.MessageResources" var="msg"/> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional// EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
    
    <f:view >

<aj4:form id="pricipalForm"  >	
<rich:panel  >
	<f:facet name="header" >
		<h:outputText value="#{geradorArquivoFaturamento.titulo}" style="text-align: center" />
	</f:facet>
	<h:panelGrid cellspacing="8" columns="3" id="botoesMenu">
		<aj4:commandButton id="home" value="Voltar para o Menu Principal" action="home" image="../../imagens/back.png"/>			
		<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="home">
            <span  style="white-space:nowrap">Clique para Voltar para o <strong>Menu Principal</strong></span>
        </rich:toolTip>

	</h:panelGrid>
	<br/>
	<h:outputText value="#{msg.confirmarGeracaoArquivoGreenline}" /> 
   
   <br/><br/>
	
	<h:panelGrid cellspacing="8" columns="3" >
	<h:graphicImage value="../../imagens/Kaptcha.jpg" id="imgKaptcha" rendered="#{empty geradorArquivoFaturamento.resumoProcessamento}"/>
    <h:inputText id="kaptchafield" value="#{geradorArquivoFaturamento.kaptchafield}" rendered="#{empty geradorArquivoFaturamento.resumoProcessamento}"/>
	<aj4:commandButton id="btnP2" value="#{msg.btnConfirmar}" action="#{geradorArquivoFaturamento.confirmarGeracao}" reRender="pricipalForm" rendered="#{empty geradorArquivoFaturamento.resumoProcessamento}"/>
		<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="btnP2">
            <span  style="white-space:nowrap">Clique para <strong>Gerar </strong> o arquivo CSV de Faturamento</span>
        </rich:toolTip>		
	<h:inputTextarea value="#{geradorArquivoFaturamento.resumoProcessamento}" rows="20" cols="180" rendered="#{not empty geradorArquivoFaturamento.resumoProcessamento}"/><br/>
	</h:panelGrid>

	
	<h:outputText value="#{geradorArquivoFaturamento.menssagemErro}" style="color:red" />
			
		
</rich:panel>
</aj4:form>

	<aj4:status onstart="#{rich:component('wait')}.show()" onstop="#{rich:component('wait')}.hide()" />

    <rich:modalPanel id="wait" autosized="true" width="200" height="120"  moveable="false" resizeable="false">
        <f:facet name="header">
            <h:outputText value="Processando" />
        </f:facet>
        <h:outputText value="Por favor aguarde ..." />
        <br/><br/><br/>
        <p align="center"><img src="../../imagens/ajax-loader.gif" align="top"/></p>
    </rich:modalPanel>              
      
	<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>      
        
    </f:view>
    </body>
</html>
