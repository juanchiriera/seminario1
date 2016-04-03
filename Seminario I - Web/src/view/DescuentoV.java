package view;

import java.util.Date;

public class DescuentoV {
	private int mes;
	private String dni;
	private String apellido;
	private String nombre;
	private float descuento;
	
	public DescuentoV(int mes, String dni, String apellido, String nombre,
			float descuento) {
		super();
		this.mes = mes;
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.descuento = descuento;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes2) {
		this.mes = mes2;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
}
