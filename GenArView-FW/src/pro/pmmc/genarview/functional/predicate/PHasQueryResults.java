package pro.pmmc.genarview.functional.predicate;

import java.util.List;

import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PHasQueryResults extends PTypeHintPlain<Node> {

	public PHasQueryResults(TypeHintPlain hint) {
		super(hint);
	}

	@Override
	public boolean test(Node n) {
		GraphUtil graphUtil = new GraphUtil();
		Node node = graphUtil.querySingleByPath(n.toString(), ConstantsFw.N_TYPE_TYPE);
		
		final String query = values.get("query");
		List lst = graphUtil.query(query);
		
		return lst != null && !lst.isEmpty();
	}

	
}
