package blatt06;

/**
 * 
 * @author Samy Dafir, 1331483
 * @author Dominik Bumgartner, 0920177
 *
 */
public class Main {

	public static void main (String[] args){
		for(int i = 0; i < 100; i++){
			Simulation sim = new Simulation (10000, 0.002);
			sim.createGraph();
			//sim.push();
			sim.pushPull();
			System.out.println(sim.rounds + " " + sim.messages);
		}
	}
}
