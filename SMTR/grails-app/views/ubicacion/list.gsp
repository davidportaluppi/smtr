
<%@ page import="ar.com.smtr.monitoreo.Ubicacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ubicacion.label', default: 'Ubicacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ubicacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ubicacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="altitud" title="${message(code: 'ubicacion.altitud.label', default: 'Altitud')}" />
					
						<g:sortableColumn property="latitud" title="${message(code: 'ubicacion.latitud.label', default: 'Latitud')}" />
					
						<g:sortableColumn property="longitud" title="${message(code: 'ubicacion.longitud.label', default: 'Longitud')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'ubicacion.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ubicacionInstanceList}" status="i" var="ubicacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ubicacionInstance.id}">${fieldValue(bean: ubicacionInstance, field: "altitud")}</g:link></td>
					
						<td>${fieldValue(bean: ubicacionInstance, field: "latitud")}</td>
					
						<td>${fieldValue(bean: ubicacionInstance, field: "longitud")}</td>
					
						<td>${fieldValue(bean: ubicacionInstance, field: "time")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ubicacionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
