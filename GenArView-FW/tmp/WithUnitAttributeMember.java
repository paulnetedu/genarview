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

import pro.pmmc.genarview.function.PredicateAnd;
import pro.pmmc.genarview.function.PredicateTypeFeature;
import pro.pmmc.genarview.function.PredicateUnitAttributeMember;


/**
 * <p>Clase Java para WithUnitAttributeMember complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WithUnitAttributeMember">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeFeature">
 *       &lt;sequence>
 *         &lt;element name="unitAttributeMemberId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithUnitAttributeMember", propOrder = {
    "unitAttributeMemberId"
})
public class WithUnitAttributeMember
    extends TypeFeature
{
	public PredicateTypeFeature<WithUnitAttributeMember> createPredicateTypeFeature() {
		return new PredicateUnitAttributeMember(this);
	}

    @XmlElement(required = true)
    protected String unitAttributeMemberId;

    /**
     * Obtiene el valor de la propiedad unitAttributeMemberId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitAttributeMemberId() {
        return unitAttributeMemberId;
    }

    /**
     * Define el valor de la propiedad unitAttributeMemberId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitAttributeMemberId(String value) {
        this.unitAttributeMemberId = value;
    }

}
