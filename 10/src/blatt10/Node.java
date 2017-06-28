package blatt10;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {

	int id;
	char leader;
	int recId;
	ArrayList<Node> neighbours;
	
	public Node(int id, char leader, int recId){
		this.id = id;
		this.leader = leader;
		this.recId = recId;
		this.neighbours = new ArrayList<>();
	}
	
	public void addNode(Node node){
		neighbours.add(node);
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getRecId(){
		return this.recId;
	}
	
	public void setRecId(int id){
		this.recId = id;
	}
	
	public int getNeighbourId(){
		return neighbours.get(0).getId();
	}
	public void setLeader(char leader){
		this.leader = leader;
	}
	public char getLeader(){
		return this.leader;
	}
	
}