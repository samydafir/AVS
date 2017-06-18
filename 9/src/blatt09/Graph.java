package blatt09;

import java.util.ArrayList;

public class Graph {
	
	ArrayList<Node> graph;
	
	public Graph(){
		graph = new ArrayList<>();
	}
	
	
	public void createGraph(int n, double p){
		for(int i = 0; i < n; i++){
			graph.add(new Node(i, 'u', n));
		}
		Node potNeighbour;
		for(Node currNode: graph){
			for(int i = 0; i < graph.size(); i++){
				potNeighbour = graph.get(i);
				if(potNeighbour.id != currNode.id && Math.random() < p){
					currNode.addNode(potNeighbour);
					potNeighbour.addNode(currNode);
				}
			}
		}
	}
	
	public void distribute(){
		int rounds = 0;
		int msgs = 0;
		Node startNode = graph.get((int)(10000 * Math.random()));
		startNode.state = 'a';
		while(!allInformed()){
			rounds++;
			for(Node currNode: graph){
				currNode.selectNodes(4);
			}
			for(Node currNode: graph){
				if(currNode.state == 'a' || currNode.state == 'g'){
					msgs += currNode.selectedBy.size();
					for(Node selectedBy: currNode.selectedBy){
						selectedBy.receivedAges.add(currNode.age);
					}/*
					msgs += currNode.selectedNodes.size();
					for(Node selected: currNode.selectedNodes){
						selected.receivedAges.add(currNode.age);
					}*/
					
				}
			}
			for(Node currNode: graph){
				currNode.processReceivedMsgs();
			}
		}
		System.out.println("Rounds: " + rounds);
		System.out.println("Messages: " + msgs);
	}

	private boolean allInformed(){
		for(Node currNode: graph){
			if(currNode.state == 'u'){
				return false;
			}
		}
		return true;
	}	
}









