package pro.pmmc.genarview.test;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import pro.pmmc.genarview.cache.CacheUtil;
import pro.pmmc.genarview.cache.CacheUtilImpl;
import pro.pmmc.genarview.dsl.DslInformer;
import pro.pmmc.genarview.functional.consumer.CColTypeHintHard;
import pro.pmmc.genarview.functional.consumer.CRelFromNode;
import pro.pmmc.genarview.functional.function.CreatorFunction;
import pro.pmmc.genarview.functional.function.FPackage;
import pro.pmmc.genarview.functional.function.FRcWordLevelIndex;
import pro.pmmc.genarview.functional.function.FRelationshipIncludePackage;
import pro.pmmc.genarview.functional.function.FRelationshipRelation;
import pro.pmmc.genarview.functional.function.FnnDecoLabeler;
import pro.pmmc.genarview.functional.function.FpnDecoLabeler;
import pro.pmmc.genarview.functional.function.FpnVisitorUnit;
import pro.pmmc.genarview.functional.predicate.CreatorPredicate;
import pro.pmmc.genarview.functional.predicate.PTypeHintGeneric;
import pro.pmmc.genarview.graph.GraphExecutor;
import pro.pmmc.genarview.query.GenericQueryCompTyper;
import pro.pmmc.genarview.util.ClassUtil;
import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.FileUtil;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.util.GraphWordUtil;
import pro.pmmc.genarview.util.PathsUtil;
import pro.pmmc.genarview.util.TrendName;
import pro.pmmc.genarview.util.TrendNameLevel;
import pro.pmmc.genarview.util.TrendPackage;
import pro.pmmc.genarview.util.TrendParent;
import pro.pmmc.genarview.visitor.AstVisitorFull;
import pro.pmmc.genarview.xml.xsd.dsl.TypeDefaulter;
import pro.pmmc.genarview.xml.xsd.dsl.TypeFunction;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintDescriptor;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintMember;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintSet;
import pro.pmmc.genarview.xml.xsd.dsl.TypeHintSource;
import pro.pmmc.genarview.xml.xsd.dsl.TypeJoin;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMeasureData;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMeasureMap;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMeasureMapComposite;
import pro.pmmc.genarview.xml.xsd.dsl.TypeMeasureReduce;
import pro.pmmc.genarview.xml.xsd.dsl.TypeQueryCompTyper;
import pro.pmmc.genarview.xml.xsd.dsl.TypeValue;


public class TestGvDsl {

	public static void main(String[] args) throws IOException {
		executeOnProjects("");
		
	}

