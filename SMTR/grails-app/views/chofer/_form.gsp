<%@ page import="ar.com.smtr.monitoreo.Chofer" %>



<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'appelido', 'error')} ">
	<label for="appelido">
		<g:message code="chofer.appelido.label" default="Appelido" />
		
	</label>
	<g:textField name="appelido" value="${choferInstance?.appelido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'dni', 'error')} ">
	<label for="dni">
		<g:message code="chofer.dni.label" default="Dni" />
		
	</label>
	<g:textField name="dni" value="${choferInstance?.dni}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'domicilio', 'error')} ">
	<label for="domicilio">
		<g:message code="chofer.domicilio.label" default="Domicilio" />
		
	</label>
	<g:textField name="domicilio" value="${choferInstance?.domicilio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="chofer.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${choferInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'fechaNacimiento', 'error')} required">
	<label for="fechaNacimiento">
		<g:message code="chofer.fechaNacimiento.label" default="Fecha Nacimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaNacimiento" precision="day"  value="${choferInstance?.fechaNacimiento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="chofer.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${choferInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="chofer.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" value="${choferInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: choferInstance, field: 'viajesRealizados', 'error')} ">
	<label for="viajesRealizados">
		<g:message code="chofer.viajesRealizados.label" default="Viajes Realizados" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${choferInstance?.viajesRealizados?}" var="v">
    <li><g:link controller="viaje" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="viaje" action="create" params="['chofer.id': choferInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'viaje.label', default: 'Viaje')])}</g:link>
</li>
</ul>

</div>

