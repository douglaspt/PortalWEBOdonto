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
    </head>

    <body>
    <f:view >


<aj4:form id="edicaoForm" ajaxSubmit="true" >	
<rich:panel id="listagem" >
	<f:facet name="header" >
		<h:outputText value="Alterar Senha de Acesso" style="text-align: center" />
	</f:facet>
	<h:panelGrid cellspacing="8" columns="3" id="botoesMenu">
		<aj4:commandLink value="Home" action="#{gerenciadorResumo.goHome}" rendered="#{odontologicoBean.usuarioLogado.senha!='1234'}" immediate="true"/>
		<aj4:commandLink value="Home" action="login" rendered="#{odontologicoBean.usuarioLogado.senha=='1234'}" immediate="true"/>	
	</h:panelGrid>
	
	<h:panelGroup id="novoUsuario" rendered="#{odontologicoBean.usuarioLogado.senha=='novo123'}">
		<br/><br/>
		<h:outputText value="Prezado Sr., "  /><h:outputLabel value="#{odontologicoBean.usuarioLogado.pessoa.nomeCompleto}"/>
		<br/><br/>
		<h:outputText value="É necessário que você altere sua senha de acesso."  />
		<br/><br/>
		<h:outputText value="Lembramos que sua senha é pessoal e não deverá ser entregue a ninguém."  />
		<br/><br/><br/>
	</h:panelGroup>
	
	
	
    <h:panelGrid columns="3" >
	
		<h:outputText value="Digite Sua Senha Antiga: "  />
			<h:inputSecret  id="senhaA" value="#{gerenciadorUsuario.senhaAntiga}" size="10" required="true" requiredMessage="Digite a Senha Antiga">
			<rich:ajaxValidator event="onblur"/>
			</h:inputSecret>	
		<rich:message for="senhaA" style="color: darkred" />	
				
		<h:outputText value="Digite Sua Senha Nova: "  />
			<h:inputSecret  id="senhaN" value="#{gerenciadorUsuario.senhaNova}" size="10"  required="true" requiredMessage="Digite a sua Nova Senha" >
			<rich:ajaxValidator event="onblur"/>
			</h:inputSecret>	
		<rich:message for="senhaN" style="color: darkred" />	
		
		<h:outputText value="Digite Novamente Sua Senha Nova: "  />
			<h:inputSecret  id="senhaR" value="#{gerenciadorUsuario.senhaNovaValidacao}" size="10"  required="true" requiredMessage="Digite Novamente Sua Senha Nova" >
			<rich:ajaxValidator event="onblur"/>
			</h:inputSecret>	
		<rich:message for="senhaR" style="color: darkred" />	
			
	</h:panelGrid>
	<br/>
	<h:outputText value="Digite apenas números para sua senha."  />
	<br/>
			<aj4:commandButton id="ConfirmarEdicao" value="Confirmar" action="#{gerenciadorUsuario.mudarSenha}" reRender="edicaoForm" />
	<br/>
	<rich:panel id="mennsagem" bodyClass="rich-laguna-panel-no-header" rendered="#{not empty gerenciadorUsuario.menssagemErro}">   
		<h:outputText value="#{gerenciadorUsuario.menssagemErro}"  style="FONT-WEIGHT: bold;"/>  	 
	</rich:panel>
			
			
</rich:panel>
</aj4:form>

<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>
       
    </f:view>
    </body>
</html>
