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
 * <p>Clase Java para TypeFormula complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeFormula">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="var" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="condition" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFormulaCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeFormula", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "var",
    "condition",
    "exp"
})
public class TypeFormula {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected List<String> var;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeFormulaCondition> condition;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String exp;

    /**
     * Gets the value of the var property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the var property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVar() {
        if (var == null) {
            var = new ArrayList<String>();
        }
        return this.var;
    }

    /**
     * Gets the value of the condition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeFormulaCondition }
     * 
     * 
     */
    public List<TypeFormulaCondition> getCondition() {
        if (condition == null) {
            condition = new ArrayList<TypeFormulaCondition>();
        }
        return this.condition;
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

}
