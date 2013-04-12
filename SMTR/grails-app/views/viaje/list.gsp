
<%@ page import="ar.com.smtr.monitoreo.Viaje" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'viaje.label', default: 'Viaje')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-viaje" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-viaje" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="viaje.chofer.label" default="Chofer" /></th>
					
						<g:sortableColumn property="destino" title="${message(code: 'viaje.destino.label', default: 'Destino')}" />
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'viaje.fechaAlta.label', default: 'Fecha Alta')}" />
					
						<g:sortableColumn property="fechaHoraSalida" title="${message(code: 'viaje.fechaHoraSalida.label', default: 'Fecha Hora Salida')}" />
					
						<g:sortableColumn property="origen" title="${message(code: 'viaje.origen.label', default: 'Origen')}" />
					
						<th><g:message code="viaje.vehiculo.label" default="Vehiculo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${viajeInstanceList}" status="i" var="viajeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${viajeInstance.id}">${fieldValue(bean: viajeInstance, field: "chofer")}</g:link></td>
					
						<td>${fieldValue(bean: viajeInstance, field: "destino")}</td>
					
						<td><g:formatDate date="${viajeInstance.fechaAlta}" /></td>
					
						<td><g:formatDate date="${viajeInstance.fechaHoraSalida}" /></td>
					
						<td>${fieldValue(bean: viajeInstance, field: "origen")}</td>
					
						<td>${fieldValue(bean: viajeInstance, field: "vehiculo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${viajeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
