package blatt06;

public class Main {

	public static void main (String[] args){
		for(int i = 0; i < 100; i++){
			Simulation sim = new Simulation (10000, 0.002);
			sim.createGraph();
			sim.pushPull();
			System.out.println(sim.rounds + " " + sim.messages);
		}
	}

}
