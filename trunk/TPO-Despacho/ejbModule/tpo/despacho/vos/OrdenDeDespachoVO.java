package tpo.despacho.vos;

import java.io.Serializable;
import java.util.List;

public class OrdenDeDespachoVO implements Serializable {
	
	private static final long serialVersionUID = 4722769366909883343L;
	private int idOrdenDeDespacho;
	private String nombrePortalWeb;
	private String nombreLogisticaYMonitoreo;
	private List<DetalleOrdenDeDespachoVO> detallesOrdenDeDespachoVO;
		
	public OrdenDeDespachoVO() {
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
	public List<DetalleOrdenDeDespachoVO> getDetallesOrdenDeDespachoVO() {
		return detallesOrdenDeDespachoVO;
	}
	public void setDetallesOrdenDeDespachoVO(
			List<DetalleOrdenDeDespachoVO> detallesOrdenDeDespachoVO) {
		this.detallesOrdenDeDespachoVO = detallesOrdenDeDespachoVO;
	}
	public String getNombreLogisticaYMonitoreo() {
		return nombreLogisticaYMonitoreo;
	}

	public void setNombreLogisticaYMonitoreo(String nombreLogisticaYMonitoreo) {
		this.nombreLogisticaYMonitoreo = nombreLogisticaYMonitoreo;
	}
	
	
}
