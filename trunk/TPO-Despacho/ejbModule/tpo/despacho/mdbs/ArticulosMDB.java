package tpo.despacho.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import tpo.despacho.facade.DespachoFacade;
import tpo.despacho.vos.ArticuloVO;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
						propertyName = "destination", propertyValue = "java:/queue/Articulos")
		})
public class ArticulosMDB implements MessageListener {
	
	@EJB
	DespachoFacade despachoFacade;

    public ArticulosMDB() {
    }
	
    public void onMessage(Message message) {
    	try 
    	{
    		ObjectMessage objectMessage = (ObjectMessage)message;
    		ArticuloVO articuloVO = (ArticuloVO)objectMessage.getObject();
    		despachoFacade.altaArticulo(articuloVO);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getStackTrace());
    	}
    }
	
}
