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
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorDescontos.goHome}" /><br/><br/>
	</aj4:form>	
	<aj4:form id="pesquisaForm" ajaxSubmit="true">
		<h:message for="mesAno" style="color: darkred" />
        <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
	      <h:outputText value="Mês/Ano : "  />
		  <h:inputText id="mesAno" value="#{gerenciadorCriticas.mesAno}" size="20" required="true" requiredMessage="É Obrigatório o preenchimento do Mês / Ano de Referência no Formato MM/AAAA Ex. 09/2009">
		    <rich:jQuery selector="#mesAno" query="mask('99/9999')" timing="onload"/>	
		  </h:inputText>
		  <h:outputText value="Ajuda?" id="ajuda"  />
			<rich:toolTip id="textoAjuda" followMouse="true" direction="top-right" showDelay="500" for="ajuda" style="background-color:white" >
			<rich:effect event="onmouseout" type="Opacity" params="duration:0.8,from:1.0,to:0.3" />
			<p><strong style="color: red">Campos Obrigatórios para Pesquisa.</strong></p>
			<strong style="color: darkred">Mês de Referência.</strong><br/>
			 <span  style="white-space:nowrap">Para realizar a pesquisa é obrigatório o preenchimento do <strong>Mês de Referência.</strong></span><br/>
			 O Mês de referencia trata-se do mês e ano em que o desconto foi enviado a Folha de Pagamento, ou seja, o mês que o desconto deve ser pago.<br/>
			 Esta informação deve ser digitada no formato MM/AAAA. Ex. 05/2009 (Para pesquisar os descontos da folha mês de maio de 2009)<br/>
			<br/>
			<p style="color: red"><strong>Campos Opcionais para Pesquisa.</strong></p>
			<strong style="color: darkred">Linha</strong><br/>
			Número da linha onde esta informação está no arquivo que foi carregado.<br/>
			Útil caso queira pesquisar um registro que está na linha X do arquivo excel.<br/><br/>
			<strong style="color: darkred">ID Odontologico</strong><br/>
			Número da Carteirinha do Plano Odontológico.<br/>
			Pode-se pesquisar digitando parte do número da carteirinha.<br/><br/>
			<strong style="color: darkred">Empresa e Matrícula</strong><br/>
			Número da Empresa e Matrícula do Funcionário na Prefeitura/Autarquia/Empresa que tem convênio odontológico<br/>
			Principais empresas: 5 - Prefeitura, 16 - IMASF, 15 - Faculdade<br/><br/>
			<strong style="color: darkred">Data de Adesão</strong><br/>
			Data de Adesão ao Plano Odontológico<br/>
			Preencha uma data inicial e uma data final. Nunca apenas uma data.<br/><br/>
			<strong style="color: darkred">Crítica</strong><br/>
			Selecione um tipo de Crítica clicando no Campo.<br/>
			As Críticas são informações de inconsistências que o sistema gerou após a carga do arquivo de Descontos.<br/><br/>			
      		<strong style="color: darkred">Nome</strong><br/>
			Preencha o nome ou parte do nome ou sobrenome para localizar os descontos.<br/>			
      		</rich:toolTip>		  
		  <h:outputText value="Linha: "  />
		  <h:inputText id="linha" value="#{gerenciadorCriticas.linha}" size="20"> 
		  </h:inputText>
		  <h:outputText value="ID Odontologico: "  />
		  <h:inputText id="idOdontologico" value="#{gerenciadorCriticas.idOdontologico}" size="20"></h:inputText>
		  <h:outputText value="Empresa  :  "  />
		  <h:inputText id="empresa" value="#{gerenciadorCriticas.idEmpresa}" size="20"></h:inputText>
		  <h:outputText value="Matricula: "  />
		  <h:inputText id="matricula" value="#{gerenciadorCriticas.matricula}" size="20"></h:inputText>
		  
		  <h:outputText value="Adesao de :  "  />     	
		  <rich:calendar id="adesaoInicial" datePattern="dd/MM/yyyy" value="#{gerenciadorCriticas.adesaoInicial}"> 
			<rich:ajaxValidator event="onblur"/>
		  </rich:calendar>

		  <h:outputText value=" até :  "  />
		  		  <rich:calendar id="adesaoFinal" datePattern="dd/MM/yyyy" value="#{gerenciadorCriticas.adesaoFinal}"> 
			<rich:ajaxValidator event="onblur"/>
		  </rich:calendar>
		  <h:outputText value="Critica: "  />
          <h:selectOneMenu id="classificacao" value="#{gerenciadorCriticas.classificacaoCritica.id}" >
		  	<f:selectItems  value="#{gerenciadorCriticas.classificacoes}" />
    	  </h:selectOneMenu>
    	  <rich:spacer width="60%" height="20px"/>
    	  <br/>
		  <h:outputText value="Nome "  />
		  <h:inputText id="nome" value="#{gerenciadorCriticas.nome}" size="50"></h:inputText>
		  <br/>
	    </h:panelGrid>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorCriticas.pesquisaPorParametro}" reRender="listaForm, pesquisaForm, controler, criticas"/>
	</aj4:form>
	<br/>
	
