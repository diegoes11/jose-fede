package tpo.despacho.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cola {
	private String nombre;
	private String usuario;
	private String password;
	
	public Cola(){
		
	}

	@Column(name="NombreCola")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="UsuarioCola")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name="PasswordCola")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
