
<%@ page import="ar.com.smtr.monitoreo.Ubicacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ubicacion.label', default: 'Ubicacion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ubicacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ubicacion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ubicacion">
			
				<g:if test="${ubicacionInstance?.altitud}">
				<li class="fieldcontain">
					<span id="altitud-label" class="property-label"><g:message code="ubicacion.altitud.label" default="Altitud" /></span>
					
						<span class="property-value" aria-labelledby="altitud-label"><g:fieldValue bean="${ubicacionInstance}" field="altitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ubicacionInstance?.latitud}">
				<li class="fieldcontain">
					<span id="latitud-label" class="property-label"><g:message code="ubicacion.latitud.label" default="Latitud" /></span>
					
						<span class="property-value" aria-labelledby="latitud-label"><g:fieldValue bean="${ubicacionInstance}" field="latitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ubicacionInstance?.longitud}">
				<li class="fieldcontain">
					<span id="longitud-label" class="property-label"><g:message code="ubicacion.longitud.label" default="Longitud" /></span>
					
						<span class="property-value" aria-labelledby="longitud-label"><g:fieldValue bean="${ubicacionInstance}" field="longitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ubicacionInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="ubicacion.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${ubicacionInstance}" field="time"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ubicacionInstance?.id}" />
					<g:link class="edit" action="edit" id="${ubicacionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
