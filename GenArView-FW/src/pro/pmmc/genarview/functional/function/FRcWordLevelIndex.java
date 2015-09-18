package pro.pmmc.genarview.functional.function;

import java.util.HashMap;
import java.util.Iterator; 
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import pro.pmmc.genarview.util.ConstantsFw;
import pro.pmmc.genarview.util.GraphUtil;
import pro.pmmc.genarview.util.GraphWordUtil;

public class FRcWordLevelIndex extends FRelationshipContainType {

	public void processNodes(Node nPackContainer, Node nTypeContained) {
		Transaction tx = GraphUtil.getGraphService().beginTx();
		String name = nTypeContained.getProperty("name").toString();
		GraphWordUtil graphWordUtil = new GraphWordUtil();
		String[] words = StringUtils.splitByCharacterTypeCamelCase(name);
		int middle = words.length / 2;
		for (int i = 0; i < middle; i++) {
			processWord(words[i], i, nTypeContained, nPackContainer, graphWordUtil);
		}
		int suffixIndex = ConstantsFw.MAX_SUFFIX_INDEX;
		for (int i = words.length - 1; i >= middle; i--) {
			processWord(words[i], suffixIndex, nTypeContained, nPackContainer, graphWordUtil);
			suffixIndex--;
		}
		tx.success();
		tx.close();
	}
	
	public void processWord(String word, int index, Node nTypeContained, Node nPackContainer, final GraphWordUtil graphWordUtil) {
		final String strWord = "word";
		final String strIndex = "index";
		int level = (int) nTypeContained.getProperty("level");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( strWord, word);
		params.put( strIndex, index);
		Node nWord = null; 
		Node nWordIndex = graphWordUtil.queryWordIndexSingle(params);
		// buscar WordIndex si no esta presente crearlo
		if (nWordIndex == null) {
			// buscar Word si no esta presente crearlo
			nWord = graphWordUtil.queryWordSingle(params);
			if (nWord == null) {
				nWord = GraphUtil.getGraphService().createNode();
				nWord.addLabel(DynamicLabel.label(ConstantsFw.N_WORD));
				nWord.setProperty(strWord, word);
			}
			nWordIndex = GraphUtil.getGraphService().createNode();
			nWordIndex.addLabel(DynamicLabel.label(ConstantsFw.N_WORD_INDEX));
			nWordIndex.setProperty(strWord, word);
			nWordIndex.setProperty(strIndex, index);
			nWord.createRelationshipTo(nWordIndex, ()->ConstantsFw.R_WORD_INDEX);
		}
		nWordIndex.createRelationshipTo(nTypeContained, ()->ConstantsFw.R_WORD_INDEX_TYPE);
	}
}
