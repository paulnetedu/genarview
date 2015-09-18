package pro.pmmc.genarview.visitor;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public abstract class AvNodeTypeNew extends AvNodeType {

	public AvNodeTypeNew() {
		
	}
	
	public AvNodeTypeNew(String path) {
		super(path);
	}
	
	public void processTypeName(String packName, String typeName, boolean isInterface, boolean isNested) {
		final String fullName = packName + "." + typeName;
		//System.out.println(fullName);
		int level = StringUtils.countMatches(fullName, ".");
		if (fullName.isEmpty()) {
			level--;
		}
		//if (true) {return;}
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Node node = GraphUtil.getGraphService().createNode();
		node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_TYPE));
		if (isInterface) {
			node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_INTERFACE));
		}
		node.addLabel(DynamicLabel.label((isNested) ? ConstantsFw.N_TYPE_DECLARATION_NESTED
				: ConstantsFw.N_TYPE_DECLARATION_TOP));
		node.setProperty("name", typeName);
		node.setProperty("fullName", fullName); 
		node.setProperty("level", level);
		node.setProperty("path", path);
		tx.success();
		tx.close();
		mapNodeTypes.put(fullName, node);
	}
}
