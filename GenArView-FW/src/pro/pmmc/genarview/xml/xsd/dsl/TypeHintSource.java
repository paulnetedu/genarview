//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.08.07 a las 12:38:07 PM COT 
//


package pro.pmmc.genarview.xml.xsd.dsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypeHintSource complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeHintSource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintExp">
 *       &lt;sequence>
 *         &lt;element name="fnn" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFunction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeHintSource", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "fnn"
})
public class TypeHintSource
    extends TypeHintExp
{

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeFunction fnn;

    /**
     * Obtiene el valor de la propiedad fnn.
     * 
     * @return
     *     possible object is
     *     {@link TypeFunction }
     *     
     */
    public TypeFunction getFnn() {
        return fnn;
    }

    /**
     * Define el valor de la propiedad fnn.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFunction }
     *     
     */
    public void setFnn(TypeFunction value) {
        this.fnn = value;
    }

}
