package tpo.despacho.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class IdArticulo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private Deposito deposito;
	
	public IdArticulo() {
	}

	public IdArticulo(int codigo, Deposito deposito) {
		super();
		this.codigo = codigo;
		this.deposito = deposito;
	}
	
	@Column(name="Codigo")
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="NombreDeposito")
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
}
