package blatt09;

public class Main {
	
	public static void main(String[] args){
		for(int i = 0; i < 100; i++){
			Graph graph = new Graph();
			graph.createGraph(10000, 0.005);
			graph.distribute();
	
		}
	}
}
