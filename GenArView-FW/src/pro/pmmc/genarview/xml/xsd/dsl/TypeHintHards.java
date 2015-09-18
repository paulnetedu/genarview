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
 * <p>Clase Java para TypeHintHards complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeHintHards">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hintSource" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintSource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hintDescriptor" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeHintHards", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "hintSource",
    "hintDescriptor"
})
public class TypeHintHards {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeHintSource> hintSource;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeHintDescriptor> hintDescriptor;

    /**
     * Gets the value of the hintSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hintSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHintSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeHintSource }
     * 
     * 
     */
    public List<TypeHintSource> getHintSource() {
        if (hintSource == null) {
            hintSource = new ArrayList<TypeHintSource>();
        }
        return this.hintSource;
    }

    /**
     * Gets the value of the hintDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hintDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHintDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeHintDescriptor }
     * 
     * 
     */
    public List<TypeHintDescriptor> getHintDescriptor() {
        if (hintDescriptor == null) {
            hintDescriptor = new ArrayList<TypeHintDescriptor>();
        }
        return this.hintDescriptor;
    }

}
