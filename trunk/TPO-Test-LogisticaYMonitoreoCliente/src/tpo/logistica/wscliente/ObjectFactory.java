
package tpo.logistica.wscliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tpo.logistica.wscliente package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RecepcionOrdenDeDespachoResponse_QNAME = new QName("http://ws.despacho.tpo/", "recepcionOrdenDeDespachoResponse");
    private final static QName _RecepcionOrdenDeDespacho_QNAME = new QName("http://ws.despacho.tpo/", "recepcionOrdenDeDespacho");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tpo.logistica.wscliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecepcionOrdenDeDespacho }
     * 
     */
    public RecepcionOrdenDeDespacho createRecepcionOrdenDeDespacho() {
        return new RecepcionOrdenDeDespacho();
    }

    /**
     * Create an instance of {@link RecepcionOrdenDeDespachoResponse }
     * 
     */
    public RecepcionOrdenDeDespachoResponse createRecepcionOrdenDeDespachoResponse() {
        return new RecepcionOrdenDeDespachoResponse();
    }

    /**
     * Create an instance of {@link VoOrdenDeDespacho }
     * 
     */
    public VoOrdenDeDespacho createVoOrdenDeDespacho() {
        return new VoOrdenDeDespacho();
    }

    /**
     * Create an instance of {@link VoDetalleOrdenDeDespacho }
     * 
     */
    public VoDetalleOrdenDeDespacho createVoDetalleOrdenDeDespacho() {
        return new VoDetalleOrdenDeDespacho();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionOrdenDeDespachoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.despacho.tpo/", name = "recepcionOrdenDeDespachoResponse")
    public JAXBElement<RecepcionOrdenDeDespachoResponse> createRecepcionOrdenDeDespachoResponse(RecepcionOrdenDeDespachoResponse value) {
        return new JAXBElement<RecepcionOrdenDeDespachoResponse>(_RecepcionOrdenDeDespachoResponse_QNAME, RecepcionOrdenDeDespachoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionOrdenDeDespacho }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.despacho.tpo/", name = "recepcionOrdenDeDespacho")
    public JAXBElement<RecepcionOrdenDeDespacho> createRecepcionOrdenDeDespacho(RecepcionOrdenDeDespacho value) {
        return new JAXBElement<RecepcionOrdenDeDespacho>(_RecepcionOrdenDeDespacho_QNAME, RecepcionOrdenDeDespacho.class, null, value);
    }

}
