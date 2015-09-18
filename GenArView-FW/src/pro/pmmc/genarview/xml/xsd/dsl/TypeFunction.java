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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypeFunction complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeFunction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}GroupImpl"/>
 *         &lt;element name="implements" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeLinkImplements" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extends" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeLinkExtends" minOccurs="0"/>
 *         &lt;element name="refs" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeLinkReferences" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeFunction", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "clazz",
    "arg",
    "args",
    "_implements",
    "_extends",
    "refs",
    "label"
})
@XmlSeeAlso({
    TypeMeasureMap.class,
    TypeMeasureReduce.class
})
public class TypeFunction {

    @XmlElement(name = "class", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", required = true)
    protected String clazz;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<String> arg;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeArgs> args;
    @XmlElement(name = "implements", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeLinkImplements> _implements;
    @XmlElement(name = "extends", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected TypeLinkExtends _extends;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<TypeLinkReferences> refs;
    @XmlElement(namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0")
    protected List<String> label;

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
     * Gets the value of the implements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the implements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImplements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeLinkImplements }
     * 
     * 
     */
    public List<TypeLinkImplements> getImplements() {
        if (_implements == null) {
            _implements = new ArrayList<TypeLinkImplements>();
        }
        return this._implements;
    }

    /**
     * Obtiene el valor de la propiedad extends.
     * 
     * @return
     *     possible object is
     *     {@link TypeLinkExtends }
     *     
     */
    public TypeLinkExtends getExtends() {
        return _extends;
    }

    /**
     * Define el valor de la propiedad extends.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeLinkExtends }
     *     
     */
    public void setExtends(TypeLinkExtends value) {
        this._extends = value;
    }

    /**
     * Gets the value of the refs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeLinkReferences }
     * 
     * 
     */
    public List<TypeLinkReferences> getRefs() {
        if (refs == null) {
            refs = new ArrayList<TypeLinkReferences>();
        }
        return this.refs;
    }

    /**
     * Gets the value of the label property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the label property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLabel() {
        if (label == null) {
            label = new ArrayList<String>();
        }
        return this.label;
    }

}
