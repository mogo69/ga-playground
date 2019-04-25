package main;

import algorithm_iterations.SUS;
import functions.Function;

import java.io.IOException;

/**
 * Created by mogo on 5/5/19.
 */
public class EvolutionRunner {

    private int numberOfRuns;
    private int numberOfDimensions;
    private Function function;
    private double fitnessSharingMinDistance;
    private double fitnessSharingShareParam;
    private boolean useHammingDist;
    private double pc;
    private double pm;

    EvolutionRunner(
            int numberOfRuns,
            int numberOfDimensions,
            Function function,
            double fitnessSharingMinDistance,
            double fitnessSharingShareParam,
            boolean useHammingDist,
            double pc,
            double pm) {
        this.numberOfRuns = numberOfRuns;
        this.numberOfDimensions = numberOfDimensions;
        this.function = function;
        this.fitnessSharingMinDistance = fitnessSharingMinDistance;
        this.fitnessSharingShareParam = fitnessSharingShareParam;
        this.useHammingDist = useHammingDist;
        this.pc = pc;
        this.pm = pm;
    }

    public void runEvolution() throws IOException {
        for (int dimension = 1; dimension < this.numberOfDimensions + 1; dimension++) {
            for(int run = 0; run < this.numberOfRuns; run++) {
                SUS.generateChartJson(
                        dimension,
                        run,
                        this.function,
                        this.fitnessSharingMinDistance,
                        this.fitnessSharingShareParam,
                        this.useHammingDist,
                        this.pc,
                        this.pm
                );
            }

        }
    }
}
