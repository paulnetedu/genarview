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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypeHintSet complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeHintSet">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintMember">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="hintAnd" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintAnd"/>
 *         &lt;element name="hintOr" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintOr"/>
 *         &lt;element name="hint" type="{http://www.genarview.pmmc.pro/genarview-dsl-1.0}TypeHintPlain"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeHintSet", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", propOrder = {
    "hintAndOrHintOrOrHint"
})
@XmlSeeAlso({
    TypeHintOr.class,
    TypeHintAnd.class
})
public class TypeHintSet
    extends TypeHintMember
{

    @XmlElements({
        @XmlElement(name = "hintAnd", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", type = TypeHintAnd.class),
        @XmlElement(name = "hintOr", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", type = TypeHintOr.class),
        @XmlElement(name = "hint", namespace = "http://www.genarview.pmmc.pro/genarview-dsl-1.0", type = TypeHintPlain.class)
    })
    protected List<TypeHintMember> hintAndOrHintOrOrHint;

    /**
     * Gets the value of the hintAndOrHintOrOrHint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hintAndOrHintOrOrHint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHintAndOrHintOrOrHint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeHintAnd }
     * {@link TypeHintOr }
     * {@link TypeHintPlain }
     * 
     * 
     */
    public List<TypeHintMember> getHintAndOrHintOrOrHint() {
        if (hintAndOrHintOrOrHint == null) {
            hintAndOrHintOrOrHint = new ArrayList<TypeHintMember>();
        }
        return this.hintAndOrHintOrOrHint;
    }

}
