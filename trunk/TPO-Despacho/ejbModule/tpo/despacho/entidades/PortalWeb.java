package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="PortalesWeb")
public class PortalWeb {
	private String nombre;
	private Coordenadas coordenadas;
	private String urlRecepcionEstadoOrdenDeDespacho;
	
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

	@Embedded
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	@Column(name="URLRecepcionEstadoOrdenDeDespacho")
	public String getUrlRecepcionEstadoOrdenDeDespacho() {
		return urlRecepcionEstadoOrdenDeDespacho;
	}

	public void setUrlRecepcionEstadoOrdenDeDespacho(
			String urlRecepcionEstadoOrdenDeDespacho) {
		this.urlRecepcionEstadoOrdenDeDespacho = urlRecepcionEstadoOrdenDeDespacho;
	}
}
