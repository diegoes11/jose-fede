package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="PortalesWeb")
public class PortalWeb {
	private String nombre;
	private Coordenadas coordenadas;
	
	public PortalWeb(){
		
	}

	@Id
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}
}
