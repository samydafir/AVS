package blatt08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class PAGraph {

	ArrayList<Node> nodes;
	int totDeg;
	int totNodes;
	
	
	public PAGraph() {
		nodes = new ArrayList<>();
		totDeg = 2;
		totNodes = 2;
		Node initialNode1 = new Node(0);
		Node initialNode2 = new Node(1);
		initialNode1.degree = 1;
		initialNode2.degree = 1;
		initialNode1.addNeighbour(initialNode2);
		initialNode2.addNeighbour(initialNode1);
		nodes.add(initialNode1);
		nodes.add(initialNode2);
	}
	
	public void insertNodes(int amount){
		HashSet<Integer> triedNodes = new HashSet<>();
		Node newNode;
		Node potNeighbour;
		for(int i = 0; i < amount; i++){
			newNode = new Node(totNodes);
			triedNodes.clear();
			while(newNode.degree == 0){
				potNeighbour = nodes.get((int)(Math.random() * nodes.size()));
				while(triedNodes.contains(potNeighbour.id)){
					if(triedNodes.size() == nodes.size()){
						triedNodes.clear();
					}else{
						potNeighbour = nodes.get((int)(Math.random() * nodes.size()));
						triedNodes.add(potNeighbour.id);
					}
				}
				if(Math.random() < (double)potNeighbour.degree / totDeg){
					totDeg += 2;
					totNodes++;
					newNode.degree++;
					potNeighbour.degree++;
					potNeighbour.addNeighbour(newNode);
					newNode.addNeighbour(potNeighbour);
					nodes.add(newNode);
				}
			}
		}
	}
	
	public String evaluate(){
		HashMap<Integer, Integer> freq = new HashMap<>();;
		String eval = "";
		for(Node currNode: nodes){
			if(freq.containsKey(currNode.degree)){
				freq.put(currNode.degree, freq.get(currNode.degree) + 1);
			}else{
				freq.put(currNode.degree, 1);
			}
		}
		for(Entry<Integer, Integer> currFreq: freq.entrySet()){
			eval += currFreq.getKey() + " " + currFreq.getValue() + "\n";
		}
		return eval;
	}

}