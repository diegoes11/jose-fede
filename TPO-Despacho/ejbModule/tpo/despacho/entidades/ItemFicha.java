package tpo.despacho.entidades;

import javax.persistence.*;

import tpo.ia.vos.FichaTecnicaVO;

@Entity
@Table(name="ItemsFicha")
public class ItemFicha {
	
	// Atributos
	private int idItemFicha;
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
	@Id
	@Column(name="IdItemFicha")
	public int getIdItemFicha() {
		return idItemFicha;
	}

	public void setIdItemFicha(int idItemFicha) {
		this.idItemFicha = idItemFicha;
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
