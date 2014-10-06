package test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tpo.despacho.facade.DespachoFacade;
import tpo.despacho.vos.UsuarioVO;

public class Test {

	public static void main(String[] args) throws NamingException {
		Properties jndiProps = new Properties();
	 	jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	 	jndiProps.put(Context.PROVIDER_URL,"remote://127.0.0.1:4447");
	 	jndiProps.put(Context.SECURITY_CREDENTIALS, "test1234.");
   		jndiProps.put("jboss.naming.client.ejb.context", true);
   		Context context = new InitialContext(jndiProps);
   		DespachoFacade facade =(DespachoFacade)context.lookup("TPO-DespachoEAR/TPO-Despacho/DespachoFacadeBean!tpo.despacho.facade.DespachoFacade");
   		UsuarioVO usuarioVO = new UsuarioVO(123451l, "Jose", "Ramirez");
   		facade.altaUsuario(usuarioVO);
	}

}
