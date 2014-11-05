package tpo.ia.vos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VOOrdenDeDespachoCompleta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenDeDespacho;
	private String nombrePortalWeb;
	private String nombreLogisticaYMonitoreo;
	private String estado;
	private Date fechaRecepcion;
	private Date fechaEntrega;
	private List<VODetalleOrdenDeDespachoCompleta> detallesOrdenDeDespachoVO;
	
	public VOOrdenDeDespachoCompleta() {
		super();
	}

	public int getIdOrdenDeDespacho() {
		return idOrdenDeDespacho;
	}

	public void setIdOrdenDeDespacho(int idOrdenDeDespacho) {
		this.idOrdenDeDespacho = idOrdenDeDespacho;
	}

	public String getNombrePortalWeb() {
		return nombrePortalWeb;
	}

	public void setNombrePortalWeb(String nombrePortalWeb) {
		this.nombrePortalWeb = nombrePortalWeb;
	}

	public String getNombreLogisticaYMonitoreo() {
		return nombreLogisticaYMonitoreo;
	}

	public void setNombreLogisticaYMonitoreo(String nombreLogisticaYMonitoreo) {
		this.nombreLogisticaYMonitoreo = nombreLogisticaYMonitoreo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public List<VODetalleOrdenDeDespachoCompleta> getDetallesOrdenDeDespachoVO() {
		return detallesOrdenDeDespachoVO;
	}

	public void setDetallesOrdenDeDespachoVO(
			List<VODetalleOrdenDeDespachoCompleta> detallesOrdenDeDespachoVO) {
		this.detallesOrdenDeDespachoVO = detallesOrdenDeDespachoVO;
	}
}
