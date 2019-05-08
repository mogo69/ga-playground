package main;

import algorithm_iterations.SUS;
import functions.*;

import java.io.IOException;

/**
 * Created by mogo on 5/5/19.
 */
public class Main {

    private static final int NUMBER_OF_RUNS = 10;
    private static final int NUMBER_OF_DIMENSIONS = 3;
    private static final Function FUNCTION = new Deba1();
    private static final double FITNESS_SHARING_MIN_DISTANCE = 4;
    private static final double FITNESS_SHARING_SHARE_PARAM = 1;
    private static final boolean USE_HAMMING_DIST = true;
    private static final double PC = 0.2;
    private static final double PM = 0;
    private static final double[] PMS = { 0.01, 0.05 };
    private static final Function[] FUNCTIONS = { new Deba1(), new Deba2(), new Deba3(), new Deba4() };
    private static double[] FITNESS_SHARING_SHARE_PARAMS = {1, 2, 5};
    private static double[] HAMMING_FITNESS_SHARING_MIN_DISTANCES = {2, 4, 6};
    private static double[] EUCLIDEA_FITNESS_SHARING_MIN_DISTANCES = {0.1, 0.2, 0.4};

    public static void main(String[] args) throws IOException {
        EvolutionRunner evolutionRunner = new EvolutionRunner(
                NUMBER_OF_RUNS,
                NUMBER_OF_DIMENSIONS,
                FUNCTION,
                FITNESS_SHARING_MIN_DISTANCE,
                FITNESS_SHARING_SHARE_PARAM,
                USE_HAMMING_DIST,
                PC,
                PM
        );
        evolutionRunner.runEvolution();

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
