package pro.pmmc.genarview.dsl;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.exception.DslUnmarshalException;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.xml.xsd.dsl.Dsl;
import pro.pmmc.genarview.xml.xsd.dsl.TypeGraph;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintCustom;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintDescriptor;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintNaming;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintSource;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMeasure;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMetadata;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMethod;
import pro.pmmc.genarview.xml.xsd.dsl.TypeQueryCompTyper;

public class DslInformer {

	private static DslInformer singleton;
	
	//private Map<String, Map<String, String>> mapTypeItems;
	
	private Map<String, String> mapMethodCall;
	
	private Map<String, Node> mapComponentType;
	
	private Map<String, List<Node>> mapComponentTypeLst;
	
	private Map<String, TypeHintSource> mapTypeHintSource;
	
	private Map<String, TypeHintDescriptor> mapTypeHintDescriptor;
	
	private Map<String, TypeQueryCompTyper> mapTypeQueryCompTyper;
	
	private List<TypeHintNaming> lstTypeHintNaming;
	
	private List<TypeHintCustom> lstTypeHintCustom;

	private List<TypeMeasure> lstMeasure;
	
	private TypeGraph typeGraph;
	
	private DslInformer() {
		mapComponentType = new LinkedHashMap<String, Node>();
		mapComponentTypeLst = new LinkedHashMap<String, List<Node>>();
		setMapTypeHintSource(new LinkedHashMap<String, TypeHintSource>());
		setMapTypeHintDescriptor(new LinkedHashMap<String, TypeHintDescriptor>());
		setMapTypeQueryCompTyper(new LinkedHashMap<String, TypeQueryCompTyper>());
		setLstMeasure(new LinkedList<TypeMeasure>());
		mapMethodCall = new HashMap<String, String>();
	}
	
	public static DslInformer getInstance() {
		if (singleton == null) {
			singleton = new DslInformer();
		}
		return singleton;
	}
	
	public Collection<TypeHintSource> getTypeHintSource() {
		return mapTypeHintSource.values();
	}
	
	public Collection<TypeHintDescriptor> getTypeHintDescriptor() {
		return mapTypeHintDescriptor.values();
	}
	
	public void clear() {
		//mapTypeItems.clear();
		getMapTypeHintSource().clear();
		getMapTypeHintDescriptor().clear();
	}
	/*
	public TypeItem findResourceById(String id) {
		return mapTypeItems.get(id);
	}*/
	
