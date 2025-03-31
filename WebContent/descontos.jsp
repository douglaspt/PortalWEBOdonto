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
		<h2 align="center"><h:outputText value="#{gerenciadorDescontos.titulo}" styleClass="title" /></h2>
	</f:facet>
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorDescontos.goHome}" /><br/><br/>
	</aj4:form>
	<aj4:form id="pesquisaForm" ajaxSubmit="true" >
	    <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
	      <h:outputText value="Mês/Ano : "  />
		  <h:inputText id="mesAno" value="#{gerenciadorDescontos.mesAno}" size="20" required="true" requiredMessage="Digite o Mês / Ano de Referência no Formato MM/AAAA Ex. 09/2009">
		    <rich:jQuery selector="#mesAno" query="mask('99/9999')" timing="onload"/>	
		  </h:inputText>
		  <h:message for="mesAno" style="color: darkred" />
		</h:panelGrid>
	    <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
		  <h:outputText value="Linha: "  />
		  <h:inputText id="linha" value="#{gerenciadorDescontos.linha}" size="20"></h:inputText>
		  <h:outputText value="ID Odontologico: "  />
		  <h:inputText id="idOdontologico" value="#{gerenciadorDescontos.idOdontologico}" size="20"></h:inputText>
		  <h:outputText value="Empresa  :  "  />
		  <h:inputText id="empresa" value="#{gerenciadorDescontos.idEmpresa}" size="20"></h:inputText>
		  <h:outputText value="Matricula: "  />
		  <h:inputText id="matricula" value="#{gerenciadorDescontos.matricula}" size="20"></h:inputText>
		  <h:outputText value="Nome "  />
		  <h:inputText id="nome" value="#{gerenciadorDescontos.nome}" size="50"></h:inputText> 
		  <br/>
	    </h:panelGrid>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorDescontos.pesquisaDescontoPorParametro}" reRender="listaForm, pesquisaForm" />
	</aj4:form>
	<br/>
	
<aj4:form id="listaForm" >
<rich:panel id="listagem" rendered="#{not empty gerenciadorDescontos.descontos}">
	<f:facet name="header" >
		<h:outputText value="#{gerenciadorDescontos.titulo}" style="text-align: center" />
	</f:facet>
	<rich:dataTable id="descontos" border="1" value="#{gerenciadorDescontos.descontos}" var="d" binding="#{gerenciadorDescontos.objDataTable}" rendered="#{not empty gerenciadorDescontos.descontos}" rows="10" width="100%">
		<rich:column sortBy="#{d.linha}">
			<f:facet name="header"><h:outputText value="Linha"/></f:facet>
			<h:outputText value="#{d.linha}" />
		</rich:column>
		<rich:column sortBy="#{d.referencia}">
			<f:facet name="header"><h:outputText value="Data"/></f:facet>
			<h:outputText value="#{d.referencia}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.idEmpresa}">
			<f:facet name="header"><h:outputText value="Emp."/></f:facet>
			<h:outputText value="#{d.idEmpresa}" />
		</rich:column>		
		<rich:column sortBy="#{d.matricula}">
			<f:facet name="header"><h:outputText value="Matricula"/></f:facet>
			<h:outputText value="#{d.matricula}" />
		</rich:column>
		<rich:column sortBy="#{d.idOdontologico}">
			<f:facet name="header"><h:outputText value="ID Odonto"/></f:facet>
			<h:outputText value="#{d.idOdontologico}" />
		</rich:column>	
		<rich:column sortBy="#{d.nome}">
			<f:facet name="header"><h:outputText value="Nome"/></f:facet>
			<h:outputText value="#{d.nome}" />
		</rich:column>
		<rich:column sortBy="#{d.nascimento}">
			<f:facet name="header"><h:outputText value="Nascimento"/></f:facet>
			<h:outputText value="#{d.nascimento}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.adesao}">
			<f:facet name="header"><h:outputText value="Adesão"/></f:facet>
			<h:outputText value="#{d.adesao}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.idTipoCobertura}">
			<f:facet name="header"><h:outputText value="Cobertura"/></f:facet>
			<h:outputText value="#{d.idTipoCobertura}" />
		</rich:column>
		<rich:column sortBy="#{d.idTipoDependente}">
			<f:facet name="header"><h:outputText value="Tit."/></f:facet>
			<h:outputText value="#{d.idTipoDependente}" />
		</rich:column>
		<rich:column sortBy="#{d.vlInformado}">
			<f:facet name="header"><h:outputText value="Vl. Informado"/></f:facet>
			<h:outputText value="#{d.vlInformado}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{d.vlPagar}">
			<f:facet name="header"><h:outputText value="Vl. Pagar"/></f:facet>
			<h:outputText value="#{d.vlPagar}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>	
		<rich:column sortBy="#{d.status.descricao}">
			<f:facet name="header"><h:outputText value="Status"/></f:facet>
			<h:outputText value="#{d.status.descricao}" ></h:outputText>
		</rich:column>	
		<rich:column>
			<f:facet name="header"><h:outputText value="Critica"/></f:facet>
			<aj4:commandButton id="alteraButton" value="A" action="#{gerenciadorDescontos.editar}" reRender="edicaocriticaForm" rendered="#{d.status.id == 1}"/>	
            <rich:componentControl for="panel" attachTo="alteraButton" operation="show" event="onclick"/>
            <aj4:commandButton id="cancelarButton" value="C" action="#{gerenciadorDescontos.editar}" reRender="cancelacriticaForm" rendered="#{d.status.id == 2}"/>
            <rich:componentControl for="panel2" attachTo="cancelarButton" operation="show" event="onclick"/>
		</rich:column>
		<f:facet name="footer">
			<rich:datascroller for="descontos"></rich:datascroller>
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
        
	 <h:commandLink id="linkPDF" rendered="#{not empty gerenciadorDescontos.descontos}">  
	 	 <p:graphicImage value="images/pdf.png" style="border:none"/>  
    	 <p:dataExporter type="pdf" target="descontos" fileName="descontos"/> 
	 </h:commandLink> 
	 <h:commandLink  id="linkExcel" rendered="#{not empty gerenciadorDescontos.descontos}">  
	 	 <p:graphicImage value="images/excel.png"  style="border:none"/>  
    	 <p:dataExporter type="xls" target="descontos" fileName="descontos"/> 
	 </h:commandLink>	
	 <h:commandLink  id="linkCVS" rendered="#{not empty gerenciadorDescontos.descontos}">  
	 	 <p:graphicImage value="images/csv.png"  style="border:none"/>  
    	 <p:dataExporter type="csv" target="descontos" fileName="descontos"/> 
	 </h:commandLink>		
    
