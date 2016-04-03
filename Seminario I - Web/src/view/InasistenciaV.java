package view;

import java.util.Vector;

import negocio.LicenciaEmpleado;

public class InasistenciaV {
	private String apellido;
	private String nombre;
	private String dni;
	private Vector<LicenciaEmpleado> licencias;
	
	public InasistenciaV(String apellido, String nombre, String dni) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.licencias = new Vector<LicenciaEmpleado>();
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Vector<LicenciaEmpleado> getLicencias() {
		return licencias;
	}

	public void setLicencias(Vector<LicenciaEmpleado> licencias) {
		this.licencias = licencias;
	}
		
}
