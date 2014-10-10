package tpo.despacho.vos;

import java.io.Serializable;

public class ArticuloNuevoVO implements Serializable {
	private static final long serialVersionUID = 7984765589167923220L;
	private String deposito;
	private ArticuloVO articuloVO;
	
	public ArticuloNuevoVO() {
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public ArticuloVO getArticuloVO() {
		return articuloVO;
	}

	public void setArticuloVO(ArticuloVO articuloVO) {
		this.articuloVO = articuloVO;
	}
		
}
