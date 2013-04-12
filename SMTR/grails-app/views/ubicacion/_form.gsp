<%@ page import="ar.com.smtr.monitoreo.Ubicacion" %>



<div class="fieldcontain ${hasErrors(bean: ubicacionInstance, field: 'altitud', 'error')} ">
	<label for="altitud">
		<g:message code="ubicacion.altitud.label" default="Altitud" />
		
	</label>
	<g:textField name="altitud" value="${ubicacionInstance?.altitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ubicacionInstance, field: 'latitud', 'error')} ">
	<label for="latitud">
		<g:message code="ubicacion.latitud.label" default="Latitud" />
		
	</label>
	<g:textField name="latitud" value="${ubicacionInstance?.latitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ubicacionInstance, field: 'longitud', 'error')} ">
	<label for="longitud">
		<g:message code="ubicacion.longitud.label" default="Longitud" />
		
	</label>
	<g:textField name="longitud" value="${ubicacionInstance?.longitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ubicacionInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="ubicacion.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="time" type="number" value="${ubicacionInstance.time}" required=""/>
</div>