<aj4:form id="listaForm" ajaxSubmit="true">
<rich:panel id="listagem" rendered="#{not empty gerenciadorCriticas.criticas}">
	<f:facet name="header" >
		<h:outputText value="#{gerenciadorCriticas.titulo}" style="text-align: center" />
	</f:facet>
	<rich:dataTable id="criticas" border="1" value="#{gerenciadorCriticas.criticas}" var="c" binding="#{gerenciadorCriticas.objDataTable}" rendered="#{not empty gerenciadorCriticas.criticas}" rows="10" width="100%">
		<rich:column sortBy="#{c.desconto.linha}">
			<f:facet name="header"><h:outputText value="Linha"/></f:facet>
			<h:outputText value="#{c.desconto.linha}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.referencia}">
			<f:facet name="header"><h:outputText value="Referência"/></f:facet>
			<h:outputText value="#{c.desconto.referencia}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.desconto.origem}">
			<f:facet name="header"><h:outputText value="Origem"/></f:facet>
			<h:outputText value="#{c.desconto.origem}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>		
		<rich:column sortBy="#{c.desconto.idEmpresa}">
			<f:facet name="header"><h:outputText value="Emp."/></f:facet>
			<h:outputText value="#{c.desconto.idEmpresa}" />
		</rich:column>		
		<rich:column sortBy="#{c.desconto.matricula}">
			<f:facet name="header"><h:outputText value="Matricula"/></f:facet>
			<h:outputText value="#{c.desconto.matricula}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.idOdontologico}">
			<f:facet name="header"><h:outputText value="ID Odonto"/></f:facet>
			<h:outputText value="#{c.desconto.idOdontologico}" />
		</rich:column>	
		<rich:column sortBy="#{c.desconto.nome}">
			<f:facet name="header"><h:outputText value="Nome"/></f:facet>
			<h:outputText value="#{c.desconto.nome}" rendered="#{c.classificacaoCritica.id >= 900 && c.classificacaoCritica.id <= 999}"/>
			<h:outputText value="#{c.desconto.nome}"  rendered="#{not (c.classificacaoCritica.id >= 900 && c.classificacaoCritica.id <= 999)}" style="color: darkred"/>

		</rich:column>
		<rich:column sortBy="#{c.desconto.nascimento}">
			<f:facet name="header"><h:outputText value="Nascimento"/></f:facet>
			<h:outputText value="#{c.desconto.nascimento}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.desconto.adesao}">
			<f:facet name="header"><h:outputText value="Adesão"/></f:facet>
			<h:outputText value="#{c.desconto.adesao}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.desconto.idTipoCobertura}">
			<f:facet name="header"><h:outputText value="Cobertura"/></f:facet>
			<h:outputText value="#{c.desconto.idTipoCobertura}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.idTipoDependente}">
			<f:facet name="header"><h:outputText value="Tit."/></f:facet>
			<h:outputText value="#{c.desconto.idTipoDependente}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.vlInformado}">
			<f:facet name="header"><h:outputText value="Informado"/></f:facet>
			<h:outputText value="#{c.desconto.vlInformado}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>		
		<rich:column sortBy="#{c.desconto.vlPagar}">
			<f:facet name="header"><h:outputText value="Pagar"/></f:facet>
			<h:outputText value="#{c.desconto.vlPagar}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.vlPago}">
			<f:facet name="header"><h:outputText value="Pago"/></f:facet>
			<h:outputText value="#{c.vlPago}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>	
		<rich:column sortBy="#{c.descricaoCritica}">
			<f:facet name="header"><h:outputText value="Critica"/></f:facet>
			<h:outputText value="#{c.descricaoCritica}" />
		</rich:column>
		<rich:column sortBy="#{c.obsCritica}">
			<f:facet name="header"><h:outputText value="Observações"/></f:facet>
			<h:outputText value="#{c.obsCritica}" />
		</rich:column>	
		<rich:column sortBy="#{c.desconto.cpf}">
			<f:facet name="header"><h:outputText value="CPF"/></f:facet>
			<h:outputText value="#{c.desconto.cpf}" />
		</rich:column>	
		<rich:column sortBy="#{c.desconto.nomeMae}">
			<f:facet name="header"><h:outputText value="Nome da Mãe"/></f:facet>
			<h:outputText value="#{c.desconto.nomeMae}" />
		</rich:column>			
		<rich:column>
			<f:facet name="header"><h:outputText value="Funções"/></f:facet>
			<aj4:commandLink id="filtro" value="Filtrar" action="#{gerenciadorCriticas.filtrarPorMatriculaSelecionada}" reRender="listaForm" rendered="#{gerenciadorCriticas.extratoMatricula==''}"/>			
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="filtro">
            	<span  style="white-space:nowrap">Clique para <strong>Filtrar os Dados desta Matrícula</strong>. Serão mostrados todos os descontos desta Matrícula de todos os meses</span>
      		</rich:toolTip>
			<aj4:commandLink id="extrato" value="Extrato" action="#{gerenciadorCriticas.gerarExtrato}" reRender="listaForm"  rendered="#{gerenciadorCriticas.extratoMatricula!=''}"/>
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="extrato">
            	<span  style="white-space:nowrap">Clique para <strong>Emissão do Extrato</strong> desta Matrícula.</span>
      		</rich:toolTip>
			
			<aj4:commandButton id="criticaManualButton" value="Criticar" action="#{gerenciadorCriticas.editar}" reRender="edicaocriticaForm" rendered="#{c.classificacaoCritica.id >= 900 && c.classificacaoCritica.id <= 999}" disabled="true"/>	
            <rich:componentControl for="panelCriticaManual" attachTo="criticaManualButton" operation="show" event="onclick"/>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="criticaManualButton">
            	<span  style="white-space:nowrap">Clique para efetuar uma critica Manualmente</span>
            </rich:toolTip>
            <aj4:commandButton id="cancelaCriticaManualButton" value="CM" action="#{gerenciadorCriticas.editar}" reRender="cancelacriticaForm" rendered="false"/>
            <rich:componentControl for="panel2" attachTo="cancelaCriticaManualButton" operation="show" event="onclick"/>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="cancelaCriticaManualButton">
            	<span  style="white-space:nowrap">Clique para cancelar a Critica Manual</span>
            </rich:toolTip>
            <aj4:commandButton id="pagarButton" value="Pagar" action="#{gerenciadorCriticas.editar}" reRender="edicaoPagamentoForm" rendered="#{c.classificacaoCritica.id < 900}" disabled="true"/>	
            <rich:componentControl for="panelPagamentoIncondicional" attachTo="pagarButton" operation="show" event="onclick"/>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="pagarButton">
            	<span  style="white-space:nowrap">Clique para Pagar Incondicional</span>
            </rich:toolTip>
            <aj4:commandButton id="cancelaPagarButton" value="CP" action="#{gerenciadorCriticas.editar}" reRender="cancelaPagamentoForm" rendered="false" />
            <rich:componentControl for="panel3" attachTo="cancelaPagarButton" operation="show" event="onclick"/>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="cancelaPagarButton">
            	<span  style="white-space:nowrap">Clique para Cancelar Pagamento Incondicional</span>
            </rich:toolTip>
		</rich:column>
		<f:facet name="footer">
			<rich:datascroller id="controler" for="criticas"></rich:datascroller>
		</f:facet>		
	</rich:dataTable>
	<br/>
	

    <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkExcel">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong> Excel</strong>.	</span>
    </rich:toolTip>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkCVS">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong>CVS</strong> (Arquivo TXT).	</span>
    </rich:toolTip>
        
	 <h:commandLink  id="linkExcel" rendered="#{not empty gerenciadorCriticas.criticas}">  
	 	 <p:graphicImage value="images/excel.png"  style="border:none"/>  
    	 <p:dataExporter type="xls" target="criticas" fileName="criticas"/> 
	 </h:commandLink>	
	 <h:commandLink  id="linkCVS" rendered="#{not empty gerenciadorCriticas.criticas}">  
	 	 <p:graphicImage value="images/csv.png"  style="border:none"/>  
    	 <p:dataExporter type="csv" target="criticas" fileName="criticas"/> 
	 </h:commandLink>	
	 
