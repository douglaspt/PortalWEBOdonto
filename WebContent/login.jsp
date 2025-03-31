<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bem Vindo ao Sistema IMASF - Menu Principal</title>
</head>
<body > 
    <SCRIPT LANGUAGE="JavaScript">    {
    	window.history.forward(1);
	}</SCRIPT> 
<f:view>


<aj4:form id="principalForm" ajaxSubmit="true" >
	<rich:panel>
	<f:facet name="header">
		<h2 align="center"><h:outputText value="IMASF - Sistema de Processamento de Descontos Odontológicos" styleClass="title" /></h2>
	</f:facet>
	
	<h:outputText value="Sistema de Processamento de Descontos Odontológicos " style="FONT-WEIGHT: bold;font-size: 22px;color:#198A8A" />
		
	<h:panelGrid columns="3" cellspacing="20">

	<h:panelGroup>
		<img src="images/escovas.jpg"  />
	</h:panelGroup>


	<h:panelGroup >		

	<h:panelGrid columns="3">

		<h:outputText value="Usuário: " style="font-size: 14px" />
		<h:inputText id="usuario" value="#{odontologicoBean.login}" size="20" required="true" requiredMessage="Digite o Usuário" >
		</h:inputText>
		<h:message for="usuario" style="color: darkred" />
		<h:outputText value="Senha: "  style="font-size: 14px" />
		<h:inputSecret id="senha" value="#{odontologicoBean.senha}" size="20" required="true" requiredMessage="Digite a Senha" />
		<h:message for="senha" style="color: darkred" />
		<br><br><br><br>		
		<aj4:commandButton id="entrar" value="Entrar no Sistema" action="#{odontologicoBean.pesquisaUsuario}" reRender="principalForm" type="submit"  style="font-size: 14px" />	
	</h:panelGrid>
		<br/>
		<rich:panel id="mennsagem" bodyClass="rich-laguna-panel-no-header" rendered="#{not empty odontologicoBean.menssagemErro}">   
			<h:outputText value="#{odontologicoBean.menssagemErro}"  style="FONT-WEIGHT: bold;"/>
			<p>Verifique se digitou corretamente seu <span>Usuário</span> e sua <span>senha.</span></p>     	 
		</rich:panel>
		
	<br/><br/><br/><br/>
	<img src="images/logo_imasf.jpg"  />
	<br/><br/>
		
	</h:panelGroup>

	</h:panelGrid>
	
	<p align="right"><h:outputText value="Desenvolvido por " style="border:none;font-size: 14px;color:#198A8A" /><a href="http://www.horizontesistemas.com" target="_blank"><h:graphicImage value="images/pch.jpg" style="align:right;border:none;font-size: 10px;color:#198A8A"/></a></p>
		
	</rich:panel>
</aj4:form>	

</f:view>
</body>
</html>