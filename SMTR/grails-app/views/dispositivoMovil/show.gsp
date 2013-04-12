
<%@ page import="ar.com.smtr.monitoreo.DispositivoMovil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dispositivoMovil.label', default: 'DispositivoMovil')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dispositivoMovil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dispositivoMovil" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dispositivoMovil">
			
				<g:if test="${dispositivoMovilInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="dispositivoMovil.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${dispositivoMovilInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${dispositivoMovilInstance?.imei}">
				<li class="fieldcontain">
					<span id="imei-label" class="property-label"><g:message code="dispositivoMovil.imei.label" default="Imei" /></span>
					
						<span class="property-value" aria-labelledby="imei-label"><g:fieldValue bean="${dispositivoMovilInstance}" field="imei"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dispositivoMovilInstance?.marca}">
				<li class="fieldcontain">
					<span id="marca-label" class="property-label"><g:message code="dispositivoMovil.marca.label" default="Marca" /></span>
					
						<span class="property-value" aria-labelledby="marca-label"><g:fieldValue bean="${dispositivoMovilInstance}" field="marca"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dispositivoMovilInstance?.model}">
				<li class="fieldcontain">
					<span id="model-label" class="property-label"><g:message code="dispositivoMovil.model.label" default="Model" /></span>
					
						<span class="property-value" aria-labelledby="model-label"><g:fieldValue bean="${dispositivoMovilInstance}" field="model"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dispositivoMovilInstance?.proveedor}">
				<li class="fieldcontain">
					<span id="proveedor-label" class="property-label"><g:message code="dispositivoMovil.proveedor.label" default="Proveedor" /></span>
					
						<span class="property-value" aria-labelledby="proveedor-label"><g:fieldValue bean="${dispositivoMovilInstance}" field="proveedor"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dispositivoMovilInstance?.id}" />
					<g:link class="edit" action="edit" id="${dispositivoMovilInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
