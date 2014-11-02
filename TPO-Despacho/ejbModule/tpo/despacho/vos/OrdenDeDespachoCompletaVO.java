package tpo.despacho.vos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrdenDeDespachoCompletaVO implements Serializable {

	private static final long serialVersionUID = 3954968373348049265L;
	private int idOrdenDeDespacho;
	private String nombrePortalWeb;
	private String nombreLogisticaYMonitoreo;
	private String estado;
	private Date fechaRecepcion;
	private Date fechaEntrega;
	private List<DetalleOrdenDeDespachoCompletaVO> detallesOrdenDeDespachoVO;
	
	public OrdenDeDespachoCompletaVO() {
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

	public List<DetalleOrdenDeDespachoCompletaVO> getDetallesOrdenDeDespachoVO() {
		return detallesOrdenDeDespachoVO;
	}

	public void setDetallesOrdenDeDespachoVO(
			List<DetalleOrdenDeDespachoCompletaVO> detallesOrdenDeDespachoVO) {
		this.detallesOrdenDeDespachoVO = detallesOrdenDeDespachoVO;
	}
}
