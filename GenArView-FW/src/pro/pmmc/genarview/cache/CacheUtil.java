package pro.pmmc.genarview.cache;

import java.util.Collection;
import java.util.Map;

import org.neo4j.graphdb.Node;

public interface CacheUtil {

	public Node getNode(String key);
	
	public Collection<Node> getNodes();
	
	public void putNode(String key, Node node);
	
	public void clearNodes();
	
	public Object getAux(String auxid, String key);
	
	public Object getMeasure(String measureid, String key);
	
	public Collection getAuxs(String auxid);
	
	public void putAux(String auxid, String key, Object output);
	
	public void putMeasure(String measureid, String key, Object value);
	
	public void clearAuxs(String auxid);
	
	public Map<String, Node> getMapNodes();
	
	public Map getMapAuxs(String auxid);
	
	public void setMapNodes(Map<String, Node> mapNodes);
	
	public void setMapAuxs(String auxid, Map mapAuxs);
	
	public static CacheUtil getCache() {
		return CacheUtilImpl.getLastCache();
	}

	public void putMeasure(String measureid, Map<String, Object> map);
}
