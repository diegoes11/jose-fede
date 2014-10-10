package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="SolicitudesDeArticulo")
public class SolicitudDeArticulo {
	private int idSolicitudDeArticulo;
	private Deposito deposito;
	private DetalleOrdenDeDespacho detalleOrdenDeDespacho;
	private int cantidadRestante;
	
	public SolicitudDeArticulo() {
	}
	
	// Metodos
	public void actualizarCantidad(int cantidad){
		if(cantidadRestante - cantidad > 0){
			cantidadRestante = cantidadRestante - cantidad;
		}
		// Si completé la solicitud de articulo
		if(cantidadRestante == 0){
			detalleOrdenDeDespacho.completarDetalle();
		}
	}

	
	// Getters y Setters
	@Id
	@Column(name="ID")
	public int getIdSolicitudDeArticulo() {
		return idSolicitudDeArticulo;
	}

	public void setIdSolicitudDeArticulo(int idSolicitudDeArticulo) {
		this.idSolicitudDeArticulo = idSolicitudDeArticulo;
	}

	@OneToOne
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	@OneToOne
	public DetalleOrdenDeDespacho getDetalleOrdenDeDespacho() {
		return detalleOrdenDeDespacho;
	}

	public void setDetalleOrdenDeDespacho(
			DetalleOrdenDeDespacho detalleOrdenDeDespacho) {
		this.detalleOrdenDeDespacho = detalleOrdenDeDespacho;
	}

	@Column(name="CantidadRestante")
	public int getCantidadRestante() {
		return cantidadRestante;
	}

	public void setCantidadRestante(int cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}
	
}
