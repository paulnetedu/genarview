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
 * <p>Clase Java para TypeMeasureData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeMeasureData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;group ref="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}GroupQuery"/>
 *           &lt;group ref="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}GroupImpl"/>
 *         &lt;/choice>
 *         &lt;element name="measureData" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeMeasureData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="idField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="join" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeJoin" minOccurs="0"/>
 *         &lt;element name="fmap" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFunction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeMeasureData", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "query",
    "param",
    "clazz",
    "arg",
    "args",
    "measureData",
    "idField",
    "join",
    "fmap"
})
public class TypeMeasureData {

    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected String query;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeValue> param;
    @XmlElement(name = "class", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected String clazz;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<String> arg;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeArgs> args;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeMeasureData> measureData;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String idField;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeJoin join;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeFunction> fmap;

    /**
     * Obtiene el valor de la propiedad query.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuery() {
        return query;
    }

    /**
     * Define el valor de la propiedad query.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuery(String value) {
        this.query = value;
    }

    /**
     * Gets the value of the param property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the param property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeValue }
     * 
     * 
     */
    public List<TypeValue> getParam() {
        if (param == null) {
            param = new ArrayList<TypeValue>();
        }
        return this.param;
    }

    /**
     * Obtiene el valor de la propiedad clazz.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Define el valor de la propiedad clazz.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the arg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArg() {
        if (arg == null) {
            arg = new ArrayList<String>();
        }
        return this.arg;
    }

    /**
     * Gets the value of the args property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the args property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArgs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeArgs }
     * 
     * 
     */
    public List<TypeArgs> getArgs() {
        if (args == null) {
            args = new ArrayList<TypeArgs>();
        }
        return this.args;
    }

    /**
     * Gets the value of the measureData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measureData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasureData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMeasureData }
     * 
     * 
     */
    public List<TypeMeasureData> getMeasureData() {
        if (measureData == null) {
            measureData = new ArrayList<TypeMeasureData>();
        }
        return this.measureData;
    }

    /**
     * Obtiene el valor de la propiedad idField.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdField() {
        return idField;
    }

    /**
     * Define el valor de la propiedad idField.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdField(String value) {
        this.idField = value;
    }

    /**
     * Obtiene el valor de la propiedad join.
     * 
     * @return
     *     possible object is
     *     {@link TypeJoin }
     *     
     */
    public TypeJoin getJoin() {
        return join;
    }

    /**
     * Define el valor de la propiedad join.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeJoin }
     *     
     */
    public void setJoin(TypeJoin value) {
        this.join = value;
    }

    /**
     * Gets the value of the fmap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fmap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFmap().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeFunction }
     * 
     * 
     */
    public List<TypeFunction> getFmap() {
        if (fmap == null) {
            fmap = new ArrayList<TypeFunction>();
        }
        return this.fmap;
    }

}
