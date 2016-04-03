package negocio;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;





@Entity
@Table(name="empleados")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.STRING)
public abstract class Empleado {
	@Column
	protected String nombre;
	@Column
	protected String apellido;
	@Column
	protected int antiguedad; //si es el 10% va 10, en los calculos luego dividimos por 100
	@Column
	protected Date fechaDeIngreso;
	@Column
	protected String cuil;
	@Id
	protected String dni;
	@Column
	protected Date fechaDeNacimiento;
	@Column
	protected boolean estado;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER )
	@JoinColumn(referencedColumnName="numero", name="nroEscuela")
	protected Escuela escuela;
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="dni")
	protected Collection<Novedad> novedades;
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="dni")
	protected Collection<LicenciaEmpleado> licenciasEmpleados;
	
	public Empleado(){
		super();
	}
	
	public Empleado(String nombre, String apellido, int antiguedad,
				Date fechaDeIngreso, String cuil, String dni,
				Date fechaDeNacimiento, Escuela escuela) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.antiguedad = antiguedad;
		this.fechaDeIngreso = fechaDeIngreso;
		this.cuil = cuil;
		this.dni = dni;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.estado = true;
		this.escuela = escuela;
		this.novedades = new Vector<Novedad>();
		
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

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public boolean getEstado(){
		return this.estado;
	}
	
	public Collection<Novedad> getNovedades() {
		return novedades;
	}

	public void setNovedades(Collection<Novedad> novedades) {
		this.novedades = novedades;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	
	public boolean estaEnEscuela(int numero) {
		return (this.escuela.getNro() == numero);
	}
	
	public boolean sosDocente(String apellido, String nombre, String cuil, String dni, 
			String materia, String curso, String division, boolean estado){
		if ((this.apellido.equalsIgnoreCase(apellido)||apellido==null)
				&& (this.nombre.equalsIgnoreCase(nombre)||nombre==null)
				&& (this.cuil.equalsIgnoreCase(cuil)||cuil==null) 
				&& (this.dni.equalsIgnoreCase(dni)||dni==null))
			/**TODO por defecto viene para elegir un estado, como represento eso?*/	
			//&& (this.estado == estado||estado==null)){
				
			//falta ver tenesClase(materia, curso, division);
			return false;
		
		else
			return true;
	}
	
	public boolean sosEmpleado(String dni) {
		return (this.dni.equals(dni));
	}
	
	public Collection<LicenciaEmpleado> getLicenciasEmpleados() {
		return licenciasEmpleados;
	}

	public void setLicenciasEmpleados(Collection<LicenciaEmpleado> licenciasEmpleados) {
		this.licenciasEmpleados = licenciasEmpleados;
	}

	
//	public DescuentoV vistaDescuento (int mes){
//		DescuentoV detalleDescuento = null;
//		detalleDescuento.setMes(mes);
//		detalleDescuento.setApellido(this.apellido);
//		detalleDescuento.setNombre(this.nombre);
//		detalleDescuento.setDni(this.dni);
//		float descuento = 0;
//		for(Novedad novedad : novedades){
//			if(novedad.getFecha().getMonth() == mes) 
//				TODO: tema del descuento total
//				descuento += novedad.obtenerDescuentoTotal();
//		}
//		detalleDescuento.setDescuento(descuento);
//		return detalleDescuento;
//	}
}
