package tpo.clientes.auditoria.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2014-11-08T15:15:39.918-03:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebServiceClient(name = "WSAgregarInformeAuditoriaBeanService", 
                  wsdlLocation = "http://172.16.164.32:8080/TPO_Logistica_Y_MonitoreoEJB/WSAgregarInformeAuditoriaBean?wsdl",
                  targetNamespace = "http://webServices/") 
public class WSAgregarInformeAuditoriaBeanService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webServices/", "WSAgregarInformeAuditoriaBeanService");
    public final static QName WSAgregarInformeAuditoriaBeanPort = new QName("http://webServices/", "WSAgregarInformeAuditoriaBeanPort");
    static {
        URL url = null;
        try {
            url = new URL("http://172.16.164.32:8080/TPO_Logistica_Y_MonitoreoEJB/WSAgregarInformeAuditoriaBean?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSAgregarInformeAuditoriaBeanService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://172.16.164.32:8080/TPO_Logistica_Y_MonitoreoEJB/WSAgregarInformeAuditoriaBean?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSAgregarInformeAuditoriaBeanService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSAgregarInformeAuditoriaBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSAgregarInformeAuditoriaBeanService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSAgregarInformeAuditoriaBeanService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSAgregarInformeAuditoriaBeanService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSAgregarInformeAuditoriaBeanService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns WSAgregarInformeAuditoriaBean
     */
    @WebEndpoint(name = "WSAgregarInformeAuditoriaBeanPort")
    public WSAgregarInformeAuditoriaBean getWSAgregarInformeAuditoriaBeanPort() {
        return super.getPort(WSAgregarInformeAuditoriaBeanPort, WSAgregarInformeAuditoriaBean.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSAgregarInformeAuditoriaBean
     */
    @WebEndpoint(name = "WSAgregarInformeAuditoriaBeanPort")
    public WSAgregarInformeAuditoriaBean getWSAgregarInformeAuditoriaBeanPort(WebServiceFeature... features) {
        return super.getPort(WSAgregarInformeAuditoriaBeanPort, WSAgregarInformeAuditoriaBean.class, features);
    }

}
