package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="PortalesWeb")
public class PortalWeb {
	private String nombre;
	private String ip;
	private Coordenadas coordenadas;
	private String urlRecepcionEstadoOrdenDeDespacho;
	
	public PortalWeb(){
		
	}
	
	public String generarUrlSyncEnvioCambioDeEstadoODD(){
		return "http://" + ip + ":8080/" + urlRecepcionEstadoOrdenDeDespacho;
	}

	@Id
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