</rich:panel>
</aj4:form>		

<rich:modalPanel id="panelCriticaManual" width="480" height="240">
  <f:facet name="header" >
    <h:panelGroup  style="text-align: center">
   	  <h:outputText value="Critica Manual"></h:outputText>
    </h:panelGroup>
  </f:facet> 
  <aj4:form id="edicaocriticaForm" ajaxSubmit="true" >
    <h:panelGrid columns="2" >
    	<h:outputText value="Matricula: "  />
		<h:inputText value="#{gerenciadorCriticas.critica.desconto.matricula}" size="15" disabled="true"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>
		<h:outputText value="Nome: "  />
		<h:inputText value="#{gerenciadorCriticas.critica.desconto.nome}" size="60" disabled="true"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>	
		<h:outputText value="Valor Informado: "  />
		<h:inputText  value="#{gerenciadorCriticas.critica.desconto.vlInformado}" size="10" disabled="true">
			<f:convertNumber pattern="###,###,##0.00"/>
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>		
		<h:outputText value="Observação " />
		<h:inputText value="#{gerenciadorCriticas.critica.obsCritica}" size="80" >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>		
	</h:panelGrid>
    <br></br>
	<h:panelGrid columns="2"cellspacing="8" >
		<aj4:commandButton value="Confirmar" action="#{gerenciadorCriticas.salvarCritica}" reRender="listaForm"/>
		<aj4:commandButton value="Cancelar" action="#{gerenciadorCriticas.limpar}" reRender="listaForm" immediate="true"/>  
	</h:panelGrid>
