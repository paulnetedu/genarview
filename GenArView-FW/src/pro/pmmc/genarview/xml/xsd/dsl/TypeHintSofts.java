//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
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
 * <p>Clase Java para TypeHintSofts complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeHintSofts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="hintNaming" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintNaming" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hintCustom" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintCustom" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeHintSofts", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "hintNaming",
    "hintCustom"
})
public class TypeHintSofts {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeHintNaming> hintNaming;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeHintCustom> hintCustom;

    /**
     * Gets the value of the hintNaming property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hintNaming property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHintNaming().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeHintNaming }
     * 
     * 
     */
    public List<TypeHintNaming> getHintNaming() {
        if (hintNaming == null) {
            hintNaming = new ArrayList<TypeHintNaming>();
        }
        return this.hintNaming;
    }

    /**
     * Gets the value of the hintCustom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hintCustom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHintCustom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeHintCustom }
     * 
     * 
     */
    public List<TypeHintCustom> getHintCustom() {
        if (hintCustom == null) {
            hintCustom = new ArrayList<TypeHintCustom>();
        }
        return this.hintCustom;
    }

}