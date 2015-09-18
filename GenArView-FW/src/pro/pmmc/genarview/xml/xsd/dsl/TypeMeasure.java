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
 * <p>Clase Java para TypeMeasure complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeMeasure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measureData" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasureData"/>
 *         &lt;element name="measureMap" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasureMap" maxOccurs="unbounded"/>
 *         &lt;element name="measureMapComposite" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasureMapComposite" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeMeasure", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "measureData",
    "measureMap",
    "measureMapComposite"
})
public class TypeMeasure {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected TypeMeasureData measureData;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected List<TypeMeasureMap> measureMap;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeMeasureMapComposite> measureMapComposite;

    /**
     * Obtiene el valor de la propiedad measureData.
     * 
     * @return
     *     possible object is
     *     {@link TypeMeasureData }
     *     
     */
    public TypeMeasureData getMeasureData() {
        return measureData;
    }

    /**
     * Define el valor de la propiedad measureData.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeMeasureData }
     *     
     */
    public void setMeasureData(TypeMeasureData value) {
        this.measureData = value;
    }

    /**
     * Gets the value of the measureMap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measureMap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasureMap().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMeasureMap }
     * 
     * 
     */
    public List<TypeMeasureMap> getMeasureMap() {
        if (measureMap == null) {
            measureMap = new ArrayList<TypeMeasureMap>();
        }
        return this.measureMap;
    }

    /**
     * Gets the value of the measureMapComposite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measureMapComposite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasureMapComposite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMeasureMapComposite }
     * 
     * 
     */
    public List<TypeMeasureMapComposite> getMeasureMapComposite() {
        if (measureMapComposite == null) {
            measureMapComposite = new ArrayList<TypeMeasureMapComposite>();
        }
        return this.measureMapComposite;
    }

}
