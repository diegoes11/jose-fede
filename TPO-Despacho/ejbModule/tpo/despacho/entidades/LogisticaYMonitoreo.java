package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="LogisticaYMonitoreo")
public class LogisticaYMonitoreo {
	private String nombre;
	private Coordenadas coordenadas;
	
	public LogisticaYMonitoreo(){
		
	}
	
	@Id
	@Column(name="Nombre")
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

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}
}
