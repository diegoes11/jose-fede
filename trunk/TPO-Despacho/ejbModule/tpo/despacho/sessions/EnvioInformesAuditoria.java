package tpo.despacho.sessions;

import javax.ejb.Local;

@Local
public interface EnvioInformesAuditoria {
	public boolean EnviarInforme(String informe);
}
