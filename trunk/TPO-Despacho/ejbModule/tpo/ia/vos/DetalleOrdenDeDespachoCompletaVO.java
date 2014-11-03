package tpo.ia.vos;

import java.io.Serializable;
import java.util.Date;

public class DetalleOrdenDeDespachoCompletaVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idDetalleOrdenDeDespacho;
	private int idSolicitudDeArticulo;
	private String nombreDeposito;
	private String estado;
	private Date fechaPedido;
	private Date fechaCompletitud;
	private int codigoArticulo;
	private String nombreArticulo;
	private int cantidadSolicitada;
	private int cantidadRecibida;
	
	public DetalleOrdenDeDespachoCompletaVO() {
		super();
	}

	public int getIdDetalleOrdenDeDespacho() {
		return idDetalleOrdenDeDespacho;
	}

	public void setIdDetalleOrdenDeDespacho(int idDetalleOrdenDeDespacho) {
		this.idDetalleOrdenDeDespacho = idDetalleOrdenDeDespacho;
	}

	public int getIdSolicitudDeArticulo() {
		return idSolicitudDeArticulo;
	}

	public void setIdSolicitudDeArticulo(int idSolicitudDeArticulo) {
		this.idSolicitudDeArticulo = idSolicitudDeArticulo;
	}

	public String getNombreDeposito() {
		return nombreDeposito;
	}

	public void setNombreDeposito(String nombreDeposito) {
		this.nombreDeposito = nombreDeposito;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaCompletitud() {
		return fechaCompletitud;
	}

	public void setFechaCompletitud(Date fechaCompletitud) {
		this.fechaCompletitud = fechaCompletitud;
	}

	public int getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public int getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}
}
