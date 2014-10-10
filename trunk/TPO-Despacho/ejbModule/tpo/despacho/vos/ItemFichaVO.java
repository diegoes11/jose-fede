package tpo.despacho.vos;

import java.io.Serializable;

public class ItemFichaVO implements Serializable
{
	private static final long serialVersionUID = -8967615296798072116L;
	private String atributo;
	private String detalle;
	
	public ItemFichaVO() {
		super();
	}
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
