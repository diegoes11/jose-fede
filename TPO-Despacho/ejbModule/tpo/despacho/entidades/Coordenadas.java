package tpo.despacho.entidades;

import javax.persistence.*;

@Embeddable
public class Coordenadas {
	private float x;
	private float y;

	public Coordenadas() {
	}
	
	@Column(name="X")
	public float getX() {
		return x;
	}

	@Column(name="Y")
	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
