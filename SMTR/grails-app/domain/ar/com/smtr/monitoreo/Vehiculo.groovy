package ar.com.smtr.monitoreo

class Vehiculo {
	String patente;
	String modelo;
	String marca;
	Date fechaAlta;
	
	DispositivoMovil dispositivoMovil;
	
	static hasMany = [historialMonitoreo: Monitor];	
    
	static constraints = {
		dispositivoMovil unique: true
	}
}
