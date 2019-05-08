package main;

import algorithm_iterations.Crossover;
import algorithm_iterations.SUS;
import functions.Deba1;
import functions.Function;
import other.ChromosomeGenerator;
import other.PopulationItem;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mogo on 5/5/19.
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        Tester.test();

    }


    public static void test() throws IOException {
        Function func = new Deba1();
        SUS fs = new SUS(1, 500);

        //Population generation + calculating fitness + selecting candidates to parents pool
        ChromosomeGenerator gen = new ChromosomeGenerator();
        String[][] encPop = gen.generatePopulation(fs.populationSize, fs.dimension, fs.chromosomeLength);

        ArrayList<PopulationItem> firstPopulation = fs.calculateParameters(func, encPop, 1, 1, false);


        System.out.println("generation 1");
        System.out.println();
        for (PopulationItem p : firstPopulation) {
            System.out.println(p);
            System.out.println();
        }

        System.out.println("Fitness:");
        System.out.println(firstPopulation.get(0).getFitness());
        System.out.println("SharedFitnessValue:");
        System.out.println(SUS.computeSharedFitnessValue(0, 4, 1, firstPopulation, true));

        System.out.println();
        System.out.println("***************");
        System.out.println();

        //Crossover testing
        Crossover cross = new Crossover();
        ArrayList<PopulationItem> parentsPool = cross.selectIndividualsForCrossover(firstPopulation);

        String[][] crossoverRes = cross.doCrossover(parentsPool, 0.5, 10); // todo fix 10
        ArrayList<PopulationItem> secondPopulation = fs.calculateParameters(func, crossoverRes, 1, 1, true);

        System.out.println("generation 2");
        System.out.println();
        for (PopulationItem p : secondPopulation) {
            System.out.println(p);
            System.out.println();
        }
    }
}
