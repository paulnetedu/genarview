package pro.pmmc.genarview.functional.predicate.node;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.functional.predicate.PTypeHintPlain;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.visitor.AstVisitorSource;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintPlain;

public class PWithAnnotationValue extends PTypeHintPlain<Node> {

	public PWithAnnotationValue(TypeHintPlain hint) {
		super(hint);
	}
	
	@Override
	public boolean test(Node node) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		Path path = Paths.get((String) node.getProperty("path"));
		tx.success();
		tx.close();
		
		String source = FileUtil.getText(path);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AstVisitorSource visitor = new AstVisitorSource();
		cu.accept(visitor);
		Map<String, Annotation> map = visitor.getMapAnnotations();
		final String annotationNameExp = values.get("annotationName");
		final String attributeNameExp = values.get("annotationAttribute");
		final String valueExp = values.get("annotationAttributeValue");
		Map<String, Annotation> mapAnnot = visitor.getMapAnnotations();
		for (String k : mapAnnot.keySet()) {
			if (k.matches(annotationNameExp)) {
				if (mapAnnot.get(k).isNormalAnnotation()) {
					NormalAnnotation na = (NormalAnnotation) mapAnnot.get(k);
					List lstValues = na.values();
					for (Object v : lstValues) {
						MemberValuePair mvp = (MemberValuePair) v;
						if (mvp.getName().toString().matches(attributeNameExp)) {
							if (mvp.getValue().toString().matches(valueExp)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		/*Node node = GraphUtil.getGraphService().createNode();
		node.addLabel(DynamicLabel.label("Component"));
		node.setProperty("name", strValue);
		tx.success();
		tx.close();*/
		return false;
	}
}
