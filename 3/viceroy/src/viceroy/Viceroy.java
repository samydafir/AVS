package viceroy;

public class Viceroy {

	private int ringCount;
	private int peersPerRing;
	Ring ringForAll;
	Ring[] rings;
	int pathLength;
	double maxNodeDist;
	
	public Viceroy(int ringCount, int peerCount) {
		this.ringCount = ringCount;
		peersPerRing = peerCount / ringCount;
		rings = new Ring[ringCount];
		ringForAll = new Ring();
		//Max. distance between current and destination node to route on same ring
		maxNodeDist = Math.pow(ringCount,2) / peerCount;
	}
	
	public void createRings(){
		Node currNewNode;
		for(int j = 0; j < ringCount; j++){
			rings[j] = new Ring();
			for(int i = 0; i < peersPerRing; i++){
				currNewNode = new Node(Math.random(), j);
				rings[j].nodes.add(currNewNode);
				ringForAll.nodes.add(currNewNode);
			}
		}
		
		Node prevNode;
		for(Ring currRing: rings){
			prevNode = currRing.nodes.last();
			for(Node currNode: currRing.nodes){
				prevNode.next = currNode;
				currNode.prev = prevNode;
				prevNode = currNode;
			}
		}
	}
	
	void createPointers(){
		double currPos;
		for(int i = 0; i < ringCount; i++){
			for(Node currNode: rings[i].nodes){
				currPos = currNode.pos;
				currNode.neighbourBelowL = rings[(i + 1) % ringCount].findSucc(currPos);
				currNode.neighbourBelowR = rings[(i + 1) % ringCount].findSucc((currPos + Math.pow(2, -(i + 1))) % 1);
				if(i - 1 < 0){
					currNode.neighbourAboveR = rings[ringCount - 1].findPred(currPos);
					if((currPos - Math.pow(2, -(i + 1)) % 1) < 0){
						currNode.neighbourAboveL = rings[ringCount - 1].findPred(1 + ((currPos - Math.pow(2, -(i + 1))) % 1));
					} else{
						currNode.neighbourAboveL = rings[ringCount - 1].findPred(((currPos - Math.pow(2, -(i + 1))) % 1));
					}
				} else{
					currNode.neighbourAboveR = rings[i - 1].findPred(currPos);
					if((currPos - Math.pow(2, -(i + 1)) % 1) < 0){
						currNode.neighbourAboveL = rings[i - 1].findPred(1 + ((currPos - Math.pow(2, -(i + 1))) % 1));
					} else{
						currNode.neighbourAboveL = rings[i - 1].findPred(((currPos - Math.pow(2, -(i + 1))) % 1));
					}
				}
				adjustPointers(currNode);
			}
		}
	}
	
	public void randRoute(){
		Node start;
		Node dest;
		Node currNode;
		int paths = 0;
		while(paths < 10000){
			start = rings[(int)(Math.random()*5)].findSucc(Math.random());
			dest = rings[(int)(Math.random()*5)].findSucc(Math.random());
			while(start.ring == dest.ring && start.pos == dest.pos){
				dest = rings[(int)(Math.random()*5)].findSucc(Math.random());
			}
			
			currNode = start;
			pathLength = 0;
			while(!(currNode.pos == dest.pos && currNode.ring == dest.ring)){
				if(pathLength > 100){
					break;
				}
				while(currNode.ring != 0){
					currNode = rings[currNode.ring - 1].findSucc(currNode.pos);
				}
				pathLength++;
				currNode = routeStep(currNode, dest);
			}
			if(currNode.pos == dest.pos && currNode.ring == dest.ring){
				paths++;
				System.out.println(pathLength);
			}

		}
	}
	
	private Node routeStep(Node currNode, Node dest){
		
		while(!(currNode.pos == dest.pos && currNode.ring == dest.ring)){
			pathLength++;
			
			if((currNode.ring == dest.ring) && ((currNode.pos - dest.pos < 0 && Math.abs(currNode.pos - dest.pos) < maxNodeDist) ||
					(currNode.pos - dest.pos > 0 && 1 - Math.abs(currNode.pos - dest.pos) < maxNodeDist))){
				currNode = currNode.next;
			}else if((currNode.ring == dest.ring) && ((currNode.pos - dest.pos > 0 && Math.abs(currNode.pos - dest.pos) < maxNodeDist) ||
					(currNode.pos - dest.pos < 0 && 1 - Math.abs(currNode.pos - dest.pos) < maxNodeDist))){
				currNode = currNode.prev;
				
			}else if(currNode.ring == ringCount - 1){
				break;
				
			}else{
				if(dest.pos > currNode.neighbourBelowL.pos && dest.pos < currNode.neighbourBelowR.pos){
					currNode = currNode.neighbourBelowL;
				}else{
					currNode = currNode.neighbourBelowR;
				}
			}
		}
		return currNode;
	}
	
	private void adjustPointers(Node node){
		Node temp;
		if(node.neighbourAboveR.pos < node.neighbourAboveL.pos){
			temp = node.neighbourAboveR;
			node.neighbourAboveR = node.neighbourAboveL;
			node.neighbourAboveL = temp;
		}
		if(node.neighbourBelowR.pos < node.neighbourBelowL.pos){
			temp = node.neighbourBelowR;
			node.neighbourBelowR = node.neighbourBelowL;
			node.neighbourBelowL = temp;
		}
	}
	
	public static void main(String[] args){	
		Viceroy network = new Viceroy(5, 100);
		network.createRings();
		network.createPointers();
		network.randRoute();
	}
}
