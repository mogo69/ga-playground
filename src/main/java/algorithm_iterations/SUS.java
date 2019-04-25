package algorithm_iterations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import other.*;
import functions.Deba1;
import functions.Function;

public class SUS {

    public int populationSize = 500;
    public int dimension;
    public int chromosomeLength = 10;

    public SUS(int dimension) {
        this.dimension = dimension;
    }

    //vals - array of chromosemes
    //chromosome - array of x values for function (depends on dimension)
    public ArrayList<PopulationItem> calculateParameters(Function func, String[][] svals, double fitnessSharingMinDistance, double fitnessSharingShareParam, boolean useHammingDist) {
    	
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
        for (int i = 0; i < decPop.length; i++) {
            tot_fitness += func.calculateResult(decPop[i]);
        }

        for (int i = 0; i < decPop.length; i++) {
            genothype = svals[i];
            phenotype = decPop[i];
            fitness = func.calculateResult(decPop[i]);
            pr = (double) fitness / tot_fitness;
            exp_pool = pr * decPop.length;
            real_pool = Math.round(exp_pool);

            population.add(new PopulationItem(genothype, phenotype, fitness, pr, exp_pool, real_pool));
        }

        ArrayList<Double> fitnessAfterSharing = new ArrayList<Double>();
        for (int i = 0; i < population.size(); i++) {
            fitnessAfterSharing.add(SUS.computeSharedFitnessValue(i, fitnessSharingMinDistance, fitnessSharingShareParam, population, useHammingDist));
        }

        for (int i = 0; i < population.size(); i++) {
            population.get(i).setFitness(fitnessAfterSharing.get(i));
        }

        return population;

    }

    public static int hammingDist(String str1, String str2) {
        int i = 0, count = 0;
        while (i < str1.length())
        {
            if (str1.charAt(i) != str2.charAt(i))
                count++;
            i++;
        }
        return count;
    }

    public static double euclideanDist(double[] params1, double[] params2) {
        Vector vector1 = new Vector(params1);
        Vector vector2 = new Vector(params2);

        return vector1.distanceTo(vector2);
    }

    /**
     * Computes the shared fitness value for a solution
     * @param index the index of the solution for which a shared fitness value will be computed
     * @param minDist any solution closer than minDist will share fitness with the current solution
     * @param shareParam a parameter that defines how much influence sharing has. Higher = more sharing.
     * @param population the array of solutions. Each solution has a genotype and associated fitness value.
     */
    public static double computeSharedFitnessValue(int index, double minDist, double shareParam, ArrayList<PopulationItem> population, boolean useHammingDist){

        double denominator = 1;

        for(int j = 0; j < population.size(); j++){

            double dist;

            if (useHammingDist) {
                dist = SUS.hammingDist(population.get(index).getStringView() ,population.get(j).getStringView());
            } else {
                dist = SUS.euclideanDist(population.get(index).getPhenotype() ,population.get(j).getPhenotype());
            }
            //System.out.println(dist);
            if (dist < minDist){
                denominator += (1-Math.pow(dist/minDist, shareParam));
            }
        }

        return population.get(index).getFitness()/denominator;
    }

    public ArrayList<PopulationItem> select(
            ArrayList<PopulationItem> population,
            boolean naturalFitnessScores,
            int selectionSize
    ) {
        Random rng = new Random();
        // Calculate the sum of all fitness values.
        double aggregateFitness = 0;
        for (PopulationItem candidate : population) {
            aggregateFitness += getAdjustedFitness(candidate.getFitness(),
                    naturalFitnessScores);
        }

        ArrayList<PopulationItem> selection = new ArrayList<PopulationItem>(selectionSize);
        // Pick a random offset between 0 and 1 as the starting point for selection.
        double startOffset = rng.nextDouble();
        double cumulativeExpectation = 0;
        int index = 0;
        for (PopulationItem candidate : population) {
            // Calculate the number of times this candidate is expected to
            // be selected on average and add it to the cumulative total
            // of expected frequencies.
            cumulativeExpectation += getAdjustedFitness(candidate.getFitness(),
                    naturalFitnessScores) / aggregateFitness * selectionSize;

            // If f is the expected frequency, the candidate will be selected at
            // least as often as floor(f) and at most as often as ceil(f). The
            // actual count depends on the random starting offset.
            while (cumulativeExpectation > startOffset + index) {
                selection.add(candidate);
                index++;
            }
        }
        return selection;
    }

    private double getAdjustedFitness(double rawFitness, boolean naturalFitness) {
        if (naturalFitness) {
            return rawFitness;
        } else {
            // If standardised fitness is zero we have found the best possible
            // solution.  The evolutionary algorithm should not be continuing
            // after finding it.
            return rawFitness == 0 ? Double.POSITIVE_INFINITY : 1 / rawFitness;
        }
    }


    public ArrayList<PopulationItem> findSeeds(ArrayList<PopulationItem> stage) {
        ArrayList<PopulationItem> seeds = new ArrayList<PopulationItem>();

        for (PopulationItem item: stage) {
            if (this.isPeakCandidate(seeds, item)) {
                seeds.add(item);
            }
        }

        return seeds;
    }

