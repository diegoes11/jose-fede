package tpo.despacho.entidades;

import javax.persistence.*;

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
		setEstado("Completo");
		// NOTIFICAR A PORTAL WEB
		ordenDeDespacho.verificarOrdenCompleta();
	}
	
	// Getters y Setters
	@Id
	@GeneratedValue
	@Column (name = "ID")
	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "Codigo")
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID")
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

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="IDSolicitudDeArticulo", referencedColumnName="ID")
	public SolicitudDeArticulo getSolicitudDeArticulo() {
		return solicitudDeArticulo;
	}

	public void setSolicitudDeArticulo(SolicitudDeArticulo solicitudDeArticulo) {
		this.solicitudDeArticulo = solicitudDeArticulo;
	}
}
