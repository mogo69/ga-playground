package main;

import algorithm_iterations.SUS;
import functions.*;

import java.io.IOException;

/**
 * Created by mogo on 5/5/19.
 */
public class Main {

    private static final int NUMBER_OF_RUNS = 10;
    private static final int NUMBER_OF_DIMENSIONS = 5;
    private static final Function FUNCTION = new Deba1();
    private static final double FITNESS_SHARING_MIN_DISTANCE = 4;
    private static final double FITNESS_SHARING_SHARE_PARAM = 1;
    private static final boolean USE_HAMMING_DIST = true;
    private static final double PC = 0.2;
    private static final double PM = 0;
    private static final Function[] FUNCTIONS = { new Deba1(), new Deba2(), new Deba3(), new Deba4() };
    private static double[] FITNESS_SHARING_SHARE_PARAMS = {1, 2, 5};
    private static double[] HAMMING_FITNESS_SHARING_MIN_DISTANCES = {2, 4, 6};
    private static double[] EUCLIDEA_FITNESS_SHARING_MIN_DISTANCES = {0.1, 0.2, 0.4};

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < FUNCTIONS.length; i++) {
            for (int j = 0; j < FITNESS_SHARING_SHARE_PARAMS.length; j++) {
                for (int k = 0; k < HAMMING_FITNESS_SHARING_MIN_DISTANCES.length; k++) {
                    EvolutionRunner evolutionRunner = new EvolutionRunner(
                            NUMBER_OF_RUNS,
                            NUMBER_OF_DIMENSIONS,
                            FUNCTIONS[i],
                            HAMMING_FITNESS_SHARING_MIN_DISTANCES[k],
                            FITNESS_SHARING_SHARE_PARAMS[j],
                            true,
                            PC,
                            PM
                    );
                    evolutionRunner.runEvolution();
                }
                for (int k = 0; k < EUCLIDEA_FITNESS_SHARING_MIN_DISTANCES.length; k++) {
                    EvolutionRunner evolutionRunner = new EvolutionRunner(
                            NUMBER_OF_RUNS,
                            NUMBER_OF_DIMENSIONS,
                            FUNCTIONS[i],
                            EUCLIDEA_FITNESS_SHARING_MIN_DISTANCES[k],
                            FITNESS_SHARING_SHARE_PARAMS[j],
                            false,
                            PC,
                            PM
                    );
                    evolutionRunner.runEvolution();
                }
            }
        }


        /*EvolutionRunner evolutionRunner = new EvolutionRunner(
                NUMBER_OF_RUNS,
                NUMBER_OF_DIMENSIONS,
                FUNCTION,
                FITNESS_SHARING_MIN_DISTANCE,
                FITNESS_SHARING_SHARE_PARAM,
                USE_HAMMING_DIST,
                PC,
                PM
        );
        evolutionRunner.runEvolution();*/

        /*EvolutionRunner evolutionRunner2 = new EvolutionRunner(
                NUMBER_OF_RUNS,
                NUMBER_OF_DIMENSIONS,
                new Deba2(),
                FITNESS_SHARING_MIN_DISTANCE,
                FITNESS_SHARING_SHARE_PARAM,
                USE_HAMMING_DIST,
                PC,
                PM
        );
        evolutionRunner2.runEvolution();*/
    }
}
