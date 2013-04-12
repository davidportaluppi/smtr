<%@ page import="ar.com.smtr.monitoreo.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="rol.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${rolInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'dispositivosMoviles', 'error')} ">
	<label for="dispositivosMoviles">
		<g:message code="rol.dispositivosMoviles.label" default="Dispositivos Moviles" />
		
	</label>
	<g:select name="dispositivosMoviles" from="${ar.com.smtr.monitoreo.DispositivoMovil.list()}" multiple="multiple" optionKey="id" size="5" value="${rolInstance?.dispositivosMoviles*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'identificador', 'error')} ">
	<label for="identificador">
		<g:message code="rol.identificador.label" default="Identificador" />
		
	</label>
	<g:textField name="identificador" value="${rolInstance?.identificador}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'nombreRol', 'error')} ">
	<label for="nombreRol">
		<g:message code="rol.nombreRol.label" default="Nombre Rol" />
		
	</label>
	<g:textField name="nombreRol" value="${rolInstance?.nombreRol}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'permisos', 'error')} ">
	<label for="permisos">
		<g:message code="rol.permisos.label" default="Permisos" />
		
	</label>
	<g:select name="permisos" from="${ar.com.smtr.monitoreo.Permiso.list()}" multiple="multiple" optionKey="id" size="5" value="${rolInstance?.permisos*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'vehiculos', 'error')} ">
	<label for="vehiculos">
		<g:message code="rol.vehiculos.label" default="Vehiculos" />
		
	</label>
	<g:select name="vehiculos" from="${ar.com.smtr.monitoreo.Vehiculo.list()}" multiple="multiple" optionKey="id" size="5" value="${rolInstance?.vehiculos*.id}" class="many-to-many"/>
</div>

