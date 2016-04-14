package negocio;

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

import srv.CargoSRV;
import view.SueldoV;
@Entity
@DiscriminatorValue("CC")
public class ConCargo extends Empleado {
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="cargo_empleado", 
	joinColumns={@JoinColumn(name="dni")},
	inverseJoinColumns={@JoinColumn(name="idCargo")})
	private Collection<Cargo> cargos;

	public ConCargo(){
		super();
	}
	public ConCargo(String nombre, String apellido, int antiguedad,
			Date fechaDeIngreso, String cuil, String dni,
			Date fechaDeNacimiento, Escuela escuela, Collection<Cargo> cargos) {
		super(nombre, apellido, antiguedad, fechaDeIngreso, cuil, dni,
				fechaDeNacimiento, escuela);
		this.cargos = cargos;
	}

	

	public Collection<Cargo> getCargos() {
		return this.cargos;
	}

	public void setCargos(Collection<Cargo> cargos) {
		this.cargos = cargos;
	}

	//No están contemplados los descuentos, si se calculan en el View, no se que es lo que pretendemos, 
	//si lo queremos acá es poner que obtenga el descuento como está hecho abajo
	public float calcularSueldo() {
		float basico = 0; 
		float porZona = 0;
		float porAntiguedad = 0;
		for(Cargo cargo : cargos){
			if(cargo.isEstado()){
				basico += cargo.getSueldoBasico();
				porAntiguedad = basico * this.antiguedad / 100;
				float zona = this.getEscuela().getCargoZona();
				porZona += basico * zona/100;//porque tiene que figurar un solo importe por zona
			}
		}
		return basico + porAntiguedad + porZona;
	}
	
	public SueldoV vistaSueldoEmpleado (int mes) {
		SueldoV detalleSueldo = new SueldoV();
		detalleSueldo.setMes(mes);
		detalleSueldo.setApellido(this.apellido);
		detalleSueldo.setNombre(this.nombre);
		//La pantalla dice Dni, yo mostraria cuil porque en todos los recibos de sueldo figura el cuil
		detalleSueldo.setCuil(this.cuil);
		float porZona = 0;
		float sumaBasicos = 0;
		float sumaAntiguadades = 0;
		for(Cargo cargo : cargos){
			if(cargo.isEstado()){
				detalleSueldo.setConcepto(cargo.getNombre());
				float basico = cargo.getSueldoBasico();
				detalleSueldo.getBasico().add(basico);
				sumaBasicos += basico;
				float porAntiguedad = basico * this.antiguedad / 100;
				detalleSueldo.getAntiguedad().add(porAntiguedad);
				sumaAntiguadades += porAntiguedad;
				float zona = this.getEscuela().getCargoZona();
				porZona += basico * zona; //porque tiene que figurar un solo importe por zona
			}
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
