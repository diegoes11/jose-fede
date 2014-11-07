package tpo.ia.vos;

import java.io.Serializable;

public class VOEnvioOrdenDeDespachoLista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idOrdenDeDespacho;

	public VOEnvioOrdenDeDespachoLista(int idOrdenDeDespacho) {
		super();
		this.idOrdenDeDespacho = idOrdenDeDespacho;
	}

	public int getIdOrdenDeDespacho() {
		return idOrdenDeDespacho;
	}

	public void setIdOrdenDeDespacho(int idOrdenDeDespacho) {
		this.idOrdenDeDespacho = idOrdenDeDespacho;
	}
}
