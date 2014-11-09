
package tpo.clientes.auditoria.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tpo.clientes.auditoria.ws package. 
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

    private final static QName _AgregarInformeAuditoria_QNAME = new QName("http://webServices/", "agregarInformeAuditoria");
    private final static QName _AgregarInformeAuditoriaResponse_QNAME = new QName("http://webServices/", "agregarInformeAuditoriaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tpo.clientes.auditoria.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgregarInformeAuditoria }
     * 
     */
    public AgregarInformeAuditoria createAgregarInformeAuditoria() {
        return new AgregarInformeAuditoria();
    }

    /**
     * Create an instance of {@link AgregarInformeAuditoriaResponse }
     * 
     */
    public AgregarInformeAuditoriaResponse createAgregarInformeAuditoriaResponse() {
        return new AgregarInformeAuditoriaResponse();
    }

    /**
     * Create an instance of {@link VoInformeAuditoria }
     * 
     */
    public VoInformeAuditoria createVoInformeAuditoria() {
        return new VoInformeAuditoria();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarInformeAuditoria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "agregarInformeAuditoria")
    public JAXBElement<AgregarInformeAuditoria> createAgregarInformeAuditoria(AgregarInformeAuditoria value) {
        return new JAXBElement<AgregarInformeAuditoria>(_AgregarInformeAuditoria_QNAME, AgregarInformeAuditoria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarInformeAuditoriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "agregarInformeAuditoriaResponse")
    public JAXBElement<AgregarInformeAuditoriaResponse> createAgregarInformeAuditoriaResponse(AgregarInformeAuditoriaResponse value) {
        return new JAXBElement<AgregarInformeAuditoriaResponse>(_AgregarInformeAuditoriaResponse_QNAME, AgregarInformeAuditoriaResponse.class, null, value);
    }

}
