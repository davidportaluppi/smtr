
<%@ page import="ar.com.smtr.monitoreo.Chofer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'chofer.label', default: 'Chofer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-chofer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-chofer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="appelido" title="${message(code: 'chofer.appelido.label', default: 'Appelido')}" />
					
						<g:sortableColumn property="dni" title="${message(code: 'chofer.dni.label', default: 'Dni')}" />
					
						<g:sortableColumn property="domicilio" title="${message(code: 'chofer.domicilio.label', default: 'Domicilio')}" />
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'chofer.fechaAlta.label', default: 'Fecha Alta')}" />
					
						<g:sortableColumn property="fechaNacimiento" title="${message(code: 'chofer.fechaNacimiento.label', default: 'Fecha Nacimiento')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'chofer.nombre.label', default: 'Nombre')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${choferInstanceList}" status="i" var="choferInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${choferInstance.id}">${fieldValue(bean: choferInstance, field: "appelido")}</g:link></td>
					
						<td>${fieldValue(bean: choferInstance, field: "dni")}</td>
					
						<td>${fieldValue(bean: choferInstance, field: "domicilio")}</td>
					
						<td><g:formatDate date="${choferInstance.fechaAlta}" /></td>
					
						<td><g:formatDate date="${choferInstance.fechaNacimiento}" /></td>
					
						<td>${fieldValue(bean: choferInstance, field: "nombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${choferInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
