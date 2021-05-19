package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        final int size = ind1.getNumGenes();

        // choose two random numbers for the start and end indices of the slice
        // (one can be at index "size")
        final int number1 = GeneticAlgorithm.random.nextInt(size - 1);
        final int number2 = GeneticAlgorithm.random.nextInt(size);

        // make the smaller the start and the larger the end
        final int start = Math.min(number1, number2);
        final int end = Math.max(number1, number2);

        // instantiate two child tours
        ArrayList child1 = new ArrayList();
        ArrayList child2 = new ArrayList();

        // add the sublist in between the start and end points to the children
        child1.addAll(ind1.subList(start, end));
        child2.addAll(ind2.subList(start, end));

        // iterate over each city in the parent tours
        int currentPiece = 0;
        int currentPiece1 = 0;
        int currentPiece2 = 0;
        for (int i = 0; i < size; i++) {

            // get the index of the current city
            currentPiece = (end + i) % size;

            // get the city at the current index in each of the two parent tours
            currentPiece1 = ind1.getGene(currentPiece);
            currentPiece2 = ind2.getGene(currentPiece);

            // if child 1 does not already contain the current city in tour 2, add it
            if (!child1.contains(currentPiece2)) {
                child1.add(currentPiece2);
            }

            // if child 2 does not already contain the current city in tour 1, add it
            if (!child2.contains(currentPiece1)) {
                child2.add(currentPiece1);
            }
        }

        // rotate the lists so the original slice is in the same place as in the
        // parent tours
        Collections.rotate(child1, start);
        Collections.rotate(child2, start);

        // copy the tours from the children back into the parents, because crossover
        // functions are in-place!
        //Collections.copy(ind1, child2);
        //Collections.copy(ind2, child1);
    }

    @Override
    public String toString() {
        return "Ordered Recombination";
    }
}