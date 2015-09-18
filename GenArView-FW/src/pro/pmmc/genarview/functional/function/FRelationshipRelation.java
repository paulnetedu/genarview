package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.util.SourceUtil;
import pro.pmmc.genarview.visitor.AstVisitorRelation;

public class FRelationshipRelation implements
		Function<Path, List<Relationship>> {

	public FRelationshipRelation() {
		
	}

	@Override
	public List<Relationship> apply(Path path) {
		// System.out.println("VISREL " + path);
		AstVisitorRelation v = SourceUtil
				.visitRelationsFromPath(path);
		final List<Relationship> lstRel = new LinkedList<Relationship>();
		List<RelationInfo> lstRelInfo = new LinkedList<RelationInfo>();
		lstRelInfo.add(new RelationInfo(v.getMapAssociation(), ConstantsFw.N_REL_ASSOCIATION));
		lstRelInfo.add(new RelationInfo(v.getMapDependency(), ConstantsFw.N_REL_DEPENDENCY));
		lstRelInfo.add(new RelationInfo(v.getMapInheritance(), ConstantsFw.N_REL_GENERALIZATION));
		lstRelInfo.add(new RelationInfo(v.getMapRealization(), ConstantsFw.N_REL_REALIZATION));
		lstRelInfo.add(new RelationInfo(v.getMapAggregation(), ConstantsFw.N_REL_AGGREGATION));
		lstRelInfo.stream().forEach(ri -> lstRel.addAll(ri.runMap()));
		return lstRel;
	}

	public class RelationInfo {
		private Map<String, Set<String>> map;
		private String relationType;

		public RelationInfo(Map<String, Set<String>> map, String relationType) {
			this.map = map;
			this.relationType = relationType;
		}

		public List<Relationship> runMap() {
			GraphUtil graphUtil = new GraphUtil();
			List<Relationship> lstRel = new ArrayList<Relationship>();
			// TX MODIF
			Transaction tx = GraphUtil.getGraphService().beginTx();
			map.keySet()
				.forEach(
					k -> {
						List<Node> lstOrigin = null;
						lstOrigin = graphUtil
								.queryByFullname(k, ConstantsFw.N_TYPE_TYPE);
						lstOrigin.forEach(n -> {
							Set<String> setTarget = map.get(k);
							setTarget.forEach(t -> {
								List<Node> lstTarget = null;
								lstTarget = graphUtil
										.queryByFullname(t, ConstantsFw.N_TYPE_TYPE);
								lstTarget.forEach(nt -> {
									lstRel.add(n.createRelationshipTo(
											nt, () -> relationType));
								});
							});
						});
					});
			tx.success();
			tx.close();
			return lstRel;
		}
	};

}
