package blatt09;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {

	int id;
	int totalNodes;
	int age;
	int ctr;
	char state;
	ArrayList<Node> neighbours;
	HashSet<Node> selectedNodes;
	HashSet<Node> selectedBy;
	ArrayList<Integer> receivedAges;
	
	public Node(int id, char state, int totalNodes){
		age = 0;
		ctr = 0;
		this.totalNodes = totalNodes;
		this.id = id;
		this.state = state;
		this.neighbours = new ArrayList<>();
		selectedNodes = new HashSet<>();
		selectedBy = new HashSet<>();
		receivedAges = new ArrayList<>();
	}
	
	public void addNode(Node node){
		neighbours.add(node);
	}
	
	public void selectNodes(int amount){
		Node currSelected;
		while(selectedNodes.size() < amount){
			currSelected = neighbours.get((int)(Math.random() * neighbours.size()));
			selectedNodes.add(currSelected);
			currSelected.addSelectedBy(this);
		}
	}
	
	public void addSelectedBy(Node node){
		selectedBy.add(node);
	}
	
	public void addAge(int age){
		receivedAges.add(age);
	}
	
	public void processReceivedMsgs(){
		int alpha = 1;
		if(state == 'u' && receivedAges.size() > 0){
			state = 'a';
			age = receivedAges.get(0);
		}
		if(state == 'a' && age >= Math.log(totalNodes)/Math.log(9)){
			state = 'g';
		}
		if(state == 'g'){
			ctr++;
			if(ctr == Math.ceil(alpha * Math.log(Math.log(totalNodes)/Math.log(2))/Math.log(2))){
				state = 's';
			}
		}
		if(state == 'a' || state == 'g'){
			age++;
		}
		selectedNodes.clear();
		selectedBy.clear();
		receivedAges.clear();
	}	
}