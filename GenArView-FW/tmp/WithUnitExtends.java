//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2014.08.03 a las 08:19:35 AM COT 
//


package pro.pmmc.genarview.xml.xsd.dsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import pro.pmmc.genarview.function.PredicateTypeFeature;
import pro.pmmc.genarview.function.PredicateUnitExtends;


/**
 * <p>Clase Java para WithUnitExtends complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WithUnitExtends">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFeature">
 *       &lt;sequence>
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extendedUnitName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithUnitExtends", propOrder = {
    "unitId",
    "extendedUnitName"
})
public class WithUnitExtends
    extends TypeFeature
{

	public PredicateTypeFeature<WithUnitExtends> createPredicateTypeFeature() {
		return new PredicateUnitExtends(this);
	}
	
    @XmlElement(required = true)
    protected String unitId;
    @XmlElement(required = true)
    protected String extendedUnitName;

    /**
     * Obtiene el valor de la propiedad unitId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Define el valor de la propiedad unitId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitId(String value) {
        this.unitId = value;
    }

    /**
     * Obtiene el valor de la propiedad extendedUnitName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendedUnitName() {
        return extendedUnitName;
    }

    /**
     * Define el valor de la propiedad extendedUnitName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendedUnitName(String value) {
        this.extendedUnitName = value;
    }

}
