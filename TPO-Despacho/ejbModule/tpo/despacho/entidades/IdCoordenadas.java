package tpo.despacho.entidades;

import javax.persistence.*;

@Embeddable
public class IdCoordenadas {
	private float x;
	private float y;
	
	public IdCoordenadas(){
		
	}

	@Column(name="X")
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	@Column(name="Y")
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
