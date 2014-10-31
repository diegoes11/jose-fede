package tpo.despacho.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="OrdenesDeDespacho")
public class OrdenDeDespacho {
	
	private IdOrdenDeDespacho id;
	private LogisticaYMonitoreo logisticaYMonitoreo;
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
	
	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@EmbeddedId
	public IdOrdenDeDespacho getId() {
		return id;
	}

	public void setId(IdOrdenDeDespacho id) {
		this.id = id;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name="IdOrdenDeDespacho", referencedColumnName="ID"),
        @JoinColumn(name="NombrePortalWeb", referencedColumnName="NombrePortalWeb")
    })
	public List<DetalleOrdenDeDespacho> getDetallesOrdenDeDespacho() {
		return detallesOrdenDeDespacho;
	}

	public void setDetallesOrdenDeDespacho(
			List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho) {
		this.detallesOrdenDeDespacho = detallesOrdenDeDespacho;
	}

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "NombreLogisticaYMonitoreo")
	public LogisticaYMonitoreo getLogisticaYMonitoreo() {
		return logisticaYMonitoreo;
	}

	public void setLogisticaYMonitoreo(LogisticaYMonitoreo logisticaYMonitoreo) {
		this.logisticaYMonitoreo = logisticaYMonitoreo;
	}
	
	
}
