package negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cargos")
public class Cargo {
	@Id
	@Column(name="id", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCargo;
	@Column
	private String nombre;
	@Column
	private float sueldoBasico;
	@Column
	private int horasTrabajo;
	@Column
	private boolean estado;

	public Cargo(){
	}
	
	public Cargo(String nombre, float sueldoBasico, int horasTrabajo,
			boolean estado) {
		super();
		this.nombre = nombre;
		this.horasTrabajo = horasTrabajo;
		this.sueldoBasico = sueldoBasico;
		this.estado = estado;
	}

	public int getIdCargo() {
		return idCargo;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(float sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean sosCargo(String nombreCargo) {
		return (this.nombre.equals(nombreCargo));
	}

	public void setIdCargo(int idCargo) {
		this.idCargo=idCargo;
	}

	public void setHoras(int horasTrabajo) {
		this.horasTrabajo = horasTrabajo;
	}

	public int getHorasTrabajo() {
		// TODO Auto-generated method stub
		return this.horasTrabajo;
	}
	
}
