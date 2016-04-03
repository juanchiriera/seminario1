package view;

public class EmpleadoV {
	public String nombre;
	public String apellido;
	public String dni;
	public String cuil;
	public int nroEscuela;
	public String nombreEscuela;
	
	public EmpleadoV(String nombre, String apellido, String dni, String cuil,
			int nroEscuela, String nombreEscuela) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cuil = cuil;
		this.nroEscuela = nroEscuela;
		this.nombreEscuela = nombreEscuela;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public int getNroEscuela() {
		return nroEscuela;
	}
	public void setNroEscuela(int nroEscuela) {
		this.nroEscuela = nroEscuela;
	}
	public String getNombreEscuela() {
		return nombreEscuela;
	}
	public void setNombreEscuela(String nombreEscuela) {
		this.nombreEscuela = nombreEscuela;
	}

}
