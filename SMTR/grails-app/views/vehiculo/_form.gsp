<%@ page import="ar.com.smtr.monitoreo.Vehiculo" %>



<div class="fieldcontain ${hasErrors(bean: vehiculoInstance, field: 'dispositivoMovil', 'error')} required">
	<label for="dispositivoMovil">
		<g:message code="vehiculo.dispositivoMovil.label" default="Dispositivo Movil" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dispositivoMovil" name="dispositivoMovil.id" from="${ar.com.smtr.monitoreo.DispositivoMovil.list()}" optionKey="id" required="" value="${vehiculoInstance?.dispositivoMovil?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vehiculoInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="vehiculo.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${vehiculoInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: vehiculoInstance, field: 'historialMonitoreo', 'error')} ">
	<label for="historialMonitoreo">
		<g:message code="vehiculo.historialMonitoreo.label" default="Historial Monitoreo" />
		
	</label>
	<g:select name="historialMonitoreo" from="${ar.com.smtr.monitoreo.Monitor.list()}" multiple="multiple" optionKey="id" size="5" value="${vehiculoInstance?.historialMonitoreo*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vehiculoInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="vehiculo.marca.label" default="Marca" />
		
	</label>
	<g:textField name="marca" value="${vehiculoInstance?.marca}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vehiculoInstance, field: 'modelo', 'error')} ">
	<label for="modelo">
		<g:message code="vehiculo.modelo.label" default="Modelo" />
		
	</label>
	<g:textField name="modelo" value="${vehiculoInstance?.modelo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vehiculoInstance, field: 'patente', 'error')} ">
	<label for="patente">
		<g:message code="vehiculo.patente.label" default="Patente" />
		
	</label>
	<g:textField name="patente" value="${vehiculoInstance?.patente}"/>
</div>

