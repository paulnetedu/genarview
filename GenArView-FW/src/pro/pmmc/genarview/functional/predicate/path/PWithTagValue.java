package pro.pmmc.genarview.functional.predicate.path;

import java.nio.file.Path;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.XmlUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithTagValue extends PTypeHintPlain<Path> {

	public PWithTagValue(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Path path) {
		final String tagName = values.get("tag");
		NodeList nodeList = (new XmlUtil()).getNodesByTag(path, tagName);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getTextContent().matches(values.get("tagValue"))) {
				return true;
			}
		}
		return false;
	}

}
