package tpo.despacho.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import tpo.ia.vos.VOUsuario;

@Entity
@Table(name="Usuarios")
public class Usuario {
	// Atributos
	private long dni;
	private String nombre;
	private String apellido;
	private boolean activo;
	
	// Constructores
	public Usuario(){
	}
	public Usuario(long dni, String nombre, String apellido){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo = true;
	}
	
	public String obtenerInformeAlta(){
		return "Se ha dado de alta el usuario " + nombre + " " + apellido + ", DNI " + dni;
	}
	
	public String obtenerInformeCambioEstado(){
		String respuesta = "Se ha cambiado el estado del usuario " + nombre + " " + apellido + ", DNI " + dni + "a ";
		if(activo == true){
			respuesta += "ACTIVO";
		}
		else{
			respuesta += "INACTIVO";
		}
		return respuesta;
	}
	
	// Getters y Setters
	@Transient
	public VOUsuario getUsuarioVO(){
		return new VOUsuario(dni, nombre, apellido, activo);
	}
	
	@Id	
	@Column(name="DNI")
	public long getDni() {
		return dni;
	}
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}
	@Column(name="Apellido")
	public String getApellido() {
		return apellido;
	}
	@Column(name="Activo", columnDefinition="bit")
	public boolean isActivo() {
		return activo;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	// Métodos
}
