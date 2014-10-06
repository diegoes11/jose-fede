package tpo.despacho.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Articulos")
public class Articulo {
	// Atributos
	private int codigo;
	private String nombre;
	private String categoria;
	private String descripcion;
	private String marca;
	private float precio;
	private String foto;
	private String origen;
	private List<ItemFicha> fichaTecnica;
	
	// Constructor
	public Articulo(){
		
	}

	// Getters y setters
	@Id
	@Column(name="Codigo")
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="Categoria")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Column(name="Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name="Marca")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column(name="Precio")
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Column(name="Foto")
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Column(name="Origen")
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@OneToMany (cascade = CascadeType.ALL)
	public List<ItemFicha> getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(List<ItemFicha> fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
	
}
