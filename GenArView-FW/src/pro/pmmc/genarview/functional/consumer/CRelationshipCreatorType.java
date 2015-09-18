package pro.pmmc.genarview.functional.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public class CRelationshipCreatorType implements Consumer<Node> {

	private Map<String, Set<String>> map;
	
	private String relationType;
	
	public CRelationshipCreatorType(String relationType, Map<String, Set<String>> map) {
		this.relationType = relationType;
		this.map = map;
	}

	@Override
	public void accept(Node node) {
		
		final String fullName = (String) node.getProperty("fullName");
		GraphUtil gu = new GraphUtil();
		List<Node> lstT = null;
		Set<String> inheritance = map.get(fullName);
		if (inheritance != null) {
			for (String item : inheritance) {
				lstT = gu.queryByFullname(item, ConstantsFw.N_TYPE_TYPE);
				lstT.stream().forEach(nt -> node.createRelationshipTo(nt, () -> relationType));
			}
		}
		
	}

}
