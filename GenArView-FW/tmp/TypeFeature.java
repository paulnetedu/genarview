//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2014.08.03 a las 11:33:51 AM COT 
//


package pro.pmmc.genarview.xml.xsd.dsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import pro.pmmc.genarview.function.PredicateTypeFeature;


/**
 * <p>Clase Java para TypeFeature complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypeFeature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeFeature")
@XmlSeeAlso({
    WithUnit.class,
    WithUnitAttributeMember.class,
    WithFile.class,
    WithXPath.class,
    WithAnnotation.class,
    WithXPathValue.class,
    WithUnitDependency.class,
    WithClassImplements.class,
    WithAnnotationValue.class,
    WithAnnotationAttribute.class,
    WithImport.class,
    TypeFeatureExp.class,
    WithUnitExtends.class
})
public class TypeFeature {

	public PredicateTypeFeature<? extends TypeFeature> createPredicateTypeFeature() {return null;}
}
