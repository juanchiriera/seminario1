package negocio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class LicenciaEmpleado {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idLicenciaEmp;
	@Column
	private int cantDisponible;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName="codigo", name="codigoLicencia")
	private Licencia licencia;
	
	public LicenciaEmpleado(){}
	
	public LicenciaEmpleado(int cantDisponible, Licencia licencia) {
		super();
		this.cantDisponible = cantDisponible;
		this.licencia = licencia;
	}
	
	
	public boolean sosLicencia(int codigo, String tipo, String motivo){
		return this.licencia.hayLicencias(codigo, tipo, motivo);
	}

	
	public int getCantDisponible() {
		return cantDisponible;
	}

	public void setCantDisponible(int cantDisponible) {
		this.cantDisponible = cantDisponible;
	}

	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}
	
}
