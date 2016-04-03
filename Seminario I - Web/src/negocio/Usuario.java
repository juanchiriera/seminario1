package negocio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@Column
	private String nombreUsuario;
	@Column
	private String contaseña;
	@Column
	private String token;

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setContraseña(String contraseña) {
		this.contaseña = contraseña;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public String validarContraseña(String password) {
		MessageDigest md;
		if (this.contaseña.equals(password)) {
			try {
				Date fecha = new Date();
				String preHash = password + fecha.toString();
				md = MessageDigest.getInstance("SHA-512");
				md.update(preHash.getBytes());
				byte[] aMessageDigest = md.digest();
				String token = Base64.getEncoder().encodeToString(aMessageDigest);
				this.token = token;
				return (token);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
