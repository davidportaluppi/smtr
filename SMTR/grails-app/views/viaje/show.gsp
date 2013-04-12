
<%@ page import="ar.com.smtr.monitoreo.Viaje" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'viaje.label', default: 'Viaje')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-viaje" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-viaje" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list viaje">
			
				<g:if test="${viajeInstance?.chofer}">
				<li class="fieldcontain">
					<span id="chofer-label" class="property-label"><g:message code="viaje.chofer.label" default="Chofer" /></span>
					
						<span class="property-value" aria-labelledby="chofer-label"><g:link controller="chofer" action="show" id="${viajeInstance?.chofer?.id}">${viajeInstance?.chofer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${viajeInstance?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="viaje.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${viajeInstance}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${viajeInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="viaje.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${viajeInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${viajeInstance?.fechaHoraSalida}">
				<li class="fieldcontain">
					<span id="fechaHoraSalida-label" class="property-label"><g:message code="viaje.fechaHoraSalida.label" default="Fecha Hora Salida" /></span>
					
						<span class="property-value" aria-labelledby="fechaHoraSalida-label"><g:formatDate date="${viajeInstance?.fechaHoraSalida}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${viajeInstance?.origen}">
				<li class="fieldcontain">
					<span id="origen-label" class="property-label"><g:message code="viaje.origen.label" default="Origen" /></span>
					
						<span class="property-value" aria-labelledby="origen-label"><g:fieldValue bean="${viajeInstance}" field="origen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${viajeInstance?.vehiculo}">
				<li class="fieldcontain">
					<span id="vehiculo-label" class="property-label"><g:message code="viaje.vehiculo.label" default="Vehiculo" /></span>
					
						<span class="property-value" aria-labelledby="vehiculo-label"><g:link controller="vehiculo" action="show" id="${viajeInstance?.vehiculo?.id}">${viajeInstance?.vehiculo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${viajeInstance?.id}" />
					<g:link class="edit" action="edit" id="${viajeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
