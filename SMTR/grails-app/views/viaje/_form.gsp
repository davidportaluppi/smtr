<%@ page import="ar.com.smtr.monitoreo.Viaje" %>



<div class="fieldcontain ${hasErrors(bean: viajeInstance, field: 'chofer', 'error')} required">
	<label for="chofer">
		<g:message code="viaje.chofer.label" default="Chofer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="chofer" name="chofer.id" from="${ar.com.smtr.monitoreo.Chofer.list()}" optionKey="id" required="" value="${viajeInstance?.chofer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: viajeInstance, field: 'destino', 'error')} ">
	<label for="destino">
		<g:message code="viaje.destino.label" default="Destino" />
		
	</label>
	<g:textField name="destino" value="${viajeInstance?.destino}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: viajeInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="viaje.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${viajeInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: viajeInstance, field: 'fechaHoraSalida', 'error')} required">
	<label for="fechaHoraSalida">
		<g:message code="viaje.fechaHoraSalida.label" default="Fecha Hora Salida" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaHoraSalida" precision="day"  value="${viajeInstance?.fechaHoraSalida}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: viajeInstance, field: 'origen', 'error')} ">
	<label for="origen">
		<g:message code="viaje.origen.label" default="Origen" />
		
	</label>
	<g:textField name="origen" value="${viajeInstance?.origen}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: viajeInstance, field: 'vehiculo', 'error')} required">
	<label for="vehiculo">
		<g:message code="viaje.vehiculo.label" default="Vehiculo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="vehiculo" name="vehiculo.id" from="${ar.com.smtr.monitoreo.Vehiculo.list()}" optionKey="id" required="" value="${viajeInstance?.vehiculo?.id}" class="many-to-one"/>
</div>

