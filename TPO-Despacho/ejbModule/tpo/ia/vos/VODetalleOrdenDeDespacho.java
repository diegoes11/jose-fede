package tpo.ia.vos;

import java.io.Serializable;

public class VODetalleOrdenDeDespacho implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int codigoArticulo;
	private int cantidad;
	
	public VODetalleOrdenDeDespacho() {

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
