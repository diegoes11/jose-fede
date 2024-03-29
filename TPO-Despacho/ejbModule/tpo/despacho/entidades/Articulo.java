package tpo.despacho.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;
import tpo.ia.vos.VOFichaTecnica;

@Entity
@Table(name="Articulos")
public class Articulo {
	// Atributos
	private IdArticulo id;
	private String nombre;
	private String tipo;
	private String descripcion;
	private String marca;
	private float precio;
	private byte[] foto;
	private String nombreFoto;
	private String origen;
	private List<ItemFicha> fichaTecnica;
	
	// Constructor
	public Articulo(){
		fichaTecnica = new ArrayList<ItemFicha>();
	}
	
	// Metodos
	@Transient
	public VOArticulo getArticuloVO(){
		VOArticulo articuloVO = new VOArticulo();
		articuloVO.setCodigo(id.getCodigo());
		articuloVO.setNombre(nombre);
		articuloVO.setTipo(tipo);
		articuloVO.setDescripcion(descripcion);
		articuloVO.setMarca(marca);
		articuloVO.setPrecio(precio);
		articuloVO.setFoto(foto);
		articuloVO.setNombreFoto(nombreFoto);
		articuloVO.setOrigen(origen);
		articuloVO.setDeposito(id.getDeposito().getNombre());
		List<VOFichaTecnica> fichasTecnicas = new ArrayList<VOFichaTecnica>();
		for(ItemFicha itemFicha : fichaTecnica)
		{
			VOFichaTecnica ft = itemFicha.getFichaTecnicaVO();
			fichasTecnicas.add(ft);
		}
		articuloVO.setFichasTecnicas(fichasTecnicas);
		return articuloVO;
	}
	
	@Transient
	public VOArticuloCompleto getArticuloVOCompleto(){
		VOArticuloCompleto articuloVO = new VOArticuloCompleto();
		articuloVO.setCodigo(id.getCodigo());
		articuloVO.setNombre(nombre);
		articuloVO.setTipo(tipo);
		articuloVO.setDescripcion(descripcion);
		articuloVO.setMarca(marca);
		articuloVO.setPrecio(precio);
		articuloVO.setFoto(foto);
		articuloVO.setNombreFoto(nombreFoto);
		articuloVO.setOrigen(origen);
		articuloVO.setDeposito(id.getDeposito().getNombre());
		List<VOFichaTecnica> fichasTecnicas = new ArrayList<VOFichaTecnica>();
		for(ItemFicha itemFicha : fichaTecnica)
		{
			VOFichaTecnica ft = itemFicha.getFichaTecnicaVO();
			fichasTecnicas.add(ft);
		}
		articuloVO.setFichasTecnicas(fichasTecnicas);
		return articuloVO;
	}
	
	public void setArticuloVO(VOArticulo articuloVO){
		nombre = articuloVO.getNombre();
		tipo = articuloVO.getTipo();
		descripcion = articuloVO.getDescripcion();
		marca = articuloVO.getMarca();
		precio = articuloVO.getPrecio();
		foto = articuloVO.getFoto();
		nombreFoto = articuloVO.getNombreFoto();
		origen = articuloVO.getOrigen();
		List<VOFichaTecnica> fichasTecnicasVO = articuloVO.getFichasTecnicas();
		for(VOFichaTecnica fichaTecnicaVO : fichasTecnicasVO)
		{
			ItemFicha itemFicha = new ItemFicha();
			itemFicha.setFichaTecnicaVO(fichaTecnicaVO);
			fichaTecnica.add(itemFicha);
		}
	}
	
	public String obtenerInformeAlta(){
		return "Se ha dado de alta el art�culo con c�digo " + id.getCodigo() + 
				" proveniente del Dep�sito " + id.getDeposito().getNombre();
	}

	// Getters y setters
	
	@EmbeddedId
	public IdArticulo getId() {
		return id;
	}

	public void setId(IdArticulo id) {
		this.id = id;
	}
	
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="Tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	@Column(name="Foto", columnDefinition="varbinary(MAX)")
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@Column(name="NombreFoto")
	String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	@Column(name="Origen")
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name="CodigoArticulo", referencedColumnName="Codigo"),
        @JoinColumn(name="NombreDeposito", referencedColumnName="NombreDeposito")
    })
	public List<ItemFicha> getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(List<ItemFicha> fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
	
}
