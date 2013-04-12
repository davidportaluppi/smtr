
<%@ page import="ar.com.smtr.monitoreo.Rol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rol.label', default: 'Rol')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rol" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rol" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rol">
			
				<g:if test="${rolInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="rol.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${rolInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rolInstance?.dispositivosMoviles}">
				<li class="fieldcontain">
					<span id="dispositivosMoviles-label" class="property-label"><g:message code="rol.dispositivosMoviles.label" default="Dispositivos Moviles" /></span>
					
						<g:each in="${rolInstance.dispositivosMoviles}" var="d">
						<span class="property-value" aria-labelledby="dispositivosMoviles-label"><g:link controller="dispositivoMovil" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rolInstance?.identificador}">
				<li class="fieldcontain">
					<span id="identificador-label" class="property-label"><g:message code="rol.identificador.label" default="Identificador" /></span>
					
						<span class="property-value" aria-labelledby="identificador-label"><g:fieldValue bean="${rolInstance}" field="identificador"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rolInstance?.nombreRol}">
				<li class="fieldcontain">
					<span id="nombreRol-label" class="property-label"><g:message code="rol.nombreRol.label" default="Nombre Rol" /></span>
					
						<span class="property-value" aria-labelledby="nombreRol-label"><g:fieldValue bean="${rolInstance}" field="nombreRol"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rolInstance?.permisos}">
				<li class="fieldcontain">
					<span id="permisos-label" class="property-label"><g:message code="rol.permisos.label" default="Permisos" /></span>
					
						<g:each in="${rolInstance.permisos}" var="p">
						<span class="property-value" aria-labelledby="permisos-label"><g:link controller="permiso" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rolInstance?.vehiculos}">
				<li class="fieldcontain">
					<span id="vehiculos-label" class="property-label"><g:message code="rol.vehiculos.label" default="Vehiculos" /></span>
					
						<g:each in="${rolInstance.vehiculos}" var="v">
						<span class="property-value" aria-labelledby="vehiculos-label"><g:link controller="vehiculo" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rolInstance?.id}" />
					<g:link class="edit" action="edit" id="${rolInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
