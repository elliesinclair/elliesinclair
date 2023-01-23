import java.util.ArrayList;

public class Vertex {
	
	public String name;
	public ArrayList<Vertex> neighbors;
	public Vertex predecessor;
	public int distance;
	
	public Vertex(String value) {
		name = value;
		neighbors = new ArrayList<Vertex>();
		predecessor = null;
		distance = 70000;
	}
	
	public void addNeighbor(Vertex neighbor) {
		neighbors.add(neighbor);
	}
	
	public ArrayList<Vertex> getNeighbors() {
		return neighbors;
	}
	
	public String toString() {
		return name;
	}	
}
