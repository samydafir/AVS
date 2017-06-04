package blatt08;

import java.util.ArrayList;

public class Node {

	int id;
	int degree;
	ArrayList<Node> neighbours;
	
	public Node(int id) {
		neighbours = new ArrayList<>();
		this.degree = 0;
		this.id = id;
	}
	
	public void addNeighbour(Node neighbour){
		neighbours.add(neighbour);
	}

}
