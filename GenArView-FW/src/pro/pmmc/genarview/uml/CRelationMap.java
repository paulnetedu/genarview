package pro.pmmc.genarview.uml;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import pro.pmmc.genarview.util.SourceUtil;
import pro.pmmc.genarview.visitor.AstVisitorRelation;

public class CRelationMap implements Consumer<Path> {

	private Map<String, Set<String>> mapInheritance = new HashMap<String, Set<String>>();
	private Map<String, Set<String>> mapRealization = new HashMap<String, Set<String>>();
	private Map<String, Set<String>> mapAssociation = new HashMap<String, Set<String>>();
	private Map<String, Set<String>> mapDependency = new HashMap<String, Set<String>>();
	
	private Map<String, String> mapTypes;
	
	public CRelationMap(Map<String, String> mapTypes) {
		this.mapTypes = mapTypes;
	}

	@Override
	public void accept(Path path) {
		AstVisitorRelation v = SourceUtil.visitRelationsFromPath(path);
		mapInheritance.putAll(v.getMapInheritance());
		mapRealization.putAll(v.getMapRealization());
		mapAssociation.putAll(v.getMapAssociation());
		mapDependency.putAll(v.getMapDependency());
	}

	public Map<String, Set<String>> getMapInheritance() {
		return mapInheritance;
	}

	public Map<String, Set<String>> getMapRealization() {
		return mapRealization;
	}

	public Map<String, Set<String>> getMapAssociation() {
		return mapAssociation;
	}

	public Map<String, Set<String>> getMapDependency() {
		return mapDependency;
	}

	
}