    public boolean isPeakCandidate(ArrayList<PopulationItem> seeds, PopulationItem candidate) {
        for (PopulationItem item: seeds) {
            if (SUS.euclideanDist(candidate.getPhenotype(), item.getPhenotype()) < 0.03) return false;
        }
        return true;
    }

    public static void generateChartJson(
            int dimension,
            int run,
            Function func,
            double fitnessSharingMinDistance,
            double fitnessSharingShareParam,
            boolean useHammingDist,
            double pc,
            double pm
    ) throws IOException {
        SUS fs = new SUS(dimension);
        final int NUMBER_OF_STEPS = 1000000;
        int fitnessCalculationCount = 0;

        //Population generation + calculating fitness + selecting candidates to parents pool
        ChromosomeGenerator gen = new ChromosomeGenerator();
        String[][] encPop = gen.generatePopulation(fs.populationSize, fs.dimension, fs.chromosomeLength);

        ArrayList<PopulationItem> firstPopulation = fs.calculateParameters(func, encPop, fitnessSharingMinDistance, fitnessSharingShareParam, useHammingDist);

        //Saving results for drawing
        ArrayList<ArrayList<PopulationItem>> stages = new ArrayList<ArrayList<PopulationItem>>();
        ArrayList<Double> stagesAvgFitness = new ArrayList<Double>();
		stages.add(firstPopulation);

		for (int i = 1; i < NUMBER_OF_STEPS; i++) {
            fitnessCalculationCount += fs.populationSize;
			ArrayList<PopulationItem> parentsPool = fs.select(stages.get(i - 1), true, fs.populationSize);

            //Crossover
            /*Crossover cross = new Crossover();
            String[][] crossoverRes = cross.doCrossover(parentsPool, pc, fs.chromosomeLength);
            ArrayList<PopulationItem> crossoveredPopulation = fs.calculateParameters(func, crossoverRes, fitnessSharingMinDistance, fitnessSharingShareParam, useHammingDist);*/
            //Mutation
            /*Mutation mut = new Mutation();
            String[][] mutationRes = mut.doMutation(parentsPool, pm);
            ArrayList<PopulationItem> mutatedPopulation = fs.calculateParameters(func, mutationRes, fitnessSharingMinDistance, fitnessSharingShareParam, useHammingDist);*/

            //System.out.println(parentsPool.size());
			stages.add(parentsPool);

            double avgFitness = SUS.computeAvgPopulationFitness(parentsPool);
            stagesAvgFitness.add(avgFitness);

            if (fitnessCalculationCount > 2000000) break;
            if (stages.size() > 11 && stagesAvgFitness.get(stages.size() - 2) - stagesAvgFitness.get(stages.size() - 3) < 0.0001 ) break;
		}



        ArrayList<ArrayList<PopulationItem>> stagesSeeds = new ArrayList<ArrayList<PopulationItem>>();
        for (ArrayList<PopulationItem> stage: stages) {
            stagesSeeds.add(fs.findSeeds(stage));
        }
        ArrayList<PopulationItem> lastPopulation = stagesSeeds.get(stagesSeeds.size() - 2);


		ExecutionResultSaver saver = new ExecutionResultSaver("execution_results", "execution_stats_results");
		saver.save(stagesSeeds);

        StatisticItem statisticItem = new StatisticItem();
        statisticItem.setEvaluation(func.getName());
        statisticItem.setDimension(fs.dimension);
        statisticItem.setRun(run);
        statisticItem.setSelection("SUSSelection");
        statisticItem.setCrossover("OnePointCrossover");
        statisticItem.setPc(pc);
        statisticItem.setMutation("OnePointMutation");
        statisticItem.setAlpha(fitnessSharingShareParam);
        statisticItem.setSigma(fitnessSharingMinDistance);
        statisticItem.setPm(pm);
        statisticItem.setNfe(fitnessCalculationCount);
        statisticItem.setDistance(useHammingDist ? "HammingDistance" : "EuclideanDistance");
        double nSeeds = stagesSeeds.size();
        statisticItem.setnSeeds(nSeeds);

        double gp = func.countGlobalPeaksIn(lastPopulation);
        double lp = func.countLocalPeaksIn(lastPopulation);
        double np = gp + lp;
        double pr = np / func.getGlobalPeaks().length + func.getLocalPeaks().length;
        double gpr = gp / func.getGlobalPeaks().length;
        double lpr = lp / func.getLocalPeaks().length;
        double fpr =  (nSeeds - np) / nSeeds;

        statisticItem.setNp(np);
        statisticItem.setGp(gp);
        statisticItem.setLp(lp);
        statisticItem.setPr(pr);
        statisticItem.setGpr(gpr);
        statisticItem.setLpr(lpr);
        statisticItem.setFpr(fpr);
        statisticItem.setLfp(-1);
        statisticItem.setHfp(-1);

        saver.saveStatisticItem(statisticItem);
    }

    public static double computeAvgPopulationFitness(ArrayList<PopulationItem> population) {
        double avgFitness = 0;
        for (PopulationItem populationItem: population) {
            avgFitness += populationItem.getFitness();
        }
        avgFitness /= population.size();
        return  avgFitness;
    }

}
