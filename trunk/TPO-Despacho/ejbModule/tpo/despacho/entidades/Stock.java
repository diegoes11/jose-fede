package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="Stock")
public class Stock {
	private Articulo articulo;
	private int cantidad;
	private Deposito deposito;
	
	public Stock(){
		
	}

	@OneToOne
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	@Column(name="Cantidad")
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@ManyToOne
	@JoinColumn(name="NombreDeposito", referencedColumnName="Nombre")
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	
	
}
