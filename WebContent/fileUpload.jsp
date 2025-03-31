<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bem Vindo ao Sistema Ambulatorial - Menu Principal</title>
</head>
<body > 
<f:view>


<rich:panel>
	<f:facet name="header">
		<h2 align="center"><h:outputText value="Processamento de Descontos Odontologicos - Upload do Arquivo" styleClass="title" /></h2>
	</f:facet>	
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorResumo.goHome}" /><br/><br/>
	</aj4:form>
	
    <style>
.top {
    vertical-align: top;
    
}
.info {
    height: 202px;
    overflow: auto;
}
</style>

    <h:form id="uploadForm">
        <h:panelGrid columns="2" columnClasses="top,top">
            <rich:fileUpload fileUploadListener="#{fileUploadBean.listener}" maxFilesQuantity="#{fileUploadBean.uploadsAvailable}" id="upload" 
            immediateUpload="#{fileUploadBean.autoUpload}" acceptedTypes="txt, csv" allowFlash="#{fileUploadBean.useFlash}" >
                <aj4:support event="onuploadcomplete" reRender="info, erros" />
                <aj4:support event="onerror" reRender="erros" action="#{fileUploadBean.clearUploadData}"/>
                <aj4:support event="onupload" reRender="erros" action="#{fileUploadBean.doUpload}"/>
                <aj4:support event="onuploadcanceled" reRender="erros" action="#{fileUploadBean.doCancel}"/>
                <aj4:support event="onclear" reRender="info, erros" action="#{fileUploadBean.clearUploadData}"/>
            </rich:fileUpload>
            <h:panelGroup id="info">
                <rich:panel bodyClass="info">
                    <f:facet name="header">
                        <h:outputText value="Arquivos Carregados" />
                    </f:facet>
                    <h:outputText value="Nenhum arquivo carregado no momento" rendered="#{fileUploadBean.size==0}" />
                    <rich:dataGrid columns="1" value="#{fileUploadBean.files}" var="file" rowKeyVar="row">
                        <rich:panel bodyClass="rich-laguna-panel-no-header">
                            <h:panelGrid columns="2">
                                <h:panelGrid columns="2">
                                    <h:outputText value="#{file.fileName}" />
                                </h:panelGrid>
                            </h:panelGrid>
                        </rich:panel>
                    </rich:dataGrid>
                </rich:panel>
                <rich:spacer height="3"/>
                <br />

            </h:panelGroup>
        </h:panelGrid>

	<h:panelGroup id="erros">
		<h:outputLabel value="Erros:"/>
		<h:outputLabel value="#{fileUploadBean.erro}" style="color: darkred" />
		<br/>
		<h:outputLabel value="Resultado:"/>
		<h:outputLabel value="#{fileUploadBean.resultado}" style="color: darkblue" />
	</h:panelGroup>

	<aj4:commandButton id="limpar" value="Limpar Carga" action="#{fileUploadBean.clearUploadData}" reRender="uploadForm" rendered="false"/>	

    </h:form>	

</rich:panel>
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>
</f:view>
</body>
</html>