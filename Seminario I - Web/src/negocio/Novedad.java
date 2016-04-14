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

import org.hibernate.validator.NotNull;

@Entity
@Table(name="novedades")
public class Novedad {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNovedad;
	@Column
	private Date fecha;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Licencia licencia;
	@Column(nullable=true)
	private float horasCatedraAusente; 
	@Column
	private float oblig_hc_noTrab; 
	@OneToOne
	@PrimaryKeyJoinColumn
	private Clase clase;
	//en esta última el director ingresa el cálculo de (oblig_hc_mes / semanas del mes corriente) * cantidad de clases que falto (o fraccion)
	//la lógica la hace él, porque es un dato totalmente dinámico, cambia según las semanas del mes y según que día caiga cada clase
	//es directamente el valor que tenemos que descontar en concepto de horas no trabajadas
	
	//Lo anterior era la lógica que se usa, a fines prácticos lo calculo todo, cantClasesAusentes es un entero que arranca en 0 y se incrementa  
	//cada vez que falta un docente o que quiere ponerse una licencia y no tenga los días, entonces hago el mismo cálculo pero automático
	@Column
	private int semanasMesCorriente;
	@Column
	private int cantDiasAusente;

	
	public Novedad(Date fecha, Licencia licencia, float horasCatedraAusente, int semanasMesCorriente, int cantDiassAusente) {
		super();
		this.fecha = fecha;
		this.licencia = licencia;
		this.horasCatedraAusente = horasCatedraAusente;
		this.semanasMesCorriente = semanasMesCorriente;
		this.cantDiasAusente = cantDiassAusente;
		setOblig_hc_noTrab();
	}
	
	public Novedad(){
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Clase getClase() {
		return clase;
	}



	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	public float getOblig_hc_mes() {
		return horasCatedraAusente;
	}

	public void setOblig_hc_mes(float oblig_hc_mes) {
		this.horasCatedraAusente = oblig_hc_mes;
	}

	public float getOblig_hc_noTrab() {
		return oblig_hc_noTrab;
	}
	
	public void setOblig_hc_noTrab() {
		//Segun lo aclarado arriba
		this.oblig_hc_noTrab = (this.horasCatedraAusente / this.semanasMesCorriente) * this.cantDiasAusente;
	}
	
	public int getSemanasMesCorriente() {
		return semanasMesCorriente;
	}

	public void setSemanasMesCorriente(int semanasMesCorriente) {
		this.semanasMesCorriente = semanasMesCorriente;
	}

	public int getCantClasesAusente() {
		return cantDiasAusente;
	}

	public void setCantClasesAusente(int cantClasesAusente) {
		this.cantDiasAusente = cantClasesAusente;
	}

	
	
	public float obtenerDescuentoTotal(float sumaBasicos){
		float descuentoLicencia = 0;
		if(clase==null){
			descuentoLicencia = this.licencia.getHaberes() * sumaBasicos * cantDiasAusente;
		}else{
			descuentoLicencia = this.licencia.getHaberes() * clase.getValor_hc() * horasCatedraAusente;
		}
		//Por como está definido quedaría simplemente devolver esto el descuento total
		return descuentoLicencia;
	}


	
	public boolean sosUnaNovedad(String tipo, String motivo, Date fechaInicio, Date fechaHasta) {
		// TODO Auto-generated method stub
		return (this.fecha.after(fechaInicio) && this.fecha.before(fechaHasta));
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	
}