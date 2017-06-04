package blatt08;

public class Main {

	public static void main(String[] args) {
		PAGraph graph = new PAGraph();
		graph.insertNodes(9998);
		System.out.println(graph.evaluate());
	}
}
