
package tpo.logistica.wscliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para voDetalleOrdenDeDespacho complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="voDetalleOrdenDeDespacho">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoArticulo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "voDetalleOrdenDeDespacho", propOrder = {
    "cantidad",
    "codigoArticulo"
})
public class VoDetalleOrdenDeDespacho {

    protected int cantidad;
    protected int codigoArticulo;

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoArticulo.
     * 
     */
    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    /**
     * Define el valor de la propiedad codigoArticulo.
     * 
     */
    public void setCodigoArticulo(int value) {
        this.codigoArticulo = value;
    }

}
