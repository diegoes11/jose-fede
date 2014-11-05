package tpo.ia.vos;

import java.io.Serializable;
import java.util.List;

public class VOOrdenDeDespacho implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int idOrdenDeDespacho;
	private String nombrePortalWeb;
	private String nombreLogisticaYMonitoreo;
	private List<VODetalleOrdenDeDespacho> detallesOrdenDeDespachoVO;
		
	public VOOrdenDeDespacho() {
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
	public List<VODetalleOrdenDeDespacho> getDetallesOrdenDeDespachoVO() {
		return detallesOrdenDeDespachoVO;
	}
	public void setDetallesOrdenDeDespachoVO(
			List<VODetalleOrdenDeDespacho> detallesOrdenDeDespachoVO) {
		this.detallesOrdenDeDespachoVO = detallesOrdenDeDespachoVO;
	}
	public String getNombreLogisticaYMonitoreo() {
		return nombreLogisticaYMonitoreo;
	}

	public void setNombreLogisticaYMonitoreo(String nombreLogisticaYMonitoreo) {
		this.nombreLogisticaYMonitoreo = nombreLogisticaYMonitoreo;
	}
	
	
}
