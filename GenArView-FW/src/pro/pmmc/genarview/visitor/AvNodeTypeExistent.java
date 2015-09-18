package pro.pmmc.genarview.visitor;

import java.nio.file.Files; 
import java.nio.file.Path;
import java.util.ArrayList; 
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;

public class AvNodeTypeExistent extends AvNodeType {

	public AvNodeTypeExistent() {
	
	}
	
	public AvNodeTypeExistent(String path) {
		super(path);
	}
	
	public void processTypeName(String packName, String typeName, boolean isInterface, boolean isNested) {
		GraphUtil graphUtil = new GraphUtil();
		String fullName = packName + "." + typeName;
		Node node = graphUtil.querySingleByFullname(
				fullName, ConstantsFw.N_TYPE_TYPE);
		mapNodeTypes.put(fullName, node);
	}

	@Override
	public void doPostVisit() {
		
	}
	
	
}
