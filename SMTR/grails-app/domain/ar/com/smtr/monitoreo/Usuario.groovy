package ar.com.smtr.monitoreo

class Usuario {
	String usuario;
	String password;
//	DatosPersonales datosPersonales;
	static hasMany = [roles:Rol];
	
	String nombre;
	String apellido;
	String domicilio;
	Date fechaNacimiento;
	Date fechaAlta;
	String telefono;
   
}

class DatosPersonales {
	String nombre;
	String apellido;
	String domicilio;
	Date fechaNacimiento;
	Date fechaAlta;
	String telefono;
}