	public void readDsl(String dslPath) {
		Dsl genarview = null;
		try {
			JAXBContext  jaxbContext = JAXBContext.newInstance(Dsl.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(dslPath);
			genarview = (Dsl) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new DslUnmarshalException(e.getMessage());
		}
		readDsl(genarview);
	}
	
	public void readDsl(Dsl dsl) {
		clear();
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Node node = null; 
		if (dsl.getLookFor() != null) {
			if (dsl.getLookFor().getMetadata() != null) {
				for (TypeMetadata md : dsl.getLookFor().getMetadata()) {
					String fullName = md.getValue();
					String level = md.getLevel();
					GraphUtil graphUtil = new GraphUtil(); 
					node = GraphUtil.getGraphService().createNode();
					node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_ANNOTATION));
					node.addLabel(DynamicLabel.label(ConstantsFw.N_ANNOT_LEVEL_PREFFIX + level));
					node.setProperty("name", fullName.substring(fullName.lastIndexOf('.') + 1));
					node.setProperty("fullName", fullName);
				}
			}
			if (dsl.getLookFor().getMethodCall() != null) {
				for (TypeMethod m : dsl.getLookFor().getMethodCall()) {
					String method = m.getValue();
					node = GraphUtil.getGraphService().createNode();
					node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_METHOD));
					node.setProperty("name", method);
					mapMethodCall.put(method, method);
				}
			}
		}
		List<Node> lstNode = null;
		for(TypeHintSource s : dsl.getHintHards().getHintSource()) {
			lstNode = new LinkedList<Node>();
			getMapTypeHintSource().put(s.getName(), s);
			for (String compType : s.getComponentType()) {
				lstNode.add(addNodeComponentType(compType));				
			}
			mapComponentTypeLst.put(s.getName(), lstNode);
		}
		for (TypeHintDescriptor s : dsl.getHintHards().getHintDescriptor()) {
			lstNode = new LinkedList<Node>();
			getMapTypeHintDescriptor().put(s.getName(), s);
			for (String compType : s.getComponentType()) {
				addNodeComponentType(compType);
			}
			mapComponentTypeLst.put(s.getName(), lstNode);
		}
		for (TypeQueryCompTyper s : dsl.getQueryCompTyper()) {
			lstNode = new LinkedList<Node>();
			getMapTypeQueryCompTyper().put(s.getName(), s);
			for (String compType : s.getComponentType()) {
				addNodeComponentType(compType);
			}
			mapComponentTypeLst.put(s.getName(), lstNode);
		}
		tx.success();
		tx.close();
		if (dsl.getHintSofts() != null) {
			setLstTypeHintNaming(dsl.getHintSofts().getHintNaming());
			setLstTypeHintCustom(dsl.getHintSofts().getHintCustom());
		}
		if (dsl.getMeasures() != null) {
			setLstMeasure(dsl.getMeasures().getMeasure());
		}
		setTypeGraph(dsl.getGraph());
	}

	public Node addNodeComponentType(String componentTypeName) {
		 Node node = mapComponentType.get(componentTypeName);
		 if (node == null) {
			 node = GraphUtil.getGraphService().createNode();
			 mapComponentType.put(componentTypeName, node);
		 } 
		 node.addLabel(DynamicLabel.label(ConstantsFw.N_TYPE_COMP_TYPE));
		 node.setProperty("name", componentTypeName);
		 return node;
	}
	
	public List<Node> getNodeComponentTypeLst(String elementName) {
		return mapComponentTypeLst.get(elementName);
	}
	
	public Node getNodeComponentType(String componentTypeName) {
		return mapComponentType.get(componentTypeName);
	}
	
	public boolean hasMethodCall(String methodName) {
		return mapMethodCall.get(methodName) != null;
	}
	
	public List<TypeHintNaming> getLstTypeHintNaming() {
		return lstTypeHintNaming;
	}

	public void setLstTypeHintNaming(List<TypeHintNaming> lstTypeHintNaming) {
		this.lstTypeHintNaming = lstTypeHintNaming;
	}

	public List<TypeHintCustom> getLstTypeHintCustom() {
		return lstTypeHintCustom;
	}

	public void setLstTypeHintCustom(List<TypeHintCustom> lstTypeHintCustom) {
		this.lstTypeHintCustom = lstTypeHintCustom;
	}

	public Map<String, TypeHintSource> getMapTypeHintSource() {
		return mapTypeHintSource;
	}

	public void setMapTypeHintSource(Map<String, TypeHintSource> mapTypeHintSource) {
		this.mapTypeHintSource = mapTypeHintSource;
	}

	public Map<String, TypeHintDescriptor> getMapTypeHintDescriptor() {
		return mapTypeHintDescriptor;
	}

	public void setMapTypeHintDescriptor(Map<String, TypeHintDescriptor> mapTypeHintDescriptor) {
		this.mapTypeHintDescriptor = mapTypeHintDescriptor;
	}

	public List<TypeMeasure> getLstMeasure() {
		return lstMeasure;
	}

	public void setLstMeasure(List<TypeMeasure> lstMeasure) {
		this.lstMeasure = lstMeasure;
	}

	public TypeGraph getTypeGraph() {
		return typeGraph;
	}

	public void setTypeGraph(TypeGraph typeGraph) {
		this.typeGraph = typeGraph;
	}

	public Map<String, Node> getMapComponentType() {
		return mapComponentType;
	}

	public void setMapComponentType(Map<String, Node> mapComponentType) {
		this.mapComponentType = mapComponentType;
	}

	public Map<String, TypeQueryCompTyper> getMapTypeQueryCompTyper() {
		return mapTypeQueryCompTyper;
	}

	public void setMapTypeQueryCompTyper(Map<String, TypeQueryCompTyper> mapTypeQueryCompTyper) {
		this.mapTypeQueryCompTyper = mapTypeQueryCompTyper;
	}

	public Map<String, List<Node>> getMapComponentTypeLst() {
		return mapComponentTypeLst;
	}

	public void setMapComponentTypeLst(Map<String, List<Node>> mapComponentTypeLst) {
		this.mapComponentTypeLst = mapComponentTypeLst;
	}

		
}
