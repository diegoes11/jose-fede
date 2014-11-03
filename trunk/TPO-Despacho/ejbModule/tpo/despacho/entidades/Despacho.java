package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table (name = "Despachos")
public class Despacho {
	
	private String nombre;
	private Coordenadas coordenadas;
	
	public Despacho() {
	}

	@Id
	@Column (name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Embedded
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}
}
