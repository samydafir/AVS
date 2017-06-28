package blatt10;

public class Main {
	
	public static void main(String[] args){
		Ring ring;
		for(int i = 0; i < 1; i++){
			ring = new Ring();
			ring.createRing(10000);
//			ring.findLeaderRG();
			ring.findLeaderCW();
		}
	}
}
