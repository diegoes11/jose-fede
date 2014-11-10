package tpo.ia.vos;

import java.io.Serializable;
import java.util.Date;

public class VOInformeAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	private int fechaYHora;
	
	public VOInformeAuditoria() {
		
	}
	
	public VOInformeAuditoria(String descripcion) {
		this.descripcion = descripcion;
		this.fechaYHora = (int) (new Date().getTime()/1000);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(int fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
}
