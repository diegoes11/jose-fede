package tpo.despacho.entidades;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import tpo.ia.vos.VOSolicitudDeArticulo;

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
	public VOSolicitudDeArticulo getSolicitudDeArticuloVO(){
		VOSolicitudDeArticulo solicitudDeArticuloVO = new VOSolicitudDeArticulo();
		solicitudDeArticuloVO.setIdSolicitudDeArticulo(idSolicitudDeArticulo);
		solicitudDeArticuloVO.setNombreDespacho("DESPACHO1");
		solicitudDeArticuloVO.setCodigoArticulo(detalleOrdenDeDespacho.getArticulo().getId().getCodigo());
		solicitudDeArticuloVO.setCantidad(detalleOrdenDeDespacho.getCantidad());
		return solicitudDeArticuloVO;
	}
	
	public void actualizarCantidad(int cantidad){
		if(cantidadRestante - cantidad >= 0){
			cantidadRestante = cantidadRestante - cantidad;
		}
		// Si completé la solicitud de articulo
		if(cantidadRestante == 0){
			this.setFechaCompletitud(Calendar.getInstance().getTime());
			detalleOrdenDeDespacho.completarDetalle();
		}
	}

	public String obtenerInformeSolicitud(){
		return "Se ha enviado una solicitud de artículos al Deposito " + deposito.getNombre() +
				" por " + detalleOrdenDeDespacho.getCantidad() + " del artículo con código " + detalleOrdenDeDespacho.getArticulo().getId().getCodigo() + 
				" para la orden de despacho " + detalleOrdenDeDespacho.getOrdenDeDespacho().getId().getIdOrdenDeDespacho() + 
				" perteneciente al Portal Web " + detalleOrdenDeDespacho.getOrdenDeDespacho().getId().getPortalWeb().getNombre();
	}
	
	// Getters y Setters
	@Id
	@GeneratedValue
	@Column(name="IdSolicitudDeArticulo")
	public int getIdSolicitudDeArticulo() {
		return idSolicitudDeArticulo;
	}

	public void setIdSolicitudDeArticulo(int idSolicitudDeArticulo) {
		this.idSolicitudDeArticulo = idSolicitudDeArticulo;
	}

	@OneToOne
	@JoinColumn(name="NombreDeposito")
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	@OneToOne
	@JoinColumn(name="IdDetalleOrdenDeDespacho")
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
