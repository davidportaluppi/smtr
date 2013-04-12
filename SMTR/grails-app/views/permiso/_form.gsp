<%@ page import="ar.com.smtr.monitoreo.Permiso" %>



<div class="fieldcontain ${hasErrors(bean: permisoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="permiso.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${permisoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: permisoInstance, field: 'idPermiso', 'error')} ">
	<label for="idPermiso">
		<g:message code="permiso.idPermiso.label" default="Id Permiso" />
		
	</label>
	<g:textField name="idPermiso" value="${permisoInstance?.idPermiso}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: permisoInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="permiso.url.label" default="Url" />
		
	</label>
	<g:textField name="url" value="${permisoInstance?.url}"/>
</div>

