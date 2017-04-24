package viceroy;

import java.util.TreeSet;

public class Ring {
	TreeSet<Node> nodes;
	
	public Ring() {
		nodes = new TreeSet<>();
	}
	
	
	public Node findPred(double pos){
		Node pred = nodes.last();
		for(Node currNode: nodes){
			if(currNode.pos <= pos && currNode.next.pos > pos){
				pred = currNode;
			}
		}
		return pred;
		
	}
	
	public Node findSucc(double pos){
		Node succ = nodes.first();
		for(Node currNode: nodes){
			if(currNode.pos <= pos && currNode.next.pos > pos){
				succ = currNode.next;
			}
		}
		return succ;
		
	}

}
