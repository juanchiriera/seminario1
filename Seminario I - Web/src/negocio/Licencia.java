package negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//TODO Se harcodea el Alta de Esto
@Entity
@Table(name="licencias")
public class Licencia {
	@Id
	private int codigo; //le agregue este, numero cada posible licencia para que sea mas sencillo el obtenerDescuento()
	@Column
	private String tipo;
	@Column
	private String motivo;
	//no pondría el atributo haberes, lo haría todo en obtenerDescuento()
	@Column
	private float haberes; // % del sueldo a descontar (ej: 100% = 1, 50% = 0.5)
	@Column
	private int tiempo; //varía segun cada licencia, es la cantidad de días que le corresponde a esa licencia
	@Column
	private int antiguedadRequerida;
	@Column
	private boolean certificado; //si tiene o no certificado, ejemplo dia de examen
	
	public Licencia(){		
	}
	
	public Licencia(int codigo, String tipo, String motivo, float haberes, int tiempo,
			int antiguedadRequerida, boolean certificado) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.motivo = motivo;
		this.haberes = haberes;
		this.tiempo = tiempo;
		this.antiguedadRequerida = antiguedadRequerida;
		this.certificado = certificado;
	}
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public float getHaberes() {
		return haberes;
	}

	public void setHaberes(float haberes) {
		this.haberes = haberes;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getAntiguedadRequerida() {
		return antiguedadRequerida;
	}

	public void setAntiguedadRequerida(int antiguedadRequerida) {
		this.antiguedadRequerida = antiguedadRequerida;
	}

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	public boolean hayLicencias(int codigo, String tipo, String motivo){
		if (this.tipo.equalsIgnoreCase(tipo) || this.codigo==codigo || this.motivo.equalsIgnoreCase(motivo)){
			return true;
		}
		return false;
	}
	

	public boolean sosLicencia(int codigo) {
		return (this.codigo==codigo);
	}

	public boolean getCertificado() {
		return this.certificado;
	}
}