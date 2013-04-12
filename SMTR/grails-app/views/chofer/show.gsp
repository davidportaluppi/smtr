
<%@ page import="ar.com.smtr.monitoreo.Chofer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'chofer.label', default: 'Chofer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-chofer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-chofer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list chofer">
			
				<g:if test="${choferInstance?.appelido}">
				<li class="fieldcontain">
					<span id="appelido-label" class="property-label"><g:message code="chofer.appelido.label" default="Appelido" /></span>
					
						<span class="property-value" aria-labelledby="appelido-label"><g:fieldValue bean="${choferInstance}" field="appelido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.dni}">
				<li class="fieldcontain">
					<span id="dni-label" class="property-label"><g:message code="chofer.dni.label" default="Dni" /></span>
					
						<span class="property-value" aria-labelledby="dni-label"><g:fieldValue bean="${choferInstance}" field="dni"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.domicilio}">
				<li class="fieldcontain">
					<span id="domicilio-label" class="property-label"><g:message code="chofer.domicilio.label" default="Domicilio" /></span>
					
						<span class="property-value" aria-labelledby="domicilio-label"><g:fieldValue bean="${choferInstance}" field="domicilio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.fechaAlta}">
				<li class="fieldcontain">
					<span id="fechaAlta-label" class="property-label"><g:message code="chofer.fechaAlta.label" default="Fecha Alta" /></span>
					
						<span class="property-value" aria-labelledby="fechaAlta-label"><g:formatDate date="${choferInstance?.fechaAlta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.fechaNacimiento}">
				<li class="fieldcontain">
					<span id="fechaNacimiento-label" class="property-label"><g:message code="chofer.fechaNacimiento.label" default="Fecha Nacimiento" /></span>
					
						<span class="property-value" aria-labelledby="fechaNacimiento-label"><g:formatDate date="${choferInstance?.fechaNacimiento}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="chofer.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${choferInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="chofer.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${choferInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.viajesRealizados}">
				<li class="fieldcontain">
					<span id="viajesRealizados-label" class="property-label"><g:message code="chofer.viajesRealizados.label" default="Viajes Realizados" /></span>
					
						<g:each in="${choferInstance.viajesRealizados}" var="v">
						<span class="property-value" aria-labelledby="viajesRealizados-label"><g:link controller="viaje" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${choferInstance?.id}" />
					<g:link class="edit" action="edit" id="${choferInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
