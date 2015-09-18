//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.08.07 a las 12:38:07 PM COT 
//


package pro.pmmc.genarview.xml.xsd.dsl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypeLookFor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeLookFor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadata" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMetadata" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="methodCall" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMethod" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeLookFor", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "metadata",
    "methodCall"
})
public class TypeLookFor {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeMetadata> metadata;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeMethod> methodCall;

    /**
     * Gets the value of the metadata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMetadata }
     * 
     * 
     */
    public List<TypeMetadata> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<TypeMetadata>();
        }
        return this.metadata;
    }

    /**
     * Gets the value of the methodCall property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the methodCall property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMethodCall().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMethod }
     * 
     * 
     */
    public List<TypeMethod> getMethodCall() {
        if (methodCall == null) {
            methodCall = new ArrayList<TypeMethod>();
        }
        return this.methodCall;
    }

}
