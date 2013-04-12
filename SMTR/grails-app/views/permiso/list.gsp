
<%@ page import="ar.com.smtr.monitoreo.Permiso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permiso.label', default: 'Permiso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-permiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-permiso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'permiso.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="idPermiso" title="${message(code: 'permiso.idPermiso.label', default: 'Id Permiso')}" />
					
						<g:sortableColumn property="url" title="${message(code: 'permiso.url.label', default: 'Url')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${permisoInstanceList}" status="i" var="permisoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${permisoInstance.id}">${fieldValue(bean: permisoInstance, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: permisoInstance, field: "idPermiso")}</td>
					
						<td>${fieldValue(bean: permisoInstance, field: "url")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${permisoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
