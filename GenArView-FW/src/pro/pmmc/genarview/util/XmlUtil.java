package pro.pmmc.genarview.util;

import java.io.IOException; 
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pro.pmmc.genarview.exception.ValidatorXPathExpressionException;
import pro.pmmc.genarview.exception.ValidatorXmlParsingException;

public class XmlUtil {

	public NodeList getNodesByTag(Path path, String tag) {
		NodeList nl = null;
		Document doc = null;
		Node node = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(path.toString());
			nl = doc.getElementsByTagName(tag);
		} catch (ParserConfigurationException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (SAXException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (IOException e) {
			throw new ValidatorXmlParsingException(e);
		}
		return nl;
	}
	
	public NodeList getNodesByXPath(Path path, String xpathExp) {
		NodeList nl = null;
		Document doc = null;
		Node node = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// avoid connect to DTD for validation
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(path.toString());
			XPath xPath =  XPathFactory.newInstance().newXPath();
			XPathExpression exp = xPath.compile(xpathExp);
			nl = (NodeList) exp.evaluate(doc, XPathConstants.NODESET);
		} catch (ParserConfigurationException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (SAXException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (IOException e) {
			System.out.println("Exception -------------");
			e.printStackTrace();
			throw new ValidatorXmlParsingException(e);
		} catch (XPathExpressionException e) {
			throw new ValidatorXPathExpressionException(e);
		}
		return nl;
	}
	
	public List<Node> getNodesByXPaths(Path path, List<String> xpathExps) {
		List<Node> lstNode = new ArrayList<Node>();
		Document doc = null;
		Node node = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(path.toString());
			XPath xPath =  XPathFactory.newInstance().newXPath();
			XPathExpression exp = null;
			NodeList nl = null;
			for (int i = 0; i < xpathExps.size(); i++) {
				exp = xPath.compile(xpathExps.get(i));
				nl = (NodeList) exp.evaluate(doc, XPathConstants.NODESET);
				for (int j = 0; j < nl.getLength(); j++) {
					lstNode.add(nl.item(j));
				}
			}
		} catch (ParserConfigurationException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (SAXException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (IOException e) {
			throw new ValidatorXmlParsingException(e);
		} catch (XPathExpressionException e) {
			throw new ValidatorXPathExpressionException(e);
		}
		return lstNode;
	}
}
