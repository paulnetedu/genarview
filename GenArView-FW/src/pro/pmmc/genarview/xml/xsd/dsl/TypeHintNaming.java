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
 * <p>Clase Java para TypeHintNaming complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeHintNaming">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeCompTyper">
 *       &lt;sequence>
 *         &lt;element name="pack" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="word" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeWord" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeHintNaming", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "pack",
    "word"
})
public class TypeHintNaming
    extends TypeCompTyper
{

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String pack;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected List<TypeWord> word;

    /**
     * Obtiene el valor de la propiedad pack.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPack() {
        return pack;
    }

    /**
     * Define el valor de la propiedad pack.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPack(String value) {
        this.pack = value;
    }

    /**
     * Gets the value of the word property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the word property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeWord }
     * 
     * 
     */
    public List<TypeWord> getWord() {
        if (word == null) {
            word = new ArrayList<TypeWord>();
        }
        return this.word;
    }

}
