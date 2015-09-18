package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.exception.ItemNotFoundException;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.visitor.AstVisitorSource;

public class FAnnotationValue implements Function<Path, Stream<Node>> {

	private String annotationName;
	
	private String attributeName;
	
	public FAnnotationValue(String annotationName, String attributeName) {
		super();
		this.annotationName = annotationName;
		this.attributeName = attributeName;
	}

	@Override
	public Stream<Node> apply(Path path) {
		String source = FileUtil.getText(path);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AstVisitorSource visitor = new AstVisitorSource();
		cu.accept(visitor);
		Map<String, Annotation> map = visitor.getMapAnnotations();
		Annotation annot = map.get(annotationName);
		if (annot == null) {
			throw new ItemNotFoundException(annotationName);
		}
		String strValue = null;
		if (annot.isNormalAnnotation()) {
			NormalAnnotation na = (NormalAnnotation) annot; 
			List lstValues = na.values();
			for (Object v : lstValues) {
				MemberValuePair mvp = (MemberValuePair) v;
				if (mvp.getName().toString().equals(attributeName)) {
					// FIXME not only string values
					strValue = mvp.getValue().toString();
				}
			}
		} else if (annot.isSingleMemberAnnotation()) {
			SingleMemberAnnotation sa = (SingleMemberAnnotation) annot;
			//sa.getValue()
		}
		if (strValue == null) {
			throw new ItemNotFoundException(annotationName, attributeName);
		}
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Node node = GraphUtil.getGraphService().createNode();
		node.addLabel(DynamicLabel.label("Component"));
		node.setProperty("name", strValue);
		tx.success();
		tx.close();
		List<Node> lstNode = new ArrayList<Node>();
		lstNode.add(node);
		return lstNode.stream();
	}

	
}
