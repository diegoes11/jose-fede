package tpo.despacho.vos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticuloVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private String descripcion;
	private String marca;
	private String tipo;
	private String origen;
	private int stock;
	private float precio;
	private byte[] foto;
	private String nombreFoto;
	private String estado;
	private List<FichaTecnicaVO> fichasTecnicas;
	private String deposito;
	
	public ArticuloVO (){
		
	}

	public String getDetalleFichaTecnicaPorAtributo(String atributo){
		for(FichaTecnicaVO ft : fichasTecnicas){
			if(ft.getAtributo().equals(atributo)){
				return ft.getDetalle();
			}
		}
		return "";
	}
		
	public ArticuloVO(int codigo, String nombre, String descripcion,
			String marca, String pais_origen, int stock, float precio,String tipo,List<FichaTecnicaVO> fichas, String estado, String nombreFoto, byte[] foto) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.origen = pais_origen;
		this.stock = stock;
		this.precio = precio;
		this.tipo=tipo;
		this.fichasTecnicas=fichas;
		this.estado=estado;
		this.nombreFoto=nombreFoto;
		this.foto=foto;
	}
	
	public ArticuloVO(int codigo, String nombre, String descripcion,
			String marca, String pais_origen, int stock, float precio,String tipo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.origen = pais_origen;
		this.stock = stock;
		this.precio = precio;
		this.tipo=tipo;
		this.fichasTecnicas=new ArrayList<FichaTecnicaVO>();
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public List<FichaTecnicaVO> getFichasTecnicas() {
		return fichasTecnicas;
	}

	public void setFichasTecnicas(List<FichaTecnicaVO> fichasTecnicas) {
		this.fichasTecnicas = fichasTecnicas;
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



	public String getDeposito() {
		return deposito;
	}



	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

}


