
<%@ page import="ar.com.smtr.monitoreo.Monitor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitor.label', default: 'Monitor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-monitor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-monitor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="time" title="${message(code: 'monitor.time.label', default: 'Time')}" />
					
						<th><g:message code="monitor.ubicacion.label" default="Ubicacion" /></th>
					
						<g:sortableColumn property="velocidad" title="${message(code: 'monitor.velocidad.label', default: 'Velocidad')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${monitorInstanceList}" status="i" var="monitorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${monitorInstance.id}">${fieldValue(bean: monitorInstance, field: "time")}</g:link></td>
					
						<td>${fieldValue(bean: monitorInstance, field: "ubicacion")}</td>
					
						<td>${fieldValue(bean: monitorInstance, field: "velocidad")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${monitorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
