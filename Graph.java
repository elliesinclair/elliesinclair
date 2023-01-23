import java.util.*;

public class Graph{
	private HashMap<String, Vertex> graph = new HashMap<>();

	public void addVertex(String word) {
		graph.put(word, new Vertex(word));
	}
	
	public Vertex getVertex(String word) {
		return graph.get(word);
	}

	public void addEdge(String source, String destination) {
		if(!graph.containsKey(source) || !graph.containsKey(destination)) {
			return;
		}
		graph.get(source).addNeighbor(graph.get(destination));
	}
	
	public void initializeGraph() {
		for(Vertex vertex : graph.values() ) {
			vertex.distance = 70000;
			vertex.predecessor = null;
		}
	}
	public HashMap<String, Vertex> getGraph() {
		return graph;
	}

	public boolean contains(String v) {
		if(graph.containsKey(v)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return graph.toString();
	}

}
