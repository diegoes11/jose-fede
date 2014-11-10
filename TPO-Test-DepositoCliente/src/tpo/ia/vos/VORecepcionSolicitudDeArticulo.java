package tpo.ia.vos;

import java.io.Serializable;

public class VORecepcionSolicitudDeArticulo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int cantidad;
	
	public VORecepcionSolicitudDeArticulo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
