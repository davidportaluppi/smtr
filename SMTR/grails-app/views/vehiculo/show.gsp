
<%@ page import="ar.com.smtr.monitoreo.Vehiculo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vehiculo.label', default: 'Vehiculo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-vehiculo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-vehiculo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list vehiculo">
			
				<g:if test="${vehiculoInstance?.dispositivoMovil}">
				<li class="fieldcontain">
					<span id="dispositivoMovil-label" class="property-label"><g:message code="vehiculo.dispositivoMovil.label" default="Dispositivo Movil" /></span>
					
						<span class="property-value" aria-labelledby="dispositivoMovil-label"><g:link controller="dispositivoMovil" action="show" id="${vehiculoInstance?.dispositivoMovil?.id}">${vehiculoInstance?.dispositivoMovil?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculoInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="vehiculo.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${vehiculoInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculoInstance?.historialMonitoreo}">
				<li class="fieldcontain">
					<span id="historialMonitoreo-label" class="property-label"><g:message code="vehiculo.historialMonitoreo.label" default="Historial Monitoreo" /></span>
					
						<g:each in="${vehiculoInstance.historialMonitoreo}" var="h">
						<span class="property-value" aria-labelledby="historialMonitoreo-label"><g:link controller="monitor" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculoInstance?.marca}">
				<li class="fieldcontain">
					<span id="marca-label" class="property-label"><g:message code="vehiculo.marca.label" default="Marca" /></span>
					
						<span class="property-value" aria-labelledby="marca-label"><g:fieldValue bean="${vehiculoInstance}" field="marca"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculoInstance?.modelo}">
				<li class="fieldcontain">
					<span id="modelo-label" class="property-label"><g:message code="vehiculo.modelo.label" default="Modelo" /></span>
					
						<span class="property-value" aria-labelledby="modelo-label"><g:fieldValue bean="${vehiculoInstance}" field="modelo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculoInstance?.patente}">
				<li class="fieldcontain">
					<span id="patente-label" class="property-label"><g:message code="vehiculo.patente.label" default="Patente" /></span>
					
						<span class="property-value" aria-labelledby="patente-label"><g:fieldValue bean="${vehiculoInstance}" field="patente"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${vehiculoInstance?.id}" />
					<g:link class="edit" action="edit" id="${vehiculoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
