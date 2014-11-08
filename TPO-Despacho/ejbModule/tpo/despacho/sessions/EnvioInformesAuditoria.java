package tpo.despacho.sessions;

import javax.ejb.Local;

import tpo.ia.vos.VOInformeAuditoria;

@Local
public interface EnvioInformesAuditoria {
	public boolean EnviarInforme(VOInformeAuditoria informe);
}
