package tpo.despacho.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="OrdenesDeDespacho")
public class OrdenDeDespacho {
	
	private int idOrdenDeDespacho;
	private PortalWeb portalWeb;
	private String estado;
	private List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho;
	
	public OrdenDeDespacho(){
		
	}

	@Id
	@Column(name="ID")
	public int getIdOrdenDeDespacho() {
		return idOrdenDeDespacho;
	}

	public void setIdOrdenDeDespacho(int idOrdenDeDespacho) {
		this.idOrdenDeDespacho = idOrdenDeDespacho;
	}

	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn (name = "idDetalle")
	public List<DetalleOrdenDeDespacho> getDetallesOrdenDeDespacho() {
		return detallesOrdenDeDespacho;
	}

	public void setDetallesOrdenDeDespacho(
			List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho) {
		this.detallesOrdenDeDespacho = detallesOrdenDeDespacho;
	}

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "Nombre")
	public PortalWeb getPortalWeb() {
		return portalWeb;
	}

	public void setPortalWeb(PortalWeb portalWeb) {
		this.portalWeb = portalWeb;
	}
	
	
}
