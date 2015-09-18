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
 * <p>Clase Java para TypeGraph complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeGraph">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="glabel" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeGLabel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="gupdate" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="grun" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeGraph", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "glabel",
    "gupdate",
    "grun"
})
public class TypeGraph {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeGLabel> glabel;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<String> gupdate;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<String> grun;

    /**
     * Gets the value of the glabel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the glabel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGlabel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeGLabel }
     * 
     * 
     */
    public List<TypeGLabel> getGlabel() {
        if (glabel == null) {
            glabel = new ArrayList<TypeGLabel>();
        }
        return this.glabel;
    }

    /**
     * Gets the value of the gupdate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gupdate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGupdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGupdate() {
        if (gupdate == null) {
            gupdate = new ArrayList<String>();
        }
        return this.gupdate;
    }

    /**
     * Gets the value of the grun property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grun property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGrun().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGrun() {
        if (grun == null) {
            grun = new ArrayList<String>();
        }
        return this.grun;
    }

}
