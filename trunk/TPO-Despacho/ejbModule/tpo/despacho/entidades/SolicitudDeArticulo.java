package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="SolicitudesDeArticulo")
public class SolicitudDeArticulo {
	private int idSolicitudDeArticulo;
	private Deposito deposito;
	private DetalleOrdenDeDespacho detalleOrdenDeDespacho;
	
	public SolicitudDeArticulo() {
	}
	
	@Id
	@Column(name="ID")
	public int getIdSolicitudDeArticulo() {
		return idSolicitudDeArticulo;
	}

	public void setIdSolicitudDeArticulo(int idSolicitudDeArticulo) {
		this.idSolicitudDeArticulo = idSolicitudDeArticulo;
	}

	@OneToOne
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	@OneToOne
	public DetalleOrdenDeDespacho getDetalleOrdenDeDespacho() {
		return detalleOrdenDeDespacho;
	}

	public void setDetalleOrdenDeDespacho(
			DetalleOrdenDeDespacho detalleOrdenDeDespacho) {
		this.detalleOrdenDeDespacho = detalleOrdenDeDespacho;
	}
}
