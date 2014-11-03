package tpo.ia.vos;

import java.io.Serializable;

public class UsuarioVO implements Serializable {
	// Atributos
	private static final long serialVersionUID = 1L;
	private long dni;
	private String nombre;
	private String apellido;
	private boolean activo;
	
	// Constructores
	public UsuarioVO(long dni, String nombre, String apellido){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public UsuarioVO(long dni, String nombre, String apellido, boolean activo){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo = activo;
	}
	
	public UsuarioVO() {
	}

	// Getters y Setters
	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	// Métodos
}