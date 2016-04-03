package negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clases")
public class Clase {
	@Column
	private Materia nombre;
	@Id
	@Column(name="numero")
	private int nroClase;
	@Column
	private Curso curso;
	@Column
	private Division division;
	@Column(name="valor_hora")
	private float valor_hc;
	@Column(name="horas_semanales")
	private int hsSemanales;
	@Column
	private boolean estado;
	
	public Clase(){}
	
	public Clase(String nombre, int nroClase, String curso, String division,
				float valor_hc, int hsSemanales, boolean estado) {
		super();
		this.nombre = Materia.valueOf(nombre);
		this.nroClase = nroClase;
		this.curso = Curso.valueOf(curso);
		this.division = Division.valueOf(division);
		this.valor_hc = valor_hc;
		this.hsSemanales = hsSemanales;
		this.estado = estado;
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

	public String getNombre() {
		return nombre.toString();
	}
	
	public void setNombre(String nombre) {
		this.nombre = Materia.valueOf(nombre);
	}
	
	public int getNroClase() {
		return nroClase;
	}
	
	public void setNroClase(int nroClase) {
		this.nroClase = nroClase;
	}
	
	public String getCurso() {
		return curso.toString();
	}
	
	public void setCurso(String curso) {
		this.curso = Curso.valueOf(curso);
	}
	
	public String getDivision() {
		return division.toString();
	}
	
	public void setDivision(String division) {
		this.division = Division.valueOf(division);
	}
	
	public float getValor_hc() {
		return valor_hc;
	}
	
	public void setValor_hc(float valor_hc) {
		this.valor_hc = valor_hc;
	}
	
	public int getHsSemanales() {
		return hsSemanales;
	}
	
	public void setHsSemanales(int hsSemanales) {
		this.hsSemanales = hsSemanales;
	}


	public boolean hayClases(int numero, String nombre, String curso,
			String division) {
		return (this.nroClase == numero || this.nombre.equals(Materia.valueOf(nombre)) || this.curso.equals(Curso.valueOf(curso)) || this.division.equals(Division.valueOf(division)));
	}
	
	public boolean sosClase(int numero){
		return this.nroClase == numero;
	}


	public boolean sosClase(String division, String curso) {
		return (this.division.equals(division)||this.curso.equals(curso));
	}
	
}
