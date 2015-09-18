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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Clase Java para TypeFormulaCondition complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeFormulaCondition">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="equalTo"/>
 *             &lt;enumeration value="lesserThan"/>
 *             &lt;enumeration value="greaterThan"/>
 *             &lt;enumeration value="notEqualTo"/>
 *             &lt;enumeration value="greaterEqualTo"/>
 *             &lt;enumeration value="lesserEqualTo"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="exp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="expVal" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeFormulaCondition", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "value"
})
public class TypeFormulaCondition {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "exp", required = true)
    protected String exp;
    @XmlAttribute(name = "expVal", required = true)
    protected String expVal;

    /**
     * Obtiene el valor de la propiedad value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad exp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExp() {
        return exp;
    }

    /**
     * Define el valor de la propiedad exp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExp(String value) {
        this.exp = value;
    }

    /**
     * Obtiene el valor de la propiedad expVal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpVal() {
        return expVal;
    }

    /**
     * Define el valor de la propiedad expVal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpVal(String value) {
        this.expVal = value;
    }

}
