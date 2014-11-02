package tpo.despacho.entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import tpo.despacho.vos.DetalleOrdenDeDespachoCompletaVO;
import tpo.despacho.vos.OrdenDeDespachoCompletaVO;

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
	
	@Transient
	public OrdenDeDespachoCompletaVO getOrdenDeDespachoVO(){
		OrdenDeDespachoCompletaVO oddvo = new OrdenDeDespachoCompletaVO();
		oddvo.setIdOrdenDeDespacho(id.getIdOrdenDeDespacho());
		oddvo.setNombreLogisticaYMonitoreo(logisticaYMonitoreo.getNombre());
		oddvo.setNombrePortalWeb(id.getPortalWeb().getNombre());
		oddvo.setEstado(estado);
		oddvo.setFechaRecepcion(fechaRecepcion);
		oddvo.setFechaEntrega(fechaEntrega);
		List<DetalleOrdenDeDespachoCompletaVO> detallesOrdenDeDespachoVO = new ArrayList<DetalleOrdenDeDespachoCompletaVO>();
		for(DetalleOrdenDeDespacho dodd : detallesOrdenDeDespacho){
			detallesOrdenDeDespachoVO.add(dodd.getDetalleOrdenDeDespachoVO());
		}
		return oddvo;
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
        @JoinColumn(name="IdOrdenDeDespacho", referencedColumnName="IdOrdenDeDespacho"),
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

	@Column (name = "FechaRecepcion", columnDefinition = "datetime", nullable = true)
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Column (name = "FechaEntrega", columnDefinition = "datetime", nullable = true)
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}