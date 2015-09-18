package pro.pmmc.genarview.functional.predicate.path;

import java.nio.file.Path;

import org.w3c.dom.NodeList;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.XmlUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithTag extends PTypeHintPlain<Path> {

	public PWithTag(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Path path) {
		final String tagName = values.get("tag");
		NodeList nodeList = (new XmlUtil()).getNodesByTag(path, tagName);
		return nodeList.getLength() > 0;
	}

}
