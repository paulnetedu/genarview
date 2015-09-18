//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.08.07 a las 12:38:07 PM COT 
//


package pro.pmmc.genarview.xml.xsd.dsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypeHintExp complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeHintExp">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeCompTyper">
 *       &lt;sequence minOccurs="0">
 *         &lt;choice>
 *           &lt;element name="hintAnd" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintAnd"/>
 *           &lt;element name="hintOr" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintOr"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeHintExp", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "hintAnd",
    "hintOr"
})
@XmlSeeAlso({
    TypeHintDescriptor.class,
    TypeHintSource.class
})
public class TypeHintExp
    extends TypeCompTyper
{

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeHintAnd hintAnd;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeHintOr hintOr;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Obtiene el valor de la propiedad hintAnd.
     * 
     * @return
     *     possible object is
     *     {@link TypeHintAnd }
     *     
     */
    public TypeHintAnd getHintAnd() {
        return hintAnd;
    }

    /**
     * Define el valor de la propiedad hintAnd.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeHintAnd }
     *     
     */
    public void setHintAnd(TypeHintAnd value) {
        this.hintAnd = value;
    }

    /**
     * Obtiene el valor de la propiedad hintOr.
     * 
     * @return
     *     possible object is
     *     {@link TypeHintOr }
     *     
     */
    public TypeHintOr getHintOr() {
        return hintOr;
    }

    /**
     * Define el valor de la propiedad hintOr.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeHintOr }
     *     
     */
    public void setHintOr(TypeHintOr value) {
        this.hintOr = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
