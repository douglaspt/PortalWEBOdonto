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


<aj4:form id="listaForm" ajaxSubmit="true" >	
<rich:panel id="listagem" >
	<f:facet name="header" >
		<h:outputText value="#{gerenciadorUsuario.titulo}" style="text-align: center" />
	</f:facet>
	<aj4:commandLink value="Home" action="#{gerenciadorUsuario.goHome}"/><br/><br/>
	<rich:dataTable id="usuarios" border="1" value="#{gerenciadorUsuario.usuarios}" var="c" binding="#{gerenciadorUsuario.objDataTable}" rendered="#{not empty gerenciadorUsuario.usuarios}" rows="10" width="100%">
		<rich:column sortBy="#{c.id}">
			<f:facet name="header"><h:outputText value="Id"/></f:facet>
			<h:outputText value="#{c.id}" />
		</rich:column>
		<rich:column sortBy="#{c.login}">
			<f:facet name="header"><h:outputText value="Login"/></f:facet>
			<h:outputText value="#{c.login}" />
		</rich:column>
		<rich:column sortBy="#{c.nome}">
			<f:facet name="header"><h:outputText value="Nome"/></f:facet>
			<h:outputText value="#{c.nome}" />
		</rich:column>
		<rich:column sortBy="#{c.perfilAcesso}">
			<f:facet name="header"><h:outputText value="Perfi de Acesso"/></f:facet>
			<h:outputText value="#{c.perfilAcesso}" />
		</rich:column>
		<rich:column sortBy="#{c.dataCadastro}">
			<f:facet name="header"><h:outputText value="Data do Cadastro"/></f:facet>
			<h:outputText value="#{c.dataCadastro}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>		
		<h:column>
			<f:facet name="header"><h:outputText value="Editar"  /></f:facet>
			<aj4:commandButton id="editarContratoButton" value="E" action="#{gerenciadorUsuario.editar}" reRender="edicaoForm"/>
			<rich:componentControl for="panel" attachTo="editarContratoButton" operation="show" event="onclick"   />
		</h:column>			
		<h:column>
			<f:facet name="header"><h:outputText value="Apagar"  /></f:facet>
			<aj4:commandButton id="apagaContratoButton" value="X" action="#{gerenciadorUsuario.excluir}" reRender="listaForm"/>
		</h:column>			
		<f:facet name="footer">
			<rich:datascroller for="usuarios"></rich:datascroller>
		</f:facet>		
	</rich:dataTable>
	
	<br/>
    <h:panelGrid columns="3" cellspacing="4" cellpadding="4">
	<aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorUsuario.pesquisar}" reRender="listaForm"/>
	<aj4:commandButton id="btnC" value="Cadastrar Novo Usuario" action="#{gerenciadorUsuario.prepararNovo}" reRender="edicaoForm"/>
	<rich:componentControl for="panel" attachTo="btnC" operation="show" event="onclick"   />    	
	</h:panelGrid>				
			
</rich:panel>
</aj4:form>


        
<rich:modalPanel id="panel" width="600" height="450" >
<f:facet name="header" >
<h:panelGroup  style="text-align: center">
   	<h:outputText value="#{gerenciadorUsuario.titulo}"/>
</h:panelGroup>
</f:facet> 
<aj4:form id="edicaoForm" ajaxSubmit="true"  >	

    <h:panelGrid columns="3" >
		<h:outputText value="Login: "  />
			<h:inputText id="login" value="#{gerenciadorUsuario.usuario.login}" size="10" required="true" requiredMessage="Digite o Login do Usuario"  >
			<rich:ajaxValidator event="onblur"/>
			</h:inputText>	
		<rich:message for="login" style="color: darkred" />	
		
		<h:outputText value="Nome: "  />
			<h:inputText id="nome" value="#{gerenciadorUsuario.usuario.nome}" size="40" required="true" requiredMessage="Digite o Nome do Usuario"  >
			<rich:ajaxValidator event="onblur"/>
			</h:inputText>	
		<rich:message for="nome" style="color: darkred" />	
				   	

    	<h:outputText value="Perfil de Acesso: " />
			<rich:comboBox defaultLabel="Selecione uma Opção" value="#{gerenciadorUsuario.usuario.perfilAcesso}">
				<f:selectItem itemValue="Gestor IMASF" />
            	<f:selectItem itemValue="Gestor Prestador"/>
            	<f:selectItem itemValue="Colaborador IMASF"/>
            	<f:selectItem itemValue="Colaborador Prestador"/>
            	<rich:ajaxValidator event="onblur"/>
    		</rich:comboBox>			
		<rich:message for="grupo" style="color: darkred" />			
		
		<h:outputText value="Senha: "  />
			<h:inputSecret id="senha" value="#{gerenciadorUsuario.usuario.senha}" size="10" required="true" requiredMessage="Digite a Senha do Usuario"  >
			<rich:ajaxValidator event="onblur"/>
			</h:inputSecret>	
		<rich:message for="senha" style="color: darkred" />	
		
		<h:outputText value="Data do Cadastro: "  />
		<rich:calendar id="dataCadastro" datePattern="dd/MM/yyyy" value="#{gerenciadorUsuario.usuario.dataCadastro}" required="true" requiredMessage="Digite a Data do Cadastro" >
			<rich:ajaxValidator event="onblur"/>
		</rich:calendar>				
	
			
	</h:panelGrid>
	
	
	<h:panelGrid columns="2"cellspacing="8" >
		<aj4:commandButton id="ConfirmarEdicao" value="Confirmar" action="#{gerenciadorUsuario.salvar}" reRender="edicaoForm, listaForm" />
		<aj4:commandButton id="CancelarEdicao" value="Cancelar" reRender="edicaoForm, listaForm"/>  
		<rich:componentControl for="panel" attachTo="CancelarEdicao" operation="hide" event="onclick" />  
	</h:panelGrid>
	  
</aj4:form>    
</rich:modalPanel>            
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>        
    </f:view>
    </body>
</html>
