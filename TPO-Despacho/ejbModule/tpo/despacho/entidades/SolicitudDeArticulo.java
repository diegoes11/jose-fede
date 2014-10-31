package tpo.despacho.entidades;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import tpo.despacho.vos.SolicitudDeArticuloVO;

@Entity
@Table(name="SolicitudesDeArticulo")
public class SolicitudDeArticulo {
	private int idSolicitudDeArticulo;
	private Deposito deposito;
	private DetalleOrdenDeDespacho detalleOrdenDeDespacho;
	private int cantidadRestante;
	private Date fechaPedido;
	private Date fechaCompletitud;
	
	public SolicitudDeArticulo() {
	}
	
	// Metodos
	@Transient
	public SolicitudDeArticuloVO getSolicitudDeArticuloVO(){
		SolicitudDeArticuloVO solicitudDeArticuloVO = new SolicitudDeArticuloVO();
		solicitudDeArticuloVO.setIdSolicitudDeArticulo(idSolicitudDeArticulo);
		solicitudDeArticuloVO.setNombreDespacho("");
		solicitudDeArticuloVO.setCodigoArticulo(detalleOrdenDeDespacho.getArticulo().getId().getCodigo());
		solicitudDeArticuloVO.setCantidad(detalleOrdenDeDespacho.getCantidad());
		return solicitudDeArticuloVO;
	}
	
	public void actualizarCantidad(int cantidad){
		if(cantidadRestante - cantidad > 0){
			cantidadRestante = cantidadRestante - cantidad;
		}
		// Si completé la solicitud de articulo
		if(cantidadRestante == 0){
			this.setFechaCompletitud(Calendar.getInstance().getTime());
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="NombreDeposito")
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="IDOrdenDeDespacho")
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

	@Column (name="FechaPedido", columnDefinition = "datetime", nullable = true)
	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	@Column (name="FechaCompletitud", columnDefinition = "datetime", nullable = true)
	public Date getFechaCompletitud() {
		return fechaCompletitud;
	}

	public void setFechaCompletitud(Date fechaCompletitud) {
		this.fechaCompletitud = fechaCompletitud;
	}
	
}
