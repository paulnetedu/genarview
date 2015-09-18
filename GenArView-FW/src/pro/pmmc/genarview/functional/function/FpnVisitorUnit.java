package pro.pmmc.genarview.functional.function;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.neo4j.graphdb.Node;

import pro.pmmc.genarview.exception.ClassInstantiationException;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.visitor.AvNodeType;

public class FpnVisitorUnit implements Function<Path, Stream<Node>> {

	private Class<? extends AvNodeType> tType;
	
	public FpnVisitorUnit(String strType) {
		try {
			this.tType = (Class<? extends AvNodeType>) 
					Class.forName(strType);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public FpnVisitorUnit(Class<? extends AvNodeType> tType) {
		this.tType = tType;
	}

	@Override
	public Stream<Node> apply(Path path) {
		String source = FileUtil.getText(path);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		Map options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
		parser.setCompilerOptions(options);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		AvNodeType visitor = null;
		try {
			visitor = tType.newInstance();
		} catch (InstantiationException e) {
			throw new ClassInstantiationException(tType.getName() + " could not be instantiated due to " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new ClassInstantiationException(tType.getName() + " could not be instantiated due to " + e.getMessage());
		}
		visitor.setPath(path.toString());
		cu.accept(visitor);
		visitor.doPostVisit();
		return visitor.getMapNodeTypes().values().stream();
	}

}