	public static void executeOnProjects(String nexecution) {
		String[][] paths = getPaths();
		long start = 0;
		long end = 0;
		int plength = 4;//paths.length;
		System.out.println("PATH,DB,#files,LOC,Pack,Level,Min,Max,Avg,StDev,PWP,PWMin,PWMax,PWAvg,PWStdev,WT,Wip,WipMin,WipMax,WipAvg,WipStdev,WipN,WipNMin,WipNMax,WipNAvg,WipNStdev,WipNL,WipNLMin,WipNLMax,WipNLAvg,WipNLStdev,WiPar,WiParMin,WiParMax,WiParAvg,WiParStdev,words,Rels");
		//System.out.println("WPLI,WLI,WP,#words,words,");
		for (int i = 0; i < plength; i++) {
			int endIndex = paths[i][0].indexOf("\\src");
			endIndex = (endIndex != -1) ? endIndex : paths[i][0].length();  
			String name = paths[i][0].substring(0, endIndex);
			name = paths[i][0].substring(name.lastIndexOf("\\") + 1, name.length());
			System.out.print(paths[i][0] + "," + name + ",");
			String pathDb = "D:\\tools\\neo4j-community-2.1.8\\data\\genarview-" + name + dateTime() + ".db";
			GraphDatabaseService gs = new GraphDatabaseFactory().newEmbeddedDatabase(pathDb);
			GraphUtil.registerGraphDatabaseService(gs);
			start = System.currentTimeMillis();
			PathsUtil pathUtil = new PathsUtil();
			for (int j = 0; j < paths[i].length; j++) {
				pathUtil.putSourcePath("source", "source" + j, paths[i][j]);
			}
			processPaths(pathUtil);
			end = System.currentTimeMillis();
			double percentage = 0.85;
			GraphWordUtil graphWordUtil = new GraphWordUtil();
			graphWordUtil.buildGraphWord();
			graphWordUtil.applyTrendLabeler(new TrendName(percentage));
			graphWordUtil.applyTrendLabeler(new TrendNameLevel(percentage));
			graphWordUtil.applyTrendLabeler(new TrendPackage(percentage));
			graphWordUtil.applyTrendLabeler(new TrendParent(percentage));
			graphWordUtil.relateTrends();
			System.out.print(GraphUtil.queryString("MATCH (wi:WordIndex)-[:W_WIT]->(t:Type:DeclarationTop)<-[:CONTAIN]-(p:Package) WITH p.name+'#'+wi.word+'#'+wi.index AS pwi1, COUNT(DISTINCT t) AS c1 WHERE c1>2 RETURN STR(COUNT(DISTINCT pwi1))+','+MIN(c1)+','+MAX(c1)+','+AVG(c1)+','+STDEV(c1)+',' AS value"));
			System.out.print(GraphUtil.queryLong("MATCH (t:Trend) RETURN COUNT(DISTINCT t.word) AS value") + ",");
			System.out.print(GraphUtil.queryLong("MATCH (w:Trend:WiPackage) RETURN COUNT(DISTINCT w.word) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WiPackage) RETURN MIN(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WiPackage) RETURN MAX(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WiPackage) RETURN AVG(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WiPackage) RETURN STDEV(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryLong("MATCH (w:Trend:WipName) RETURN COUNT(DISTINCT w.word) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipName) RETURN MIN(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipName) RETURN MAX(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipName) RETURN AVG(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipName) RETURN STDEV(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryLong("MATCH (w:Trend:WipNameLevel) RETURN COUNT(DISTINCT w.word) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipNameLevel) RETURN MIN(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipNameLevel) RETURN MAX(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipNameLevel) RETURN AVG(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipNameLevel) RETURN STDEV(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryLong("MATCH (w:Trend:WipParent) RETURN COUNT(DISTINCT w.word) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipParent) RETURN MIN(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipParent) RETURN MAX(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipParent) RETURN AVG(w.percentage) AS value") + ",");
			System.out.print(GraphUtil.queryString("MATCH (w:Trend:WipParent) RETURN STDEV(w.percentage) AS value") + ",");
			List<String> lstW =  GraphUtil.queryStrings("MATCH (t:Trend) RETURN DISTINCT t.word AS value ORDER BY t.word");
			for (String word : lstW) {
				System.out.print("#" + word);
			}
			System.out.print(",");
			System.out.print(GraphUtil.queryStrings("MATCH(tr1:Trend)-[r]->(tr2:Trend) WHERE tr1.typeCount>2 AND r.typeFrom>=0.9*tr1.typeCount RETURN REDUCE(total='', s IN COLLECT(tr1.word+'-'+r.type+'->'+tr2.word)| total+'#'+ s) AS value"));
			System.out.print(",");
			System.out.println();
			//*/
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public static void processPaths(PathsUtil pathUtil) {
		CacheUtil cache = new CacheUtilImpl();
		List<Path> lstPFile = new ArrayList<Path>();
		List<Path> lstPSource = new LinkedList<Path>();
		List<Node> lstNPackSource = new LinkedList<Node>();
		FRelationshipIncludePackage nodeRelationer = new FRelationshipIncludePackage();
		for (String groupKey : pathUtil.getGroupKeys()) {
			List<Path> lstPFileGroup = pathUtil.getFiles(groupKey);
			lstPFile.addAll(lstPFileGroup);
			if (groupKey.equals("source")) {
				lstPSource.addAll(lstPFileGroup);
			}
			for (String sourceKey : pathUtil.getSourceKeys(groupKey)) {
				List<Path> lstPDirGroup = pathUtil.getDirectories(groupKey, sourceKey);
				//System.out.println("lstGsDir.size() " + lstGsDir.size());
				FPackage nfSource = new FPackage(pathUtil.getSourcePath(groupKey, sourceKey).toString());
				List<Node> lstNPackTmp = null;
				lstNPackTmp = lstPDirGroup.stream().filter(p -> p.getFileName().toString().indexOf('-') == -1).map(nfSource).collect(Collectors.toList());// to parallel with problem
				lstNPackTmp.stream().map(nodeRelationer).collect(Collectors.toList());
				lstNPackSource.addAll(lstNPackTmp);
			}
		}
		lstPSource = lstPSource.stream().filter(p -> p.toString().toUpperCase().endsWith(".JAVA")).collect(Collectors.toList());
		List<Path> lstPDescriptor = null;
		lstPDescriptor = lstPFile.stream().filter(p -> p.toString().toUpperCase().endsWith(".XML")).collect(Collectors.toList());
		System.out.print(lstPSource.size() + ",");
		int locs = lstPSource.stream().map(p -> FileUtil.countLines(p.toString()))
				.reduce(0, (a, b) -> a+b).intValue();
		System.out.print(locs + ",");
		//for (Path path : lstPSource) {System.out.println("++" + path.toString().substring(path.toString().lastIndexOf("\\")));		}
		
		// /////////////////////////////////////////////////////////////////
		String dsl = null;
		dsl = "C:\\Users\\paul\\workspace-luna-jee\\GenArView-FW\\src\\genarview-dsl-1.0.xml";
		long ti = System.nanoTime();
		DslInformer informer = DslInformer.getInstance();
		informer.readDsl(dsl);
		// Creación de unidades de implementación en nodos
		Map<String, Node> mapNodes = lstPSource.stream() //// to parallel and concurrentmap with problem
				.flatMap(new FpnVisitorUnit(AstVisitorFull.class)).collect(
						Collectors.toMap(n -> {
							Transaction tx = GraphUtil.getGraphService().beginTx();
							String s = (String) n.getProperty("fullName");
							tx.success();
							tx.close();
							return s;}, n -> n));
		 
		cache.setMapNodes(mapNodes);
		//for (String s : mapNodes.keySet()) {System.out.println("-" + s);}
		//if (true) {return;}
		Collection<Node> colNSource = null;
		colNSource = mapNodes.values();
		//System.out.println("colNSource.size() " + colNSource.size());
		// Creacion de relaciones CONTAIN entre package y type
		List<Relationship> lstRel = colNSource.stream()
				.flatMap(new FRcWordLevelIndex()) //.flatMap(new FRelationshipContainType())
				.collect(Collectors.toList());
		GraphUtil.buildPackageLevel();
		System.out.print(GraphUtil.queryLong("MATCH (p:Package)-->(t:Type) RETURN COUNT(DISTINCT p) AS value") + ",");
		System.out.print(GraphUtil.queryInteger("MATCH(p:Package) return MAX(p.level) AS value") + ",");
		System.out.print(GraphUtil.queryLong("MATCH(p:Package) RETURN MIN(p.sizeType) AS value") + ",");
		System.out.print(GraphUtil.queryLong("MATCH(p:Package) RETURN MAX(p.sizeType) AS value") + ",");
		System.out.print(GraphUtil.queryDouble("MATCH(p:Package) RETURN AVG(p.sizeType) AS value") + ",");
		System.out.print(GraphUtil.queryDouble("MATCH(p:Package) RETURN STDEV(p.sizeType) AS value") + ",");
		// Creación de relaciones entre nodos (unidades de implementación)
		lstPSource.stream().map(new FRelationshipRelation()).collect(Collectors.toList());
		Collection<TypeHintSource> colHintSource = informer.getTypeHintSource();
		Collection<TypeHintDescriptor> colHintDescriptor = informer.getTypeHintDescriptor();
		// creacion de stream de filters
		CColTypeHintHard ctf = new CColTypeHintHard(); 
		colHintSource.forEach(ctf);
		List<Predicate> lstFilter = ctf.getPredicates();
		Predicate<Node> predReduced = lstFilter.stream().reduce(Predicate::or).orElse(p -> false);
		// creacion de nodos componentes 
		List<Node> lstNComponent = new ArrayList<Node>();
		List<Node> lstNSourceFilterAll = colNSource.stream().filter(predReduced).collect(Collectors.toList());
		CreatorPredicate creator = new CreatorPredicate();
		PTypeHintGeneric<Node, ? extends TypeHintMember> predicateNode = null;
		List lstArg = new LinkedList();
		List<List<String>> lstLst = null;
		for (TypeHintSource tf : colHintSource) {
			TypeHintSet hintSet = tf.getHintAnd();
			hintSet = (hintSet == null) ? tf.getHintOr() : hintSet;
			predicateNode = (PTypeHintGeneric) creator.createPTypeHint(hintSet);
			List<Node> lstNSourceFilterCurrent = lstNSourceFilterAll.stream().filter(predicateNode).collect(Collectors.toList());
			if (tf.getFnn() != null) {
				TypeFunction fnn = tf.getFnn();
				lstArg.clear();
				if (fnn.getArg() != null && !fnn.getArg().isEmpty()) { // String
					lstArg.addAll(fnn.getArg());
				}
				if (fnn.getArgs() != null && !fnn.getArgs().isEmpty()) { // List<String>
					lstLst = fnn.getArgs().stream().map(a -> a.getArg()).collect(Collectors.toList());
					lstArg.addAll(lstLst);
				}
				Function<Node, Stream<Node>> f = CreatorFunction.createFnn(fnn.getClazz(), lstArg);
				if (fnn.getLabel() != null && !fnn.getLabel().isEmpty()) {
					f = new FnnDecoLabeler(fnn.getLabel());
				}
				lstNSourceFilterCurrent = lstNSourceFilterCurrent.stream().flatMap(f).collect(Collectors.toList());
			}
			lstNSourceFilterCurrent.stream().forEach(
						new CRelFromNode(tf.getComponentType(), informer.getNodeComponentTypeLst(tf.getName()), ConstantsFw.N_REL_REALIZED_BY));
		};
		PTypeHintGeneric<Path, ? extends TypeHintMember> predicatePath = null;
		for (TypeHintDescriptor tf : colHintDescriptor) {
			TypeHintSet hintSet = tf.getHintAnd();
			hintSet = (hintSet == null) ? tf.getHintOr() : hintSet;
			predicatePath = (PTypeHintGeneric) creator.createPTypeHint(hintSet);
			List<Path> lstPDescriptorCurrent = lstPDescriptor.stream().filter(predicatePath).collect(Collectors.toList());
			List<Path> lstFpp = null;
			List<Node> lstFpn = null;
			List<Node> lstFnn = null;
			Stream<Node> streamCurrent = null;
			if (tf.getFpp() != null) {
				TypeFunction fpp = tf.getFpp();
				lstArg.clear();
				if (fpp.getArg() != null && !fpp.getArg().isEmpty()) { // String
					lstArg.addAll(fpp.getArg());
				}
				if (fpp.getArgs() != null && !fpp.getArgs().isEmpty()) { // List<String>
					lstLst = fpp.getArgs().stream().map(a -> a.getArg()).collect(Collectors.toList());
					lstArg.addAll(lstLst);
				}
				Function<Path, Stream<Path>> f = CreatorFunction.createFpp(fpp.getClazz(), lstArg);
				lstFpp = lstPDescriptorCurrent.stream().flatMap(f).collect(Collectors.toList());
			}
			TypeFunction fpn = tf.getFpn();
			lstArg.clear();
			if (fpn.getArg() != null && !fpn.getArg().isEmpty()) { // String
				lstArg.addAll(fpn.getArg());
			}
			if (fpn.getArgs() != null && !fpn.getArgs().isEmpty()) { // List<String>
				lstLst = fpn.getArgs().stream().map(a -> a.getArg()).collect(Collectors.toList());
				lstArg.addAll(lstLst);
			}
			Function<Path, Stream<Node>> f = CreatorFunction.createFpn(fpn.getClazz(), lstArg);
			if (fpn.getLabel() != null && !fpn.getLabel().isEmpty()) {
				f = new FpnDecoLabeler(fpn.getLabel());
			}
			lstFpn = ((lstFpp != null) ? lstFpp : lstPDescriptorCurrent).stream().flatMap(f).collect(Collectors.toList());
			streamCurrent = lstFpn.stream();
			if (tf.getFnn() != null) {
				TypeFunction fnn = tf.getFnn();
				lstArg.clear();
				if (fnn.getArg() != null && !fnn.getArg().isEmpty()) { // String
					lstArg.addAll(fnn.getArg());
				}
				if (fnn.getArgs() != null && !fnn.getArgs().isEmpty()) { // List<String>
					lstLst = fnn.getArgs().stream().map(a -> a.getArg()).collect(Collectors.toList());
					lstArg.addAll(lstLst);
				}
				Function<Node, Stream<Node>> fnnObj = CreatorFunction.createFnn(fnn.getClazz(), lstArg);
				if (fnn.getLabel() != null && !fnn.getLabel().isEmpty()) {
					fnnObj = new FnnDecoLabeler(fnn.getLabel());
				}
				lstFnn = lstFpn.stream().flatMap(fnnObj).collect(Collectors.toList());
				streamCurrent = Stream.concat(streamCurrent, lstFnn.stream());
			}
			for (String ct : tf.getComponentType()) { 
				streamCurrent.forEach(new CRelFromNode(tf.getComponentType(), informer.getNodeComponentTypeLst(tf.getName()), ConstantsFw.N_REL_REALIZED_BY));
			}
		}
		for (TypeQueryCompTyper query : informer.getMapTypeQueryCompTyper().values()) {
			GenericQueryCompTyper gql = (GenericQueryCompTyper) ClassUtil.createObject(query.getClazz(), 
					query.getQuery(), query.getComponentType(), query.getArgs().get(0).getArg());
			gql.relQueryResults();
		}
		//if (informer.getLstMeasure() == null || informer.getLstMeasure().isEmpty()) {
			//return;
		//}
		// Populate aggregated graph
		new GraphExecutor(informer.getTypeGraph()).execute();
		//GraphAggregation graphAgg = new GraphAggregation();
		//graphAgg.executeUpdates();
		// Compute measures
		List<Map<String, Object>> lstFinalOutput =
		informer.getLstMeasure().stream().map(measure -> {
			Map<String, Object> mapOutput = new HashMap<String, Object>();
			Collection<Map<String, Object>> lstData = processMeasureData(measure.getMeasureData()).values();
			Collection<Map<String, Object>> lstDataAll = lstData;
			List<TypeFunction> lstFmap = measure.getMeasureData().getFmap();
			if (lstFmap != null && !lstFmap.isEmpty()) { 
				lstDataAll = new LinkedList<Map<String, Object>>();
				Function<Map<String, Object>, Map<String, Object>> fmap = null;
				CreatorFunction cf = new CreatorFunction();
				List<Map<String, Object>> lstMapTmp = null;
				for (TypeFunction tf : lstFmap) {
					fmap = cf.createFunction(tf);
					lstMapTmp = lstData.stream().map(fmap).collect(Collectors.toList());
					lstDataAll.addAll(lstMapTmp);
				}
			} 
			Function<Map<String, Object>, Entry<String, Object>> fmeasure = null;
			Map<String, Object> mapMeasure = null;
			CreatorFunction cf = new CreatorFunction();
			BinaryOperator< Entry<String, Object> > reducer = null;
			Map<String, String> mapMeasureKey = new HashMap<String, String>();
			Transaction tx = GraphUtil.getGraphService().beginTx();
			for (TypeMeasureMap tmf : measure.getMeasureMap()) {
				fmeasure = (tmf.getFormula() == null) ? cf.createFunction(tmf, tmf.getKey())
						: cf.createFunction(tmf, tmf.getKey(), tmf.getFormula());
				mapMeasure = lstDataAll.stream().map(fmeasure).collect(
						Collectors.toConcurrentMap(e -> e.getKey(), e -> e.getValue()));
				cache.putMeasure(tmf.getName(), mapMeasure);
				mapOutput.put(tmf.getName(), mapMeasure);
				if (tmf.getReducer() != null) {
					for (TypeMeasureReduce tRed : tmf.getReducer()) {
						reducer = cf.createBinaryOperator(tRed, tRed.getKey());
						Optional<Entry<String, Object>> opt = mapMeasure.entrySet().stream().reduce(reducer);
						if (opt.isPresent()) {
							mapOutput.put(tRed.getKey(), opt.get());
							//System.out.print(opt.get().getValue() + ",");
						}
					}
				}
				mapMeasureKey.put(tmf.getName(), tmf.getKey());
			}
			for (TypeMeasureMapComposite tmfcomp : measure.getMeasureMapComposite()) {
				mapMeasure = lstDataAll.stream().map(map -> {
					String prop = null;
					String k = tmfcomp.getKey();
					if (k.startsWith("#")) {
						k = k.substring(1);
						final int index = k.indexOf('.');
						prop = k.substring(index + 1);
						k = k.substring(0, index);
					}
					Transaction tran = GraphUtil.getGraphService().beginTx();
					if (prop == null) {
						k = map.get(k).toString();
					} else {
						Node node = (Node) map.get(k);
						k = node.getProperty(prop).toString();
					}
					mapMeasureKey.put(tmfcomp.getName(), tmfcomp.getKey());
					String formula = tmfcomp.getFormula();
					ExpressionBuilder builder = new ExpressionBuilder(formula);
					for (String measureFName : tmfcomp.getMeasureMapName()) {
						builder.variable(mapMeasureKey.get(measureFName));
					}//builder.variables("x", "y");
			        Expression exp = builder.build();
			        for (String measureFName : tmfcomp.getMeasureMapName()) {
			        	String kmeasure = mapMeasureKey.get(measureFName);
						if (kmeasure.startsWith("#")) {
							kmeasure = kmeasure.substring(1);
							final int index = kmeasure.indexOf('.');
							prop = kmeasure.substring(index + 1);
							kmeasure = kmeasure.substring(0, index);
						}
						if (prop == null) {
							kmeasure = map.get(kmeasure).toString();
						} else {
							Node node = (Node) map.get(kmeasure);
							kmeasure = node.getProperty(prop).toString();
						}
			        	exp.setVariable(measureFName, Double.parseDouble(kmeasure));
					}//exp.setVariable("x", 2.3);
			        tran.success();
			        tran.close();
					return new AbstractMap.SimpleEntry<String, Object>(k, exp.evaluate());
				}).collect(Collectors.toConcurrentMap(e -> e.getKey(), e -> e.getValue()));
				cache.putMeasure(tmfcomp.getName(), mapMeasure);
				if (tmfcomp.getReducer() == null) {
					mapOutput.put(tmfcomp.getName(), mapMeasure);
				} else {
					mapOutput.put(tmfcomp.getName(), mapMeasure.entrySet().stream().reduce(reducer));
				}
			}
			tx.success();
			tx.close();
			return mapOutput;
		}).collect(Collectors.toList());
		//System.out.println();
		/*for (Map<String, Object> map : lstFinalOutput) {
			for (String k : map.keySet()) {
				//System.out.println("=============" + k);
				Object o = map.get(k);
				if (!(o instanceof Map)) {
					Entry<String, Object> entry = (Entry<String, Object>) o;
					System.out.print(entry.getValue() + ",");
				} else {
					Map<String, Object> mapMeasure = (Map<String, Object>) o;
					for (String label : mapMeasure.keySet()) {
						System.out.println(label + "," + mapMeasure.get(label));
					}
				}
			}
		}*/
		//System.out.println("lstNodePackSourceFinal.size() " + lstNodePackSourceFinal.size());
			
	}
	
	public static Map<String, Map<String, Object>> processMeasureData(TypeMeasureData measureData) {
		Map<String, Map<String, Object>> mapResult = new HashMap<String, Map<String, Object>>();
		//if (measureData.getMeasureData() != null) {}
		GraphUtil graphUtil = new GraphUtil();
		Map<String, Object> selMap = null;
		int indexMap = 0;
		int posSecond = 0;
		if (measureData.getQuery() != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			// param format 1: #key#i  //  
			// param format 2: #key
			for (TypeValue param : measureData.getParam()) {
				
			}
			mapResult = graphUtil.queryMap(measureData.getQuery(), params, measureData.getIdField());
			
		} else {
			measureData.getClazz();
			// XXX
		}
		TypeJoin join = measureData.getJoin();
		if (join != null) {
			Map<String, Map<String, Object>> mapResultNew = new HashMap<String, Map<String, Object>>();
			Map<String, Map<String, Object>> mapToJoin = processMeasureData(join.getMeasureData());
			String kjoin = null;
			Map<String, Object> r = null;
			Map<String, Object> newm = null;
			for (Entry<String, Map<String, Object>> entry : mapResult.entrySet()) {
				Map<String, Object> mr = entry.getValue();
				kjoin = mr.get(join.getFromField()).toString();
				r = mapToJoin.get(kjoin);
				newm = new HashMap<String, Object>();
				newm.putAll(mr);
				if (r == null) {
					for (TypeDefaulter fieldk : join.getForField()) {
						newm.put(fieldk.getValue(), fieldk.getDefault());
					}
				} else {
					for (TypeDefaulter fieldk : join.getForField()) {
						newm.put(fieldk.getValue(), r.get(fieldk.getValue()));
					}
				}
				mapResultNew.put(entry.getKey(), newm);
			}
			mapResult = mapResultNew;
		}
		return mapResult;
	}
	
	public static String[][] getPaths() {
		String[][] paths = new String[][] {
				{"C:\\Users\\paul\\git\\git-apache-larger\\camel\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\openjpa\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\wicket\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\myfaces\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\bpmn2-modeler\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\birt\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\scout.rt\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\xtext\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\osee\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\eclipse.jdt.core\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\egit\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\eclipselink.runtime\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\hudson.core\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\jetty.project\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\org.aspectj\\src"},
				{"C:\\Users\\paul\\git\\git-eclipse-larger\\emf\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\jmeter\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\tomcat\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\activemq\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\openejb\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\hadoop\\src"},
				{"C:\\Users\\paul\\git\\git-apache-larger\\geronimo\\src"},
		{"C:\\Users\\paul\\git\\git-apache-larger\\servicemix-components\\src"},
		//*/
		};
		return paths;
	}
	
	public static String dateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		return sdf.format(new Date());
	}
}
// axedademo: SimpleJdbcTemplate jdbcTemplate, implements MessageListener - public void onMessage(Message message),
// ACM
// Maintaining architectural conformance during software development: a practical approach
// Revealing the relationship between architectural elements and source code characteristics

// Labeling source code with information retrieval methods: an empirical study
// Android: # de actividades, fragments, view objects, popups
// Transfuse @BroadcastReceiver