package blatt10;

import java.util.ArrayList;

public class Ring {

	ArrayList<RGNode> rGRing;
	ArrayList<CWNode> cWRing;
	
	public Ring(){
		rGRing = new ArrayList<>();
		cWRing = new ArrayList<>();
	}
	
	public void createRing(int size){
		ArrayList<Integer> ids = new ArrayList<>();

		for(int i = 0; i < size; i++){
			ids.add(i);
		}
		int random;
		int tempId;
		for(int i = 0; i < size; i++){
			random = (int)(Math.random() * size);
			tempId = ids.get(i);
			ids.set(i, ids.get(random));
			ids.set(random, tempId);
		}
		
		for(int i = 0; i < size; i++){
			rGRing.add(new RGNode(ids.get(i)));
			cWRing.add(new CWNode(ids.get(i)));
		}
		
		for(int i = 0; i < size; i++){
			cWRing.get(i).rightNeighbour = cWRing.get((i + 1) % size);
			rGRing.get(i).rightNeighbour = rGRing.get((i + 1) % size);
			rGRing.get((i + 1) % size).leftNeighbour = rGRing.get(i);
		}
		
	}
	
	public void findLeaderRG(){
		int TTL = 1;
		int distSent = 0;
		int messages = 0;
		int steps = 0;
		int rounds = 0;
		RGNode right;
		RGNode left;
		boolean leaderFound = false;
		while(!leaderFound){
			rounds++;
			if(TTL < rGRing.size()){
				steps += TTL * 2;
			} else{
				steps += rGRing.size() * 2;
			}
			for(RGNode currNode: rGRing){
				if(currNode.active){
					distSent = 0;
					right = currNode.rightNeighbour;
					left = currNode.leftNeighbour;
					while(distSent < TTL){
						messages += 4;
						if(right.id > currNode.id || left.id > currNode.id)
							currNode.giveUp = true;
						if(right.id < currNode.id)
							right.giveUp = true;
						if(left.id < currNode.id)
							left.giveUp = true;
						
						distSent++;
						right = right.rightNeighbour;
						left = left.leftNeighbour;
						if(right == currNode || left == currNode){
							leaderFound = true;
							break;
						}
					}
				}	
			}
			TTL *= 2;
			refreshRGNodes();
		}
		System.out.println(steps);
		System.out.println(rounds);
		System.out.println(messages);
	}
	
	public void findLeaderCW(){
		int rounds = 1;
		int messages = 0;
		for(CWNode currNode: cWRing){
			currNode.send(currNode.id);
			messages++;
		}
		updateCWNodes();
			
		while(!leaderFound()){
			rounds++;
			for(CWNode currNode: cWRing){
				if(currNode.send){
					currNode.send(currNode.max);
					messages++;
				}
			}
			updateCWNodes();
		}
		System.out.println(messages);
		System.out.println(rounds);		
	}
	
	private void updateCWNodes(){
		for(CWNode currNode: cWRing){
			currNode.updateNode();
		}
	}
	
	private boolean leaderFound(){
		for(CWNode currNode: cWRing){
			if(currNode.leader){
				return true;
			}
		}
		return false;
	}
	
	private void refreshRGNodes(){
		for(RGNode currNode: rGRing){
			if(currNode.giveUp){
				currNode.active = false;
			}
		}		
	}		
}