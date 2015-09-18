package pro.pmmc.genarview.functional.predicate.path;

import java.nio.file.Path;

import org.w3c.dom.NodeList;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.XmlUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithXPath extends PTypeHintPlain<Path> {

	public PWithXPath(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Path path) {
		final String xpathExp = values.get("xpath");
		NodeList nl = (new XmlUtil()).getNodesByXPath(path, xpathExp);
		return nl != null && nl.getLength() > 0;
	}

}
