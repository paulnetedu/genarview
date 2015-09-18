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
 * <p>Clase Java para TypeMeasureMapComposite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeMeasureMapComposite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="measureMapName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="2"/>
 *         &lt;element name="reducer" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasureReduce" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeMeasureMapComposite", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "key",
    "formula",
    "measureMapName",
    "reducer"
})
public class TypeMeasureMapComposite {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String key;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String formula;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected List<String> measureMapName;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeMeasureReduce reducer;
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
     *     {@link String }
     *     
     */
    public String getFormula() {
        return formula;
    }

    /**
     * Define el valor de la propiedad formula.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormula(String value) {
        this.formula = value;
    }

    /**
     * Gets the value of the measureMapName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measureMapName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasureMapName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMeasureMapName() {
        if (measureMapName == null) {
            measureMapName = new ArrayList<String>();
        }
        return this.measureMapName;
    }

    /**
     * Obtiene el valor de la propiedad reducer.
     * 
     * @return
     *     possible object is
     *     {@link TypeMeasureReduce }
     *     
     */
    public TypeMeasureReduce getReducer() {
        return reducer;
    }

    /**
     * Define el valor de la propiedad reducer.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeMeasureReduce }
     *     
     */
    public void setReducer(TypeMeasureReduce value) {
        this.reducer = value;
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
