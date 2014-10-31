package tpo.despacho.entidades;

import javax.persistence.*;

import tpo.despacho.vos.FichaTecnicaVO;

@Entity
@Table(name="ItemsFicha")
public class ItemFicha {
	
	// Atributos
	private int idItemFicha;
	private int idItemFichaInterno;
	private String atributo;
	private String detalle;
	
	// Constructor
	public ItemFicha()
	{
		
	}
	
	// Metodos
	@Transient
	public FichaTecnicaVO getFichaTecnicaVO(){
		FichaTecnicaVO fichaTecnicaVO = new FichaTecnicaVO();
		fichaTecnicaVO.setId(idItemFicha);
		fichaTecnicaVO.setAtributo(atributo);
		fichaTecnicaVO.setDetalle(detalle);
		return fichaTecnicaVO;
	}
	
	public void setFichaTecnicaVO(FichaTecnicaVO fichaTecnicaVO){
		idItemFicha = fichaTecnicaVO.getId();
		atributo = fichaTecnicaVO.getAtributo();
		detalle = fichaTecnicaVO.getDetalle();
	}

	// Getters y setters
	@Column(name="ID")
	//@GeneratedValue (strategy = GenerationType.AUTO)
	public int getIdItemFicha() {
		return idItemFicha;
	}

	public void setIdItemFicha(int idItemFicha) {
		this.idItemFicha = idItemFicha;
	}

	@Id
	@Column(name="IDInterno")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int getIdItemFichaInterno() {
		return idItemFichaInterno;
	}

	public void setIdItemFichaInterno(int idItemFichaInterno) {
		this.idItemFichaInterno = idItemFichaInterno;
	}

	@Column(name="Atributo")
	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	@Column(name="Detalle")
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
