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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypeMeasureMap complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeMeasureMap">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFunction">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formula" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFormula" minOccurs="0"/>
 *         &lt;element name="reducer" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasureReduce" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeMeasureMap", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "key",
    "formula",
    "reducer"
})
public class TypeMeasureMap
    extends TypeFunction
{

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String key;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeFormula formula;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeMeasureReduce> reducer;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Obtiene el valor de la propiedad key.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Define el valor de la propiedad key.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Obtiene el valor de la propiedad formula.
     * 
     * @return
     *     possible object is
     *     {@link TypeFormula }
     *     
     */
    public TypeFormula getFormula() {
        return formula;
    }

    /**
     * Define el valor de la propiedad formula.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFormula }
     *     
     */
    public void setFormula(TypeFormula value) {
        this.formula = value;
    }

    /**
     * Gets the value of the reducer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reducer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReducer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMeasureReduce }
     * 
     * 
     */
    public List<TypeMeasureReduce> getReducer() {
        if (reducer == null) {
            reducer = new ArrayList<TypeMeasureReduce>();
        }
        return this.reducer;
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
