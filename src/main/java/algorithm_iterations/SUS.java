package algorithm_iterations;

import java.util.ArrayList;

import other.PopulationItem;
import functions.Function;
import functions.TestFunction;

public class SUS {
	
	//vals - array of chromosemes
	//chromosome - array of x values for function (depends on dimension)
	public ArrayList<PopulationItem> calculateFirstStage(Function func, double[][] vals) {
		double tot_fitness = 0;
		double fitness = 0; //fitness function
		double pr = 0; //probability to parent's pool election
		double exp_pool = 0; //expected number in parent's pool
		double real_pool = 0; //real number in parent's pool
		
		ArrayList<PopulationItem> population = new ArrayList<PopulationItem>();
		
		//Total fitness of the population
		for(int i = 0; i < vals.length; i++) {
			tot_fitness += func.calculateResult(vals[i]);
		}
		
		for(int i = 0; i < vals.length; i++) {
			fitness = func.calculateResult(vals[i]);
			pr = (double) fitness / tot_fitness;
			exp_pool = pr * vals.length;
			real_pool = Math.round(exp_pool);
			
			population.add(new PopulationItem(-1, vals[i][0], fitness, pr, exp_pool, real_pool));
		}
		
		return population;
		
	}
	
	public static void main(String[] args) {
		Function func = new TestFunction();
		double[][] vals = {{19}, {3}, {7}, {21}, {8}, {29}};
		
		SUS fs = new SUS();
		
		ArrayList<PopulationItem> res = fs.calculateFirstStage(func, vals);
		
		for(PopulationItem p : res) {
			System.out.println(p);
		}
		
	}

}
