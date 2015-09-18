package pro.pmmc.genarview.render;

import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.uml2.uml.Class;
import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.dw.sketch.dwfigure.DwConnector;
import pro.pmmc.genarview.dw.sketch.dwfigure.DwFigure;

public class CFigureDependency extends CFigureRelation {

	public CFigureDependency(Map<String, DwFigure> mapClass) {
		super(mapClass);
	}

	@Override
	public void accept(DwFigure f, Node nodeTarget) {
		String fullName = nodeTarget.getProperty("fullName").toString();
		DwFigure ftarget = mapFigure.get(fullName);
		DwConnector con = new DwConnector();
        con.setSourceDw(f);
        con.setTargetDw(ftarget);
        con.setTargetDecoration(new PolylineDecoration());
        con.setLineStyle(3);
        lstConnector.add(con);
	}

	@Override
	public String getRelationshipType() {
		return "USE";
	}

}
