package tpo.despacho.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import tpo.despacho.vos.ArticuloVO;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
						propertyName = "destination", propertyValue = "java:/queue/TPOIA")
		})
public class ArticulosBean implements MessageListener {

    public ArticulosBean() {
    }
    
    public void onMessage(Message message) {
    	try 
    	{
    		ObjectMessage objectMessage = (ObjectMessage)message;
    		ArticuloVO articuloVO = (ArticuloVO)objectMessage.getObject();
    		// DAR DE ALTA EL ARTICULO EN NUESTRO SISTEMA
    	}
    	catch (JMSException jmse)
    	{
    		System.out.println(jmse.getStackTrace());
    	}
    }
}
