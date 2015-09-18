package pro.pmmc.genarview.cache;

import java.util.Collection; 
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.neo4j.graphdb.Node;

public class CacheUtilImpl implements CacheUtil {

	private static CacheUtil lastCache;
	
	private Map<String, Node> mapNodes;
	
	private Map<String, Map<String, Object>> mapAuxs;
	
	private Map<String, Map<String, Object>> mapMeasure;

	public CacheUtilImpl() {
		setMapNodes(new ConcurrentHashMap<String, Node>());
		mapAuxs = new ConcurrentHashMap<String, Map<String, Object>>();
		mapMeasure = new ConcurrentHashMap<String, Map<String, Object>>();
		lastCache = this;
	}
	
	@Override
	public Node getNode(String key) {
		return mapNodes.get(key);
	}

	@Override
	public void putNode(String key, Node node) {
		mapNodes.put(key, node);
	}

	@Override
	public void clearNodes() {
		mapNodes.clear();
	}

	@Override
	public Object getAux(String auxid, String key) {
		Map<String, Object> mapAux = mapAuxs.get(auxid);
		//System.out.println("K--" + key);
		return mapAux.get(key);
	}

	@Override
	public void putAux(String auxid, String key, Object aux) {
		Map<String, Object> mapAux = mapAuxs.get(auxid);
		mapAux.put(key, aux);
	}

	@Override
	public void putMeasure(String measureid, Map<String, Object> map) {
		mapMeasure.put(measureid, map);
	}
	
	@Override
	public void putMeasure(String measureid, String key, Object value) {
		Map<String, Object> mapAux = mapMeasure.get(measureid);
		mapAux.put(key, value);
	}
	
	@Override
	public Object getMeasure(String measureid, String key) {
		Map<String, Object> mapAux = mapMeasure.get(measureid);
		//System.out.println("K--" + key);
		return mapAux.get(key);
	}


	@Override
	public void clearAuxs(String auxid) {
		Map<String, Object> mapAux = mapAuxs.get(auxid);
		mapAux.clear();
	}

	public Map<String, Node> getMapNodes() {
		return mapNodes;
	}



	public void setMapNodes(Map<String, Node> mapNodes) {
		this.mapNodes = mapNodes;
	}



	public Map<String, Map<String, Object>> getMapAuxs() {
		return mapAuxs;
	}



	public void setMapAuxs(String auxid, Map mapAux) {
		mapAuxs.put(auxid, mapAux);
	}

	@Override
	public Collection<Node> getNodes() {
		return mapNodes.values();
	}

	@Override
	public Collection getAuxs(String auxid) {
		Map<String, Object> mapAux = mapAuxs.get(auxid);
		return mapAux.values();
	}

	public static CacheUtil getLastCache() {
		return lastCache;
	}

	@Override
	public Map getMapAuxs(String auxid) {
		return mapAuxs.get(auxid);
	}
		
	
}
