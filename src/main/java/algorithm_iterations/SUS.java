package algorithm_iterations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import io.jenetics.Optimize;
import other.ChromosomeDecoder;
import other.ChromosomeGenerator;
import other.ExecutionResultSaver;
import other.PopulationItem;
import functions.Deba1;
import functions.Function;

public class SUS {
	
	private int populationSize = 500;
	private int dimension = 1;
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

	public ArrayList<PopulationItem> select(
			ArrayList<PopulationItem> population,
			boolean naturalFitnessScores,
			int selectionSize
	) {
		Random rng = new Random();
		// Calculate the sum of all fitness values.
		double aggregateFitness = 0;
		for (PopulationItem candidate: population)
		{
			aggregateFitness += getAdjustedFitness(candidate.getFitness(),
					naturalFitnessScores);
		}

		ArrayList<PopulationItem> selection = new ArrayList<PopulationItem>(selectionSize);
		// Pick a random offset between 0 and 1 as the starting point for selection.
		double startOffset = rng.nextDouble();
		double cumulativeExpectation = 0;
		int index = 0;
		for (PopulationItem candidate : population)
		{
			// Calculate the number of times this candidate is expected to
			// be selected on average and add it to the cumulative total
			// of expected frequencies.
			cumulativeExpectation += getAdjustedFitness(candidate.getFitness(),
					naturalFitnessScores) / aggregateFitness * selectionSize;

			// If f is the expected frequency, the candidate will be selected at
			// least as often as floor(f) and at most as often as ceil(f). The
			// actual count depends on the random starting offset.
			while (cumulativeExpectation > startOffset + index)
			{
				selection.add(candidate);
				index++;
			}
		}
		return selection;
	}

	private double getAdjustedFitness(double rawFitness, boolean naturalFitness)
	{
		if (naturalFitness)
		{
			return rawFitness;
		}
		else
		{
			// If standardised fitness is zero we have found the best possible
			// solution.  The evolutionary algorithm should not be continuing
			// after finding it.
			return rawFitness == 0 ? Double.POSITIVE_INFINITY : 1 / rawFitness;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Function func = new Deba1();
		SUS fs = new SUS();
		
		ChromosomeGenerator gen = new ChromosomeGenerator();
		String[][] encPop = gen.generatePopulation(fs.populationSize, fs.dimension, fs.chromosomeLength);
		
		ArrayList<PopulationItem> firstPopulation = fs.calculateFirstStage(func, encPop);

		for(PopulationItem p : firstPopulation) {
			System.out.println(p);
			System.out.println();
		}

		ArrayList<ArrayList<PopulationItem>> stages = new ArrayList<ArrayList<PopulationItem>>();
		stages.add(firstPopulation);

		for (int i = 0; i < 10; i++) {
			ArrayList<PopulationItem> next = fs.select(firstPopulation, true, 500);
			stages.add(next);
		}


		ExecutionResultSaver saver = new ExecutionResultSaver("execution_results");
		saver.save(stages);
		
	}

}
