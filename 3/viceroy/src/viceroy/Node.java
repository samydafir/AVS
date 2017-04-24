package viceroy;

public class Node implements Comparable<Node> {
	double pos;
	int ring;
	Node next;
	Node prev;
	Node neighbourBelowL;
	Node neighbourBelowR;
	Node neighbourAboveL;
	Node neighbourAboveR;

	public Node(double pos, int ring) {
		this.pos = pos;
		this.ring = ring;
	}
	
	
	public int compareTo(Node other){
		if(this.pos < other.pos){
			return -1;
		}else if(this.pos > other.pos){
			return 1;
		}else{
			return 0;
		}
	}
	
	public String toString(){
		return neighbourAboveL.pos + "_________" + neighbourAboveR.pos + "\n"
				+ prev.pos + "_" + pos + "_" + next.pos + "\n"
				+ neighbourBelowL.pos + "______" + neighbourBelowR.pos;
	}

}
