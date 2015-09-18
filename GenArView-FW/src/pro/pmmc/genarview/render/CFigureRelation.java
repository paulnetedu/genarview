package pro.pmmc.genarview.render;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.dw.sketch.dwfigure.DwConnector;
import pro.pmmc.genarview.dw.sketch.dwfigure.DwFigure;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;

public abstract class CFigureRelation implements Consumer<DwFigure> {

	protected Map<String, DwFigure> mapFigure;
	
	protected List<DwConnector> lstConnector;
	
	public CFigureRelation(Map<String, DwFigure> mapFigure) {
		this.mapFigure = mapFigure;
		lstConnector = new ArrayList<DwConnector>();
	}

	public abstract void accept(DwFigure f, Node node);
	
	public abstract String getRelationshipType();

	@Override
	public void accept(DwFigure f) {
		String fullName = f.getFullName();
		GraphUtil gu = new GraphUtil();
		List<Node> lstNode =  gu.queryByFullname(fullName, ConstantsFw.N_TYPE_TYPE);
		// TX MODIF
		Transaction t = GraphUtil.getGraphService().beginTx();
		lstNode.forEach(n -> {
			Iterable<Relationship> it = n.getRelationships(() -> getRelationshipType(), Direction.OUTGOING);
			Iterator<Relationship> iter = it.iterator();
			while (iter.hasNext()) {
				Relationship r = iter.next();
				Node target = r.getEndNode();
				accept(f, target);
			}
			
		});
		t.success();
		t.close();
	}

	public Map<String, DwFigure> getMapFigure() {
		return mapFigure;
	}

	public void setMapFigure(Map<String, DwFigure> mapFigure) {
		this.mapFigure = mapFigure;
	}

	public List<DwConnector> getLstConnector() {
		return lstConnector;
	}

	public void setLstConnector(List<DwConnector> lstConnector) {
		this.lstConnector = lstConnector;
	}

}
