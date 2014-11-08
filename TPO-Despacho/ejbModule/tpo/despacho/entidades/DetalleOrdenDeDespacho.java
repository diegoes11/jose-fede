package tpo.despacho.entidades;

import javax.persistence.*;

import tpo.ia.vos.VODetalleOrdenDeDespachoCompleta;

@Entity
@Table(name="DetallesOrdenDeDespacho")
public class DetalleOrdenDeDespacho {
	
	private int idDetalle;
	private Articulo articulo;
	private OrdenDeDespacho ordenDeDespacho;
	private int cantidad;
	private String estado;
	private SolicitudDeArticulo solicitudDeArticulo;
	
	public DetalleOrdenDeDespacho() {
	}
	
	// Metodos	
	public void completarDetalle(){
		// Actualizo el estado del detalle
		setEstado("completo");
		ordenDeDespacho.verificarOrdenCompleta();
	}
	
	public boolean estaCompleto(){
		if(estado.equals("completo"))
			return true;
		else
			return false;
	}
	
	public String obtenerInformeCompletitud(){
		return "Se han recibido todos los items (" + cantidad + ") del artículo  " + articulo.getId().getCodigo() + 
				" proveniente del Depósito " + articulo.getId().getDeposito().getNombre() +
				" para la orden de despacho " + ordenDeDespacho.getId().getIdOrdenDeDespacho() +
				" perteneciente al Portal Web " + ordenDeDespacho.getId().getPortalWeb();
	}
	
	@Transient
	public VODetalleOrdenDeDespachoCompleta getDetalleOrdenDeDespachoVO(){
		VODetalleOrdenDeDespachoCompleta doddvo = new VODetalleOrdenDeDespachoCompleta();
		doddvo.setIdDetalleOrdenDeDespacho(idDetalle);
		doddvo.setIdSolicitudDeArticulo(solicitudDeArticulo.getIdSolicitudDeArticulo());
		doddvo.setNombreDeposito(solicitudDeArticulo.getDeposito().getNombre());
		doddvo.setEstado(getEstado());
		doddvo.setFechaPedido(solicitudDeArticulo.getFechaPedido());
		doddvo.setFechaCompletitud(solicitudDeArticulo.getFechaCompletitud());
		doddvo.setCodigoArticulo(articulo.getId().getCodigo());
		doddvo.setNombreArticulo(articulo.getNombre());
		doddvo.setCantidadSolicitada(cantidad);
		doddvo.setCantidadRecibida(cantidad-solicitudDeArticulo.getCantidadRestante());
		return doddvo;
	}
	
	// Getters y Setters
	@Id
	@GeneratedValue
	@Column (name = "IdDetalleOrdenDeDespacho")
	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	
	@ManyToOne
	@JoinColumns({
        @JoinColumn(name="CodigoArticulo", referencedColumnName="Codigo"),
        @JoinColumn(name="NombreDeposito", referencedColumnName="NombreDeposito")
    })
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	@ManyToOne
	@JoinColumns({
        @JoinColumn(name="IdOrdenDeDespacho", referencedColumnName="IdOrdenDeDespacho"),
        @JoinColumn(name="NombrePortalWeb", referencedColumnName="NombrePortalWeb")
    })
	public OrdenDeDespacho getOrdenDeDespacho() {
		return ordenDeDespacho;
	}

	public void setOrdenDeDespacho(OrdenDeDespacho ordenDeDespacho) {
		this.ordenDeDespacho = ordenDeDespacho;
	}

	@Column(name="Cantidad")
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@OneToOne(cascade=CascadeType.ALL, mappedBy="detalleOrdenDeDespacho")
	public SolicitudDeArticulo getSolicitudDeArticulo() {
		return solicitudDeArticulo;
	}

	public void setSolicitudDeArticulo(SolicitudDeArticulo solicitudDeArticulo) {
		this.solicitudDeArticulo = solicitudDeArticulo;
	}
}
