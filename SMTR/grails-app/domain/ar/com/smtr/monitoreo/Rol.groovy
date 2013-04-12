package ar.com.smtr.monitoreo

class Rol {
	String identificador;
	String nombreRol;
	String descripcion;
	static hasMany = [permisos: Permiso, vehiculos: Vehiculo, dispositivosMoviles: DispositivoMovil];	
}
