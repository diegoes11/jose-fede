package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="Depositos")
public class Deposito {
	
	private String nombre;
	private String ip;
	private Coordenadas coordenadas;
	private Cola recepcionDeSolicitudes;
	
	public Deposito(){
		
	}
	
	public String generarUrlRecepcionSolicitudDeArticulos(){
		return "remote://" + ip + ":4447";
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

	@Embedded
	public Cola getRecepcionDeSolicitudes() {
		return recepcionDeSolicitudes;
	}

	public void setRecepcionDeSolicitudes(Cola recepcionDeSolicitudes) {
		this.recepcionDeSolicitudes = recepcionDeSolicitudes;
	}

}
