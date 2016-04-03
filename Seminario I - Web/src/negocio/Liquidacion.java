package negocio;

import java.util.Date;

public class Liquidacion {
	private Date fecha;
	private Empleado empleado;
	
	public Liquidacion(Date fecha, Empleado empleado) {
		super();
		this.fecha = fecha;
		this.empleado = empleado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}
