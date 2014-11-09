package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="LogisticaYMonitoreo")
public class LogisticaYMonitoreo {
	private String nombre;
	private String ip;
	private Coordenadas coordenadas;
	private String urlRecepcionEstadoOrdenDeDespacho;
	private String urlInformes;
	private Cola colaInformes;
	private boolean informeAsincronico;
	
	public LogisticaYMonitoreo(){
	}
	
	public String generarURLCola(){
		return "remote://" + ip + ":4447";
	}
	
	public String generarUrlRESTEnvioCambioDeEstadoODD(){
		return "http://" + ip + ":8080/" + urlRecepcionEstadoOrdenDeDespacho;
	}
	
	public String generarUrlSyncInformes(){
		return "http://" + ip + ":8080/" + urlInformes;
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

	@Column(name="URLInformes")
	public String getUrlInformes() {
		return urlInformes;
	}

	public void setUrlInformes(String urlInformes) {
		this.urlInformes = urlInformes;
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
