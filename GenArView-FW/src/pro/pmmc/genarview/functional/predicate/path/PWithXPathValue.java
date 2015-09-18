package pro.pmmc.genarview.functional.predicate.path;

import java.nio.file.Path;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.XmlUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithXPathValue extends PTypeHintPlain<Path> {

	public PWithXPathValue(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Path path) {
		final String xpathExp = values.get("xpath");
		NodeList nodes = (new XmlUtil()).getNodesByXPath(path, xpathExp);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeValue().matches(values.get("xpathValue"))) {
				return true;
			}
		}
		return false;
	}

	
}
