
package tpo.logistica.wscliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ordenDeDespachoVO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ordenDeDespachoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detallesOrdenDeDespachoVO" type="{http://ws.despacho.tpo/}detalleOrdenDeDespachoVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="idOrdenDeDespacho" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreLogisticaYMonitoreo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrePortalWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ordenDeDespachoVO", propOrder = {
    "detallesOrdenDeDespachoVO",
    "idOrdenDeDespacho",
    "nombreLogisticaYMonitoreo",
    "nombrePortalWeb"
})
public class OrdenDeDespachoVO {

    @XmlElement(nillable = true)
    protected List<DetalleOrdenDeDespachoVO> detallesOrdenDeDespachoVO;
    protected int idOrdenDeDespacho;
    protected String nombreLogisticaYMonitoreo;
    protected String nombrePortalWeb;

    /**
     * Gets the value of the detallesOrdenDeDespachoVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detallesOrdenDeDespachoVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetallesOrdenDeDespachoVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleOrdenDeDespachoVO }
     * 
     * 
     */
    public List<DetalleOrdenDeDespachoVO> getDetallesOrdenDeDespachoVO() {
        if (detallesOrdenDeDespachoVO == null) {
            detallesOrdenDeDespachoVO = new ArrayList<DetalleOrdenDeDespachoVO>();
        }
        return this.detallesOrdenDeDespachoVO;
    }

    /**
     * Obtiene el valor de la propiedad idOrdenDeDespacho.
     * 
     */
    public int getIdOrdenDeDespacho() {
        return idOrdenDeDespacho;
    }

    /**
     * Define el valor de la propiedad idOrdenDeDespacho.
     * 
     */
    public void setIdOrdenDeDespacho(int value) {
        this.idOrdenDeDespacho = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreLogisticaYMonitoreo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreLogisticaYMonitoreo() {
        return nombreLogisticaYMonitoreo;
    }

    /**
     * Define el valor de la propiedad nombreLogisticaYMonitoreo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreLogisticaYMonitoreo(String value) {
        this.nombreLogisticaYMonitoreo = value;
    }

    /**
     * Obtiene el valor de la propiedad nombrePortalWeb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePortalWeb() {
        return nombrePortalWeb;
    }

    /**
     * Define el valor de la propiedad nombrePortalWeb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePortalWeb(String value) {
        this.nombrePortalWeb = value;
    }

}
