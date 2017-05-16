import java.util.ArrayList;

public class PushSim {

	ArrayList<Node> graph;
	int rounds;
	
	public PushSim(){
		graph = new ArrayList<>();
		rounds = 0;
	}	
	
	public void createGraph(int nodes){
		graph.add(new Node(0, true));
		for(int i = 1; i < nodes; i++){
			graph.add(new Node(i, false));
		}
	}
	
	public void simulate(){
		int random;
		while(!allInformed()){
			rounds++;
			for(Node currNode: graph){
				if(currNode.informed){
					random = (int)(Math.random() * graph.size());
					graph.get(random).informed = true;
				}
			}
		}
	}
	
	private boolean allInformed(){
		for(Node currNode: graph){
			if(!currNode.informed){
				return false;
			}
		}
		return true;
	}

}
















