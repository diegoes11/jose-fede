package tpo.despacho.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.VOArticulo;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
						propertyName = "destination", propertyValue = "java:/queue/Articulos")
		})
public class ArticulosMDB implements MessageListener {
	
	private static final Logger LOGGER = Logger.getLogger(ArticulosMDB.class);
	
	@EJB
	DespachoFacade despachoFacade; 

    public ArticulosMDB() {
    }
	
    public void onMessage(Message message) {
    	try {
    		LOGGER.info("Alta artículo...");
    		ObjectMessage objectMessage = (ObjectMessage)message;
    		VOArticulo articuloVO = (VOArticulo)objectMessage.getObject();
    		despachoFacade.altaArticulo(articuloVO);
    		LOGGER.info("Alta artículo: OK");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		LOGGER.error("Alta artículo: Error desconocido - " + e.getStackTrace());
    	}
    }
	
}
