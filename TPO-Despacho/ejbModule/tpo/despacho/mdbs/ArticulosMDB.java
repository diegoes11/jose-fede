package tpo.despacho.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.Deposito;
import tpo.despacho.vos.ArticuloVO;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(
						propertyName = "destination", propertyValue = "java:/queue/Articulos")
		})
public class ArticulosMDB implements MessageListener {
	
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;

    public ArticulosMDB() {
    }
	
    public void onMessage(Message message) {
    	try 
    	{
    		ObjectMessage objectMessage = (ObjectMessage)message;
    		ArticuloVO articuloVO = (ArticuloVO)objectMessage.getObject();
    		// DAR DE ALTA EL ARTICULO EN NUESTRO SISTEMA
    		Articulo articulo = buscarArticulo(articuloVO.getCodigo());
    		Deposito deposito = buscarDeposito(articuloVO.getDeposito());
    		if(articulo == null && deposito != null){
    			articulo = new Articulo();
    			articulo.setArticuloVO(articuloVO);
    			articulo.setDeposito(deposito);
    			//manager.persist(articulo);
    		}
    	}
    	catch (JMSException jmse)
    	{
    		System.out.println(jmse.getStackTrace());
    	}
    }
    
    private Articulo buscarArticulo(int codigo){
    	Articulo articulo = manager.find(Articulo.class, codigo);
    	return articulo;
    }
    
    private Deposito buscarDeposito(String nombre){
    	Deposito deposito = manager.find(Deposito.class, nombre);
    	return deposito;
    }
}
