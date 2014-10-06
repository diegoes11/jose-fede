package tpo.despacho.entidades;

import javax.persistence.*;

@Entity
@Table(name="ItemsFicha")
public class ItemFicha {
	// Atributos
	private int idItemFicha;
	private String atributo;
	private String detalle;
	
	public ItemFicha()
	{
		
	}

	@Id
	@Column(name="ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
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
