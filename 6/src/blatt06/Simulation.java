package blatt06;

import java.util.ArrayList;

public class Simulation {
	
	private ArrayList<Node> graph;
	private ArrayList<Integer> toInform;
	private double p;
	private int n;
	int rounds;
	int messages;
	
	public Simulation(int n, double p) {
		this.n = n;
		this.p = p;
		graph = new ArrayList<>();
		toInform = new ArrayList<>();
	}
	
	public void createGraph(){
		graph.add(new Node(0, true));
		for(int i = 1; i < n; i++){
			graph.add(new Node(i, false));
		}
		
		Node potNeighbour;
		for(Node currNode: graph){
			currNode.addNeighbour(currNode);
			for(int i = 0; i < graph.size(); i++){
				potNeighbour = graph.get(i);
				if(!potNeighbour.equals(currNode) && Math.random() < p){
					currNode.addNeighbour(potNeighbour);
				}
			}
		}	
	}
	
	public void push(){
		while(numOfInformed() < n){
			round(true, false);
		}
	}
	
	public void pushPull(){
		double c = 50;
		int pushOnly = (int)(2 * c * Math.pow(Math.log(n),2));
		int pushPullLimit = n / 3;
		while(numOfInformed() < n){
			if(numOfInformed() <= pushOnly){
				round(true, false);
			}else if(numOfInformed() < pushPullLimit){
				round(true, true);
			}else{
				round(false, true);
			}
		}
	}
	
	private void round(boolean push, boolean pull){
		int neighbourId;
		rounds++;
		toInform.clear();
		for(Node currNode: graph){
			if(push){
				if(currNode.informed){
					neighbourId = currNode.getGlobalNeighbourId((int)(Math.random() * currNode.neighbours.size()));
					toInform.add(neighbourId);
					messages++;
				}
			}
			if(pull){
				if(currNode.neighbours.get((int)(Math.random() * currNode.neighbours.size())).informed){
					toInform.add(currNode.id);
				}
				messages++;
			}
		}
		informMarkedNodes();
	}
	
	private int numOfInformed(){
		int infCount = 0;
		for(Node currNode: graph){
			if(currNode.informed){
				infCount++;
			}
		}
		return infCount;
	}
	
	private void informMarkedNodes(){
		for(int id: toInform){
			graph.get(id).informed = true;
		}
	}
	
}
