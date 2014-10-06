package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="Stock")
public class Stock {
	
	private int idStock;
	private Articulo articulo;
	private int cantidad;
	private Deposito deposito;
	
	public Stock(){
		
	}
	
	@Id
	@GeneratedValue
	@Column (name = "ID")
	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
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

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="NombreDeposito", referencedColumnName="Nombre")
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	
	
}
