package pro.pmmc.genarview.xml.impl.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pro.pmmc.genarview.xml.xsd.dsl.Dsl;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintExp;

public class TestXmlDsl {

	public static void main(String[] args) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Dsl.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(
					"C:\\Users\\paul\\workspace-luna-jee\\GenArView-FW\\src\\genarview-dsl-1.0.xml");
			Dsl genarview = (Dsl) jaxbUnmarshaller.unmarshal(XMLfile);
			
		} catch (JAXBException e) {
			// some exception occured
			e.printStackTrace();
		}
	}
}
