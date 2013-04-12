<%@ page import="ar.com.smtr.monitoreo.DispositivoMovil" %>



<div class="fieldcontain ${hasErrors(bean: dispositivoMovilInstance, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="dispositivoMovil.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day"  value="${dispositivoMovilInstance?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: dispositivoMovilInstance, field: 'imei', 'error')} ">
	<label for="imei">
		<g:message code="dispositivoMovil.imei.label" default="Imei" />
		
	</label>
	<g:textField name="imei" value="${dispositivoMovilInstance?.imei}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dispositivoMovilInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="dispositivoMovil.marca.label" default="Marca" />
		
	</label>
	<g:textField name="marca" value="${dispositivoMovilInstance?.marca}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dispositivoMovilInstance, field: 'model', 'error')} ">
	<label for="model">
		<g:message code="dispositivoMovil.model.label" default="Model" />
		
	</label>
	<g:textField name="model" value="${dispositivoMovilInstance?.model}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dispositivoMovilInstance, field: 'proveedor', 'error')} ">
	<label for="proveedor">
		<g:message code="dispositivoMovil.proveedor.label" default="Proveedor" />
		
	</label>
	<g:textField name="proveedor" value="${dispositivoMovilInstance?.proveedor}"/>
</div>

