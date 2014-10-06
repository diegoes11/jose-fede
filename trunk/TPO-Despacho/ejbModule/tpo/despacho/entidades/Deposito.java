package tpo.despacho.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Depositos")
public class Deposito {
	
	private String nombre;
	private Coordenadas coordenadas;
	private List<Stock> stock;
	
	public Deposito(){
		
	}
	
	@Id
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	@OneToMany (cascade = CascadeType.ALL)
	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	
	
}
