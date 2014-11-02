package tpo.despacho.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class IdOrdenDeDespacho implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idOrdenDeDespacho;
	private PortalWeb portalWeb;
	
	public IdOrdenDeDespacho(int idOrdenDeDespacho, PortalWeb portalWeb) {
		super();
		this.idOrdenDeDespacho = idOrdenDeDespacho;
		this.portalWeb = portalWeb;
	}

	public IdOrdenDeDespacho() {
	}

	@Column(name="IdOrdenDeDespacho")
	public int getIdOrdenDeDespacho() {
		return idOrdenDeDespacho;
	}

	public void setIdOrdenDeDespacho(int idOrdenDeDespacho) {
		this.idOrdenDeDespacho = idOrdenDeDespacho;
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
