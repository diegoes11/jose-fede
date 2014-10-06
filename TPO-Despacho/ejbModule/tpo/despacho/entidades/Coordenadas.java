package tpo.despacho.entidades;

import javax.persistence.*;

@Embeddable
public class Coordenadas {
	private IdCoordenadas id;

	public Coordenadas() {
	}

	@EmbeddedId
	public IdCoordenadas getId() {
		return id;
	}

	public void setId(IdCoordenadas id) {
		this.id = id;
	}
}
