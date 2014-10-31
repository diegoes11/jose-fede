package tpo.despacho.entidades;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="OrdenesDeDespacho")
public class OrdenDeDespacho {
	
	private IdOrdenDeDespacho id;
	private LogisticaYMonitoreo logisticaYMonitoreo;
	private String estado;
	private List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho;
	private Date fechaRecepcion;
	private Date fechaEntrega;
	
	public OrdenDeDespacho(){
	
	}
	
	// M�todos	
	public void verificarOrdenCompleta(){
		boolean ordenCompleta = true;
		for(DetalleOrdenDeDespacho detalle : detallesOrdenDeDespacho){
			if(detalle.getEstado().equals("incompleto"))
				ordenCompleta = false;
		}
		if(ordenCompleta == true){
			this.setFechaEntrega(Calendar.getInstance().getTime());
			setEstado("despachada");
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

	@Column (name="FechaRecepcion")
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Column (name="FechaEntrega")
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}
