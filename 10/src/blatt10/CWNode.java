package blatt10;

public class CWNode {

	int id;
	int max;
	int recvd;
	boolean send;
	boolean leader;
	CWNode rightNeighbour;
	
	public CWNode(int id){
		this.id = id;
		this.max = id;
		send = true;
		recvd = -1;
		leader = false;
	}
	
	public void updateNode(){
		if(recvd == id){
			leader = true;
		} else if(recvd > max){
			max = recvd;
			send = true;
		} else{
			send = false;
		}
		recvd = -1;
	}
	
	
	public void send(int value){
		rightNeighbour.recvd = value;
	}
}
