package pro.pmmc.genarview.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pro.pmmc.genarview.xml.impl.XmlSchemaRecognizer;

public class XmlRecognizerDom implements XmlSchemaRecognizer {

	@Override
	public String recognizeXmlSchema(String filePath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(filePath);
			//get the root element
			Element docEle = dom.getDocumentElement();

			//get a nodelist of elements
			System.out.println("docEle.getTagName() " + docEle.getTagName());
			NodeList nl = docEle.getElementsByTagName("web-app");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					Element el = (Element)nl.item(i);
					System.out.println("Element" + el);
				}
			}

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		XmlRecognizerDom r = new XmlRecognizerDom();
		//r.recognizeXmlSchema("")
	}
}
