package tpo.despacho.vos;

import java.io.Serializable;



public class FichaTecnicaVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String atributo;
	private String detalle;
	
	public FichaTecnicaVO(){
		
	}
	public FichaTecnicaVO(int id,String atributo,String detalle){
		this.id=id;
		this.atributo=atributo;
		this.detalle=detalle;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

