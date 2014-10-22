package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="Depositos")
public class Deposito {
	
	private String nombre;
	private Coordenadas coordenadas;
	
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

	@Embedded
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

}
