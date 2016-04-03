package negocio;

import javax.persistence.*;

@Entity
@Table(name="escuelas")
public class Escuela {
	@Id
	@Column(name="numero",nullable=false)
	private int nro;
	@Column(name="nombre")
	private String nombre;
	@Column(name="cargo_por_zona")
	private float cargoZona; //directamente va el porcentaje
	@SuppressWarnings("unused")
	@Column
	private boolean activa;
	
	public Escuela(){};
	
	public Escuela(int nro, String nombre, float cargoZona) {
		super();
		this.activa = true;
		this.nro = nro;
		this.nombre = nombre;
		this.cargoZona = cargoZona;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCargoZona() {
		return cargoZona;
	}

	public void setCargoZona(float cargoZona) {
		this.cargoZona = cargoZona;
	}

	public boolean sosEscuela(int numero){
		return (this.nro == numero);
	}
	
	public boolean hayEscuelas(int numero, String nombre) {
		return (this.nro == numero || this.nombre.contains(nombre));
	}

	public void setInactiva() {
		this.activa = false;
	}
}
