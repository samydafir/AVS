package blatt10;

import java.util.ArrayList;

public class GraphClockwise{
	
	ArrayList<Node> graph;
	
	public GraphClockwise(){
		graph = new ArrayList<>();
	}
	
	
	public void createGraph(int n){
		for(int i = 1; i <= n; i++){
			graph.add(new Node(i, 'n', 0));
		}
		Node neighbour;
		Node currNode;
		for(int i = 0; i < graph.size(); i++){
			currNode = graph.get(i);
			if((i+1) >= n){
				neighbour = graph. get(0);
			}else{
				neighbour = graph. get(i+1);
			}
			currNode.addNode(neighbour);
			neighbour.addNode(currNode);	
		}
	}
		
	public void leader(){
		int rounds = 0;
		int msgs = 0;
		Node neighbour;
		while(!leaderElected()){
			rounds++;
			for(Node currNode: graph){
				neighbour = graph.get(currNode.getNeighbourId());
				if(currNode.getId() > currNode.getRecId()){
					neighbour.setRecId(currNode.getId());
					msgs++;
				}else if(currNode.getId() < currNode.getRecId()){
					neighbour.setRecId(currNode.getRecId());
					msgs++;
				}else{
					currNode.setLeader('l');
				}
			}
		}
		System.out.println("Rounds: " + rounds);
		System.out.println("Messages: " + msgs);
	}

	private boolean leaderElected(){
		for(Node currNode: graph){
			if(currNode.getLeader() == 'l'){
				return true;
			}
		}
		return false;
	}	

}