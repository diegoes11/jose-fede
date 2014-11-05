package tpo.ia.vos;

import java.io.Serializable;

public class VOSolicitudDeArticulo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombreDespacho;
	private int idSolicitudDeArticulo;
	private int codigoArticulo;
	private int cantidad;
	
	public VOSolicitudDeArticulo(){
		
	}
	
	public String getNombreDespacho() {
		return nombreDespacho;
	}
	public void setNombreDespacho(String nombreDespacho) {
		this.nombreDespacho = nombreDespacho;
	}
	public int getIdSolicitudDeArticulo() {
		return idSolicitudDeArticulo;
	}
	public void setIdSolicitudDeArticulo(int idSolicitudDeArticulo) {
		this.idSolicitudDeArticulo = idSolicitudDeArticulo;
	}
	public int getCodigoArticulo() {
		return codigoArticulo;
	}
	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
