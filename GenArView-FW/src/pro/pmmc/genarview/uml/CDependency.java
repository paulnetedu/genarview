package pro.pmmc.genarview.uml;

import java.util.Map;

import org.eclipse.uml2.uml.Class;
import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.util.ConstantsFw;

public class CDependency extends CRelation {

	@Override
	public void accept(Class c, Node nodeTarget) {
		String fullName = nodeTarget.getProperty("fullName").toString();
		Class ctarget = (Class) CacheUtil.getCache().getAux("UMLClass", fullName);
		c.createDependency(ctarget);
	}

	@Override
	public String getRelationshipType() {
		return ConstantsFw.N_REL_DEPENDENCY;
	}

}
