package negocio;

import java.awt.List;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import view.SueldoV;
@Entity
@DiscriminatorValue("SC")
public class SinCargo  extends Empleado{
	@ManyToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="clase_profesor", 
	joinColumns={@JoinColumn(name="dni")},
	inverseJoinColumns={@JoinColumn(name="idClase")})
	private Collection<Clase> clases;
	
	public SinCargo(){}
	
	public SinCargo(String nombre, String apellido, int antiguedad,
			Date fechaDeIngreso, String cuil, String dni,
			Date fechaDeNacimiento, Collection<Clase> clases, Escuela escuela) {
		super(nombre, apellido, antiguedad, fechaDeIngreso, cuil, dni,
				fechaDeNacimiento, escuela);
		this.clases = new Vector<Clase>();
	}
	
	public SinCargo(ConCargo empleado) {
		this.nombre = String.valueOf(empleado.getNombre());
		this.apellido = String.valueOf(empleado.getApellido());
		this.cuil = String.valueOf(empleado.getCuil());
		this.dni = String.valueOf(empleado.getDni());
		this.escuela = new Escuela(empleado.getEscuela().getNro(), String.valueOf(empleado.getEscuela().getNombre()), empleado.getEscuela().getCargoZona());
		this.estado = true;
		this.fechaDeIngreso = new Date(empleado.getFechaDeIngreso().getTime());
		this.fechaDeNacimiento = new Date(empleado.getFechaDeNacimiento().getTime());
		this.licenciasEmpleados = new Vector<LicenciaEmpleado>();
		this.novedades = new Vector<Novedad>();
		this.clases = new Vector<Clase>();
		this.antiguedad = empleado.getAntiguedad();
	}

	public Collection<Clase> getClases() {
		return clases;
	}

	public void setClases(Collection<Clase> clases) {
		this.clases = clases;
	}

	public boolean tenesClase(String division, String curso){
		for (Clase clase : this.clases) {
			if(clase.sosClase(division, curso))
				return true;
		}
		return false;
		
	}
	
	//No est�n contemplados los descuentos, si se calculan en el View, no se que es lo que pretendemos, 
	//si lo queremos ac� es poner que obtenga el descuento como est� hecho abajo
	public float calcularSueldoSemanal() {
		float basico = 0; 
		float porZona = 0;
		float porAntiguedad = 0;
		for(Clase clase : clases){
			float valorHc = clase.getValor_hc();
			float hsSemanales = clase.getHsSemanales();
			basico += valorHc * hsSemanales;
			porAntiguedad = basico * this.antiguedad / 100;
			float zona = this.escuela.getCargoZona()/100;
			porZona += basico * zona; //porque tiene que figurar un solo importe por zona
		}
		return basico + porAntiguedad + porZona;
	}
	
	@SuppressWarnings({ "null", "deprecation" })
	public SueldoV vistaSueldoEmpleado (int mes){
		SueldoV detalleSueldo = null;
		detalleSueldo.setMes(mes);
		detalleSueldo.setApellido(this.apellido);
		detalleSueldo.setNombre(this.nombre);
		//La pantalla dice Dni, yo mostraria cuil porque en todos los recibos de sueldo figura el cuil
		detalleSueldo.setCuil(this.cuil);
		float porZona = 0;
		float sumaBasicos = 0;
		float sumaAntiguadades = 0;
		for(Clase clase : clases){
			detalleSueldo.setConcepto(clase.getNombre());
			float valorHc = clase.getValor_hc();
			float hsSemanales = clase.getHsSemanales();
			float basico = valorHc * hsSemanales;
			detalleSueldo.getBasico().add(basico);
			sumaBasicos += basico;
			float porAntiguedad = basico * this.antiguedad / 100;
			detalleSueldo.getAntiguedad().add(porAntiguedad);
			sumaAntiguadades += porAntiguedad;
			float zona = this.escuela.getCargoZona();
			porZona += basico * zona; //porque tiene que figurar un solo importe por zona
		}
		detalleSueldo.setZona(porZona);
		//Estoy sumando todos los descuentos de la novedad y mostramos como concepto descuento el total, sin detalle, porque sino es un bardo
		float descuento = 0;
		for(Novedad novedad : novedades){
			if(novedad.getFecha().getMonth() == mes) //como carajo hago esto!?
				descuento += novedad.obtenerDescuentoTotal(sumaBasicos);
		}
		detalleSueldo.setDescuento(descuento);
		detalleSueldo.setSueldoTotal(sumaBasicos + sumaAntiguadades + porZona);
		
		return detalleSueldo;
	}
}
