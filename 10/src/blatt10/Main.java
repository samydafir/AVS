package blatt10;

public class Main {
	
	public static void main(String[] args){
		for(int i = 0; i < 10; i++){
			GraphClockwise graph = new GraphClockwise();
			graph.createGraph(10000);
			graph.leader();	
		}
	}
}
