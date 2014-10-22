package tpo.despacho.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="OrdenesDeDespacho")
public class OrdenDeDespacho {
	
	private int idOrdenDeDespacho;
	private PortalWeb portalWeb;
	private String estado;
	private List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho;
	// AGREGAR FECHA
	
	public OrdenDeDespacho(){
	}
	
	// Métodos	
	public void verificarOrdenCompleta(){
		boolean ordenCompleta = true;
		for(DetalleOrdenDeDespacho detalle : detallesOrdenDeDespacho){
			if(detalle.getEstado().equals("Incompleto"))
				ordenCompleta = false;
		}
		if(ordenCompleta == true){
			setEstado("Entregada");
			// ENVIARLE NOTIFICACION A LOGISTICA Y MONITOREO Y AL PORTAL WEB
		}
	}

	// Getters y Setters
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
	@JoinColumn (name = "IDOrdenDeDespacho")
	public List<DetalleOrdenDeDespacho> getDetallesOrdenDeDespacho() {
		return detallesOrdenDeDespacho;
	}

	public void setDetallesOrdenDeDespacho(
			List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho) {
		this.detallesOrdenDeDespacho = detallesOrdenDeDespacho;
	}

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "NombrePortalWeb")
	public PortalWeb getPortalWeb() {
		return portalWeb;
	}

	public void setPortalWeb(PortalWeb portalWeb) {
		this.portalWeb = portalWeb;
	}
	
	
}
