<%@ page import="ar.com.smtr.monitoreo.Usuario" %>



<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="usuario.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${usuarioInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'domicilio', 'error')} ">
	<label for="domicilio">
		<g:message code="usuario.domicilio.label" default="Domicilio" />
		
	</label>
	<g:textField name="domicilio" value="${usuarioInstance?.domicilio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="usuario.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${usuarioInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'fechaNacimiento', 'error')} required">
	<label for="fechaNacimiento">
		<g:message code="usuario.fechaNacimiento.label" default="Fecha Nacimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaNacimiento" precision="day"  value="${usuarioInstance?.fechaNacimiento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="usuario.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${usuarioInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="usuario.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${usuarioInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="usuario.roles.label" default="Roles" />
		
	</label>
	<g:select name="roles" from="${ar.com.smtr.monitoreo.Rol.list()}" multiple="multiple" optionKey="id" size="5" value="${usuarioInstance?.roles*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="usuario.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${usuarioInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="usuario.usuario.label" default="Usuario" />
		
	</label>
	<g:textField name="usuario" value="${usuarioInstance?.usuario}"/>
</div>

