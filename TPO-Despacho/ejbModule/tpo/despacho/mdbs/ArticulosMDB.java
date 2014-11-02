package tpo.despacho.mdbs;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.Deposito;
import tpo.despacho.entidades.IdArticulo;
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
    			articulo.setId(new IdArticulo(articuloVO.getCodigo(), deposito));
    			manager.persist(articulo);
    		}
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getStackTrace());
    	}
    }
    
    @SuppressWarnings("unchecked")
	private Articulo buscarArticulo(int codigo){
    	List<Articulo> articulos = manager.createQuery("SELECT a FROM Articulo a WHERE a.id.codigo =:c").setParameter("c", codigo).getResultList();
    	if(articulos.size() == 0) {
    		return null;
    	}
    	return articulos.get(0);
    }
    
    private Deposito buscarDeposito(String nombre){
    	Deposito deposito = manager.find(Deposito.class, nombre);
    	return deposito;
    }
}
