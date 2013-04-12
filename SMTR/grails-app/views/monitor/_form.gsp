<%@ page import="ar.com.smtr.monitoreo.Monitor" %>



<div class="fieldcontain ${hasErrors(bean: monitorInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="monitor.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="time" type="number" value="${monitorInstance.time}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitorInstance, field: 'ubicacion', 'error')} required">
	<label for="ubicacion">
		<g:message code="monitor.ubicacion.label" default="Ubicacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ubicacion" name="ubicacion.id" from="${ar.com.smtr.monitoreo.Ubicacion.list()}" optionKey="id" required="" value="${monitorInstance?.ubicacion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitorInstance, field: 'velocidad', 'error')} ">
	<label for="velocidad">
		<g:message code="monitor.velocidad.label" default="Velocidad" />
		
	</label>
	<g:textField name="velocidad" value="${monitorInstance?.velocidad}"/>
</div>

