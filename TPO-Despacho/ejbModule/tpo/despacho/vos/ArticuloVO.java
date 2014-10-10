package tpo.despacho.vos;

import java.io.Serializable;
import java.util.List;

public class ArticuloVO implements Serializable {
	private static final long serialVersionUID = 4129130312589973623L;
	private int codigo;
	private String nombre;
	private String tipo;
	private String descripcion;
	private String marca;
	private float precio;
	private byte[] foto;
	private String nombreFoto;
	private String origen;
	private float stock;
	private List<ItemFichaVO> fichaTecnica;
	
	public ArticuloVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public List<ItemFichaVO> getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(List<ItemFichaVO> fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
	
	
}
