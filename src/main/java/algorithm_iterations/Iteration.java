package algorithm_iterations;

import java.util.ArrayList;

import other.ChromosomeGenerator;
import other.PopulationItem;
import functions.Deba1;
import functions.Function;

public class Iteration {
	
	private int iterationsNum = 20;
	private int populationSize = 5;
    private int dimension = 3;
    private int chromosomeLength = 10;
    private Function func = new Deba1();
    
    private double pc = 0.8; //percentage for crossover 
    private double pm = 0.01; //percentage for mutation
    
    public void doIteration() {
    	//Generating start population
    	ChromosomeGenerator gen = new ChromosomeGenerator();
        String[][] encPop = gen.generatePopulation(populationSize, dimension, chromosomeLength);
        
        //Calculating fitness
        SUS sus = new SUS(1, 500);
        ArrayList<PopulationItem> firstPopulation = sus.calculateParameters(func, encPop, 1, 1, true);
        
        //Crossover
        Crossover cross = new Crossover();
        ArrayList<PopulationItem> parentsPool = cross.selectIndividualsForCrossover(firstPopulation);
        String[][] crossoverRes = cross.doCrossover(parentsPool, pc, 10); // todo fix 10
        ArrayList<PopulationItem> crossoveredPopulation = sus.calculateParameters(func, crossoverRes, 1, 1, true);
        
        Mutation mut = new Mutation();
        String[][] mutationRes = mut.doMutation(crossoveredPopulation, pc);
        ArrayList<PopulationItem> mutatedPopulation = sus.calculateParameters(func, crossoverRes, 1, 1, true);
    }

}
