package tpo.despacho.entidades;

import javax.persistence.Embeddable;

@Embeddable
public class Cola {
	private String nombre;
	private String usuario;
	private String password;
	
	public Cola(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
