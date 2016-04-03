package negocio;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="novedades")
public class Novedad {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNovedad;
	@Column
	private Date fecha;
//	private Clase clase; //volaría esto, porque no se como usarlo, tenemos 2 tipos de empleados, uno con clase y otro con cargo
	@OneToOne
	@PrimaryKeyJoinColumn
	private Licencia licencia;
	@Column
	private float oblig_hc_mes; 
	@Column
	private float oblig_hc_noTrab; 
	//en esta última el director ingresa el cálculo de (oblig_hc_mes / semanas del mes corriente) * cantidad de clases que falto (o fraccion)
	//la lógica la hace él, porque es un dato totalmente dinámico, cambia según las semanas del mes y según que día caiga cada clase
	//es directamente el valor que tenemos que descontar en concepto de horas no trabajadas
	
	//Lo anterior era la lógica que se usa, a fines prácticos lo calculo todo, cantClasesAusentes es un entero que arranca en 0 y se incrementa  
	//cada vez que falta un docente o que quiere ponerse una licencia y no tenga los días, entonces hago el mismo cálculo pero automático
	@Column
	private int semanasMesCorriente;
	@Column
	private float cantClasesAusente;
	@Column
	private String tipo;
	@Column
	private String motivo;
	@SuppressWarnings("unused")
	@Column
	private int dias;
	
	public Novedad(Date fecha/*, Clase clase*/, Licencia licencia, float oblig_hc_mes, float oblig_hc_noTrab, int semanasMesCorriente, float cantClasesAusente) {
		super();
		this.fecha = fecha;
//		this.clase = clase;
		this.licencia = licencia;
		this.oblig_hc_mes = oblig_hc_mes;
		this.oblig_hc_noTrab = oblig_hc_noTrab;
		this.semanasMesCorriente = semanasMesCorriente;
		this.cantClasesAusente = cantClasesAusente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

//	public Clase getClase() {
//		return clase;
//	}
//
//	public void setClase(Clase clase) {
//		this.clase = clase;
//	}

	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	public float getOblig_hc_mes() {
		return oblig_hc_mes;
	}

	public void setOblig_hc_mes(float oblig_hc_mes) {
		this.oblig_hc_mes = oblig_hc_mes;
	}

	public float getOblig_hc_noTrab() {
		return oblig_hc_noTrab;
	}
	
	public void setOblig_hc_noTrab(float oblig_hc_noTrab) {
		//Segun lo aclarado arriba
		this.oblig_hc_noTrab = (this.oblig_hc_mes / this.semanasMesCorriente) * this.cantClasesAusente;
	}
	
	public int getSemanasMesCorriente() {
		return semanasMesCorriente;
	}

	public void setSemanasMesCorriente(int semanasMesCorriente) {
		this.semanasMesCorriente = semanasMesCorriente;
	}

	public float getCantClasesAusente() {
		return cantClasesAusente;
	}

	public void setCantClasesAusente(float cantClasesAusente) {
		this.cantClasesAusente = cantClasesAusente;
	}

	public float obtenerDescuentoTotal(float sumaBasicos){
		float descuentoLicencia = this.licencia.getHaberes() * sumaBasicos;
		//Por como está definido quedaría simplemente devolver esto el descuento total
		return this.oblig_hc_noTrab + descuentoLicencia;
	}
	
	public float obtenerDescuentoTotal(){
		float descuentoLicencia = this.licencia.getHaberes();
		//Por como está definido quedaría simplemente devolver esto el descuento total
		return descuentoLicencia;
	}

	public String getTipo() {
		return this.tipo;
	}

	public String getMotivo() {
		return this.motivo;
	}
	
	public boolean sosUnaNovedad(String tipo, String motivo, Date fechaInicio, Date fechaHasta) {
		// TODO Auto-generated method stub
		return (this.tipo.equals(tipo) || this.motivo.equals(motivo) || (this.fecha.after(fechaInicio) && this.fecha.before(fechaHasta)));
	}

	
}