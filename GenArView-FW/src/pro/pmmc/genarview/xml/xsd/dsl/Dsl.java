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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lookFor" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeLookFor" minOccurs="0"/>
 *         &lt;element name="hintHards" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintHards" minOccurs="0"/>
 *         &lt;element name="hintSofts" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintSofts" minOccurs="0"/>
 *         &lt;element name="queryCompTyper" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeQueryCompTyper" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="graph" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeGraph" minOccurs="0"/>
 *         &lt;element name="measures" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasures" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lookFor",
    "hintHards",
    "hintSofts",
    "queryCompTyper",
    "graph",
    "measures"
})
@XmlRootElement(name = "dsl", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
public class Dsl {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeLookFor lookFor;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeHintHards hintHards;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeHintSofts hintSofts;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeQueryCompTyper> queryCompTyper;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeGraph graph;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeMeasures measures;

    /**
     * Obtiene el valor de la propiedad lookFor.
     * 
     * @return
     *     possible object is
     *     {@link TypeLookFor }
     *     
     */
    public TypeLookFor getLookFor() {
        return lookFor;
    }

    /**
     * Define el valor de la propiedad lookFor.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeLookFor }
     *     
     */
    public void setLookFor(TypeLookFor value) {
        this.lookFor = value;
    }

    /**
     * Obtiene el valor de la propiedad hintHards.
     * 
     * @return
     *     possible object is
     *     {@link TypeHintHards }
     *     
     */
    public TypeHintHards getHintHards() {
        return hintHards;
    }

    /**
     * Define el valor de la propiedad hintHards.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeHintHards }
     *     
     */
    public void setHintHards(TypeHintHards value) {
        this.hintHards = value;
    }

    /**
     * Obtiene el valor de la propiedad hintSofts.
     * 
     * @return
     *     possible object is
     *     {@link TypeHintSofts }
     *     
     */
    public TypeHintSofts getHintSofts() {
        return hintSofts;
    }

    /**
     * Define el valor de la propiedad hintSofts.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeHintSofts }
     *     
     */
    public void setHintSofts(TypeHintSofts value) {
        this.hintSofts = value;
    }

    /**
     * Gets the value of the queryCompTyper property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryCompTyper property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryCompTyper().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeQueryCompTyper }
     * 
     * 
     */
    public List<TypeQueryCompTyper> getQueryCompTyper() {
        if (queryCompTyper == null) {
            queryCompTyper = new ArrayList<TypeQueryCompTyper>();
        }
        return this.queryCompTyper;
    }

    /**
     * Obtiene el valor de la propiedad graph.
     * 
     * @return
     *     possible object is
     *     {@link TypeGraph }
     *     
     */
    public TypeGraph getGraph() {
        return graph;
    }

    /**
     * Define el valor de la propiedad graph.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeGraph }
     *     
     */
    public void setGraph(TypeGraph value) {
        this.graph = value;
    }

    /**
     * Obtiene el valor de la propiedad measures.
     * 
     * @return
     *     possible object is
     *     {@link TypeMeasures }
     *     
     */
    public TypeMeasures getMeasures() {
        return measures;
    }

    /**
     * Define el valor de la propiedad measures.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeMeasures }
     *     
     */
    public void setMeasures(TypeMeasures value) {
        this.measures = value;
    }

}
