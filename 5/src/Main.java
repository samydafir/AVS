
public class Main {

	public static void main(String[] args){
		int nodes = Integer.parseInt(args[0]);
		int runs = Integer.parseInt(args[1]);
		PushSim sim = new PushSim();
		
		for(int i = 0; i < runs; i++){
			sim = new PushSim();
			sim.createGraph(nodes);
			sim.simulate();
			System.out.println(sim.rounds);
		}
	}

}
