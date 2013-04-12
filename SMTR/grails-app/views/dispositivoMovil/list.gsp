
<%@ page import="ar.com.smtr.monitoreo.DispositivoMovil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dispositivoMovil.label', default: 'DispositivoMovil')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dispositivoMovil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dispositivoMovil" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaAlta" title="${message(code: 'dispositivoMovil.fechaAlta.label', default: 'Fecha Alta')}" />
					
						<g:sortableColumn property="imei" title="${message(code: 'dispositivoMovil.imei.label', default: 'Imei')}" />
					
						<g:sortableColumn property="marca" title="${message(code: 'dispositivoMovil.marca.label', default: 'Marca')}" />
					
						<g:sortableColumn property="model" title="${message(code: 'dispositivoMovil.model.label', default: 'Model')}" />
					
						<g:sortableColumn property="proveedor" title="${message(code: 'dispositivoMovil.proveedor.label', default: 'Proveedor')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dispositivoMovilInstanceList}" status="i" var="dispositivoMovilInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dispositivoMovilInstance.id}">${fieldValue(bean: dispositivoMovilInstance, field: "fechaAlta")}</g:link></td>
					
						<td>${fieldValue(bean: dispositivoMovilInstance, field: "imei")}</td>
					
						<td>${fieldValue(bean: dispositivoMovilInstance, field: "marca")}</td>
					
						<td>${fieldValue(bean: dispositivoMovilInstance, field: "model")}</td>
					
						<td>${fieldValue(bean: dispositivoMovilInstance, field: "proveedor")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dispositivoMovilInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
