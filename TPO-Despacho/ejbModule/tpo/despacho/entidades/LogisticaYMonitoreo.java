package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="LogisticaYMonitoreo")
public class LogisticaYMonitoreo {
	private String nombre;
	private String ip;
	private Coordenadas coordenadas;
	private String urlRecepcionEstadoOrdenDeDesapcho;
	private Cola colaInformes;
	private boolean informeAsincronico;
	
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

	@Column(name="RecepcionEstadoOrdenDeDesapcho")
	public String getUrlRecepcionEstadoOrdenDeDesapcho() {
		return urlRecepcionEstadoOrdenDeDesapcho;
	}

	public void setUrlRecepcionEstadoOrdenDeDesapcho(
			String urlRecepcionEstadoOrdenDeDesapcho) {
		this.urlRecepcionEstadoOrdenDeDesapcho = urlRecepcionEstadoOrdenDeDesapcho;
	}

	@Embedded
	public Cola getColaInformes() {
		return colaInformes;
	}

	public void setColaInformes(Cola colaInformes) {
		this.colaInformes = colaInformes;
	}

	@Column(name="InformeAsincronico", columnDefinition="bit")
	public boolean isInformeAsincronico() {
		return informeAsincronico;
	}

	public void setInformeAsincronico(boolean informeAsincronico) {
		this.informeAsincronico = informeAsincronico;
	}
}
