package view;

import java.util.Vector;

public class SueldoV {
	private int mes;
	private String cuil;
	private String apellido;
	private String nombre;
	private String concepto;
	private Vector <Float> basico;
	private Vector <Float> antiguedad;
	private float zona;
	private float descuento;
	private float sueldoTotal;
	
	public SueldoV(int mes, String cuil, String apellido, String nombre, String concepto,
			Vector<Float> basico, Vector<Float> antiguedad, float zona,
			float descuento, float sueldoTotal) {
		super();
		this.mes = mes;
		this.cuil = cuil;
		this.apellido = apellido;
		this.nombre = nombre;
		this.basico = basico;
		this.antiguedad = antiguedad;
		this.zona = zona;
		this.descuento = descuento;
		this.sueldoTotal = sueldoTotal;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
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

	public Vector<Float> getBasico() {
		return basico;
	}

	public void setBasico(Vector<Float> basico) {
		this.basico = basico;
	}

	public Vector<Float> getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(Vector<Float> antiguedad) {
		this.antiguedad = antiguedad;
	}

	public float getZona() {
		return zona;
	}

	public void setZona(float zona) {
		this.zona = zona;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getSueldoTotal() {
		return sueldoTotal;
	}

	public void setSueldoTotal(float sueldoTotal) {
		this.sueldoTotal = sueldoTotal;
	}
	
}
