package tpo.despacho.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "Despachos")
public class Despacho {
	
	private String nombre;
	private Coordenadas coordenadas;
	private List<OrdenDeDespacho> ordenesDeDespacho;
	private List<Articulo> articulos;
	private List<Usuario> usuarios;
	
	public Despacho() {
	}

	@Id
	@Column (name = "Nombre")
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

	public List<OrdenDeDespacho> getOrdenesDeDespacho() {
		return ordenesDeDespacho;
	}

	public void setOrdenesDeDespacho(List<OrdenDeDespacho> ordenesDeDespacho) {
		this.ordenesDeDespacho = ordenesDeDespacho;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
