package ar.com.smtr.monitoreo

import java.util.Date;

class Chofer {
	String dni;
	String nombre;
	String appelido;
	String domicilio;
	String telefono;
	Date fechaNacimiento;
	Date fechaAlta;
	
	static hasMany = [viajesRealizados: Viaje];	
}