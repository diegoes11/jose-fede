
package tpo.clientes.portalweb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tpo.clientes.portalweb package. 
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

    private final static QName _ProcesarVentaResponse_QNAME = new QName("http://soap/", "procesarVentaResponse");
    private final static QName _ProcesarVenta_QNAME = new QName("http://soap/", "procesarVenta");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tpo.clientes.portalweb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcesarVenta }
     * 
     */
    public ProcesarVenta createProcesarVenta() {
        return new ProcesarVenta();
    }

    /**
     * Create an instance of {@link ProcesarVentaResponse }
     * 
     */
    public ProcesarVentaResponse createProcesarVentaResponse() {
        return new ProcesarVentaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarVentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "procesarVentaResponse")
    public JAXBElement<ProcesarVentaResponse> createProcesarVentaResponse(ProcesarVentaResponse value) {
        return new JAXBElement<ProcesarVentaResponse>(_ProcesarVentaResponse_QNAME, ProcesarVentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarVenta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "procesarVenta")
    public JAXBElement<ProcesarVenta> createProcesarVenta(ProcesarVenta value) {
        return new JAXBElement<ProcesarVenta>(_ProcesarVenta_QNAME, ProcesarVenta.class, null, value);
    }

}
