//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2014.08.03 a las 08:19:35 AM COT 
//


package pro.pmmc.genarview.xml.xsd.dsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import pro.pmmc.genarview.function.PredicateAnd;
import pro.pmmc.genarview.function.PredicateTypeFeature;
import pro.pmmc.genarview.function.PredicateXPathValue;


/**
 * <p>Clase Java para WithXPathValue complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WithXPathValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFeature">
 *       &lt;sequence>
 *         &lt;element name="xpathValueId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithXPathValue", propOrder = {
    "xpathValueId"
})
public class WithXPathValue
    extends TypeFeature
{
	public PredicateTypeFeature<WithXPathValue> createPredicateTypeFeature() {
		return new PredicateXPathValue(this);
	}

    @XmlElement(required = true)
    protected String xpathValueId;

    /**
     * Obtiene el valor de la propiedad xpathValueId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathValueId() {
        return xpathValueId;
    }

    /**
     * Define el valor de la propiedad xpathValueId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathValueId(String value) {
        this.xpathValueId = value;
    }

}
