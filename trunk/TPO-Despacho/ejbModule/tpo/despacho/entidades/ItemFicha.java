package tpo.despacho.entidades;

import javax.persistence.*;

import tpo.ia.vos.VOFichaTecnica;

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
	public VOFichaTecnica getFichaTecnicaVO(){
		VOFichaTecnica fichaTecnicaVO = new VOFichaTecnica();
		fichaTecnicaVO.setId(idItemFicha);
		fichaTecnicaVO.setAtributo(atributo);
		fichaTecnicaVO.setDetalle(detalle);
		return fichaTecnicaVO;
	}
	
	public void setFichaTecnicaVO(VOFichaTecnica fichaTecnicaVO){
		atributo = fichaTecnicaVO.getAtributo();
		detalle = fichaTecnicaVO.getDetalle();
	}

	// Getters y setters
	@Id
	@Column(name="IdItemFicha")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
