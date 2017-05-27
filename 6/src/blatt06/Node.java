package blatt06;

import java.util.ArrayList;

public class Node {
	int id;
	boolean informed;
	ArrayList<Node> neighbours;
	
	public Node(int id, boolean informed) {
		this.id = id;
		this.informed = informed;
		neighbours = new ArrayList<>();
	}
	
	public void addNeighbour(Node neighbour){
		neighbours.add(neighbour);
	}
	
	public boolean equals(Node other){
		if(id == other.id){
			return true;
		}
		return false;
	}
	
	public int getGlobalNeighbourId(int id){
		return neighbours.get(id).id;
	}	
	
}