</rich:panel>
</aj4:form>	
	

<rich:modalPanel id="panel" width="350" height="200">
  <f:facet name="header" >
    <h:panelGroup  style="text-align: center">
   	  <h:outputText value="Critica Manual"></h:outputText>
    </h:panelGroup>
  </f:facet> 
  <aj4:form id="edicaocriticaForm" ajaxSubmit="true" >
    <h:panelGrid columns="2" >
    	<h:outputText value="Matricula: "  />
		<h:inputText id="matricula" value="#{gerenciadorDescontos.desconto.matricula}" size="15" disabled="true"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>
		<h:outputText value="Nome: "  />
		<h:inputText id="nome" value="#{gerenciadorDescontos.desconto.nome}" size="45" disabled="true"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>	    
		<h:outputText value="Descricao: "  />
		<h:inputText id="descricao" value="#{gerenciadorDescontos.descricaoCritica}" size="45" required="true" requiredMessage="Digite a Descrição"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>
		</br>
		<rich:message for="descricao" style="color: darkred" />
		<h:outputText value="Observação " />
		<h:inputText id="obs" value="#{gerenciadorDescontos.obsCritica}" size="45" >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>		
	</h:panelGrid>
    <br></br>
	<h:panelGrid columns="2"cellspacing="8" >
		<aj4:commandButton id="ConfirmarEdicao" value="Confirmar" action="#{gerenciadorDescontos.salvaCritica}" reRender="edicaocriticaForm, listaForm"/>
		<aj4:commandButton id="CancelarEdicao" value="Cancelar" action="#{gerenciadorDescontos.limpar}" reRender="edicaocriticaForm"/>  
		<rich:componentControl for="panel" attachTo="CancelarEdicao" operation="hide" event="onclick"/>  
	</h:panelGrid>
</aj4:form>
</rich:modalPanel> 	  
    


<rich:modalPanel id="panel2" width="230" height="120">
  <f:facet name="header" >
    <h:panelGroup  style="text-align: center">	
   	  <h:outputText value="Pagamento Incondicional"></h:outputText>
    </h:panelGroup>
  </f:facet> 
  <aj4:form id="cancelacriticaForm" ajaxSubmit="true" >	
  <h:outputText value="Confirma Pagamento Incondicional para"/>
  </br>
  <h:outputText value="#{gerenciadorDescontos.desconto.nome}"/>
  <br></br>
	<h:panelGrid columns="2"cellspacing="8" >
		<aj4:commandButton id="sim" value="Sim" action="#{gerenciadorDescontos.cancelaCritica}" reRender="cancelacriticaForm, listaForm"/>
		<aj4:commandButton id="nao" value="Não" reRender="cancelacriticaForm"/>
		<rich:componentControl for="pane2" attachTo="sim" operation="hide" event="onclick"   />  
		<rich:componentControl for="pane2" attachTo="nao" operation="hide" event="onclick" />  
	</h:panelGrid>  
  </aj4:form>    
</rich:modalPanel>

</rich:panel>             
    
    <rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>
        
    </f:view>
    </body>
</html>
