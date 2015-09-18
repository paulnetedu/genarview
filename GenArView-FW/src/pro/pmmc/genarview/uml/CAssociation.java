package pro.pmmc.genarview.uml;

import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.util.ConstantsFw;

public class CAssociation extends CRelation {

	@Override
	public void accept(Class c, Node nodeTarget) {
		String fullName = nodeTarget.getProperty("fullName").toString();
		Class ctarget = (Class) CacheUtil.getCache().getAux("UMLClass", fullName);
		c.createAssociation(true, AggregationKind.NONE_LITERAL, "", 1, 1, ctarget, false, AggregationKind.NONE_LITERAL, "", 1, 1);
	}

	@Override
	public String getRelationshipType() {
		return ConstantsFw.N_REL_ASSOCIATION;
	}
}
