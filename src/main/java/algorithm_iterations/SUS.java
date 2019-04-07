package algorithm_iterations;

import java.util.ArrayList;

import other.ChromosomeDecoder;
import other.ChromosomeGenerator;
import other.PopulationItem;
import functions.Deba1;
import functions.Function;
import functions.TestFunction;

public class SUS {
	
	private int populationSize = 500;
	private int dimension = 3;
	private int chromosomeLength = 10;
	
	//vals - array of chromosemes
	//chromosome - array of x values for function (depends on dimension)
	public ArrayList<PopulationItem> calculateFirstStage(Function func, String[][] svals) {
		ChromosomeDecoder decod = new ChromosomeDecoder();
		double[][] decPop = decod.decodePopulation(svals, func.getStartValue(), func.getEndValue(), chromosomeLength);
		
		double tot_fitness = 0;
		
		String[] genothype;
		double[] phenotype;
		double fitness = 0; //fitness function
		double pr = 0; //probability to parent's pool election
		double exp_pool = 0; //expected number in parent's pool
		double real_pool = 0; //real number in parent's pool
		
		ArrayList<PopulationItem> population = new ArrayList<PopulationItem>();
		
		//Total fitness of the population
		for(int i = 0; i < decPop.length; i++) {
			tot_fitness += func.calculateResult(decPop[i]);
		}
		
		for(int i = 0; i < decPop.length; i++) {
			genothype = svals[i];
			phenotype = decPop[i];
			fitness = func.calculateResult(decPop[i]);
			pr = (double) fitness / tot_fitness;
			exp_pool = pr * decPop.length;
			real_pool = Math.round(exp_pool);
			
			population.add(new PopulationItem(genothype, phenotype, fitness, pr, exp_pool, real_pool));
		}
		
		return population;
		
	}
	
	public static void main(String[] args) {
		Function func = new Deba1();
		SUS fs = new SUS();
		
		ChromosomeGenerator gen = new ChromosomeGenerator();
		String[][] encPop = gen.generatePopulation(fs.populationSize, fs.dimension, fs.chromosomeLength);
		
		ArrayList<PopulationItem> res = fs.calculateFirstStage(func, encPop);
		
		for(PopulationItem p : res) {
			System.out.println(p);
			System.out.println();
		}
		
	}

}