</aj4:form>
</rich:modalPanel> 	


<rich:modalPanel id="panelPagamentoIncondicional" width="400" height="240">
  <f:facet name="header" >
    <h:panelGroup  style="text-align: center">
   	  <h:outputText value="Pagamento Incondicional"></h:outputText>
    </h:panelGroup>
  </f:facet> 
  <aj4:form id="edicaoPagamentoForm" ajaxSubmit="true" >
    <h:panelGrid columns="2" >
    	<h:outputText value="Matricula: "  />
		<h:inputText  value="#{gerenciadorCriticas.critica.desconto.matricula}" size="15" disabled="true"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>
		<h:outputText value="Nome: "  />
		<h:inputText  value="#{gerenciadorCriticas.critica.desconto.nome}" size="45" disabled="true"  >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>	    
		<h:outputText value="Observação " />
		<h:inputText  value="#{gerenciadorCriticas.critica.obsCritica}" size="45" >
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>
		<h:outputText value="Valor Informado: "  />
		<h:inputText  value="#{gerenciadorCriticas.critica.desconto.vlInformado}" size="10" disabled="true">
			<f:convertNumber pattern="###,###,##0.00"/>
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>			
		<h:outputText value="Valor a Pagar: "  />
		<h:inputText  value="#{gerenciadorCriticas.critica.vlPagar}" size="10" >
			<f:convertNumber pattern="###,###,##0.00"/>
			<rich:ajaxValidator event="onblur"/>
		</h:inputText>	
	</h:panelGrid>
    <br></br>
	<h:panelGrid columns="2"cellspacing="8" >
		<aj4:commandButton value="Confirmar" action="#{gerenciadorCriticas.salvarPagamentoIncondicional}" reRender="listaForm"/>
		<aj4:commandButton value="Cancelar" action="#{gerenciadorCriticas.cancelar}" reRender="listaForm" immediate="true"/>  
	</h:panelGrid>
</aj4:form>
</rich:modalPanel> 	

</rich:panel>             
        
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>        
        
    </f:view>
    </body>
</html>
