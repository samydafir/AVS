package blatt10;

public class RGNode {

	int id;
	int recvd;
	RGNode rightNeighbour;
	RGNode leftNeighbour;
	boolean active;
	boolean giveUp;
	
	public RGNode(int id){
		this.id = id;
		active = true;
		giveUp = false;
	}
	
	
	public void sendMsg(){

	
	}
	
}
