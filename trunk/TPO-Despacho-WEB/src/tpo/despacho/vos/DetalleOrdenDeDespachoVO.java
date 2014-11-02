package tpo.despacho.vos;

import java.io.Serializable;

public class DetalleOrdenDeDespachoVO implements Serializable {
	
	private static final long serialVersionUID = -2040193994485861327L;
	private int codigoArticulo;
	private int cantidad;
	
	public DetalleOrdenDeDespachoVO() {

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
