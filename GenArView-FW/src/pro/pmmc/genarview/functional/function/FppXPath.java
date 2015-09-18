package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.w3c.dom.Node;

import pro.pmmc.genarview.util.PathsUtil;
import pro.pmmc.genarview.util.XmlUtil;

public class FppXPath implements Function<Path, Stream<Path>> {

	/**
	 * eg .java
	 */
	private String suffix;
	
	private List<String> xpathExps;
	
	public FppXPath(String suffix, List<String> xpathExps) {
		this.suffix = suffix;
		this.xpathExps = xpathExps;
	}
	
	@Override
	public Stream<Path> apply(Path xmlPath) {
		Set<Path> setPath = new HashSet<Path>(); 
		XmlUtil xmlUtil = new XmlUtil();
		List<Node> lstNode = xmlUtil.getNodesByXPaths(xmlPath, xpathExps);
		String text = null;
		PathsUtil pathUtil = PathsUtil.getLastPathsUtil();
		Path path = null;
		for (Node node : lstNode) {
			text = node.getTextContent();
			path = pathUtil.getPathFromSources(text, ".", suffix);
			if (path != null) {
				setPath.add(path);
			}
		}
		return setPath.stream();
	}

}
