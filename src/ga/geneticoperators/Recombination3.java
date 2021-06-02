package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.*;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        int size = ind1.getNumGenes();

        // array representations of the parents

        // and of the children: do a crossover copy to simplify the later processing
        ArrayList child1Rep = new ArrayList();
        ArrayList child2Rep = new ArrayList();

        child1Rep.addAll(ind1.subList(0, size));
        child2Rep.addAll(ind1.subList(0, size));
        // the set of all visited indices so far
        final Set<Integer> visitedIndices = new HashSet(size);
        // the indices of the current cycle
        final List<Integer> indices = new ArrayList(size);

        // determine the starting index
        int idx = GeneticAlgorithm.random.nextInt(size);
        int cycle = 1;

        while (visitedIndices.size() < size) {
            indices.add(idx);

            int item = ind2.getGene(idx);
            idx = ind1.getIndexof(item);

            while (idx != indices.get(0)) {
                // add that index to the cycle indices
                indices.add(idx);
                // get the item in the second parent at that index
                item = ind2.getGene(idx);
                // get the index of that item in the first parent
                idx = ind1.getIndexof(item);
            }

            // for even cycles: swap the child elements on the indices found in this cycle
            if (cycle++ % 2 != 0) {
                for (int i : indices) {
                    var tmp = child1Rep.get(i);
                    child1Rep.set(i, child2Rep.get(i));
                    child2Rep.set(i, tmp);
                }
            }

            visitedIndices.addAll(indices);
            // find next starting index: last one + 1 until we find an unvisited index
            idx = (indices.get(0) + 1) % size;
            while (visitedIndices.contains(idx) && visitedIndices.size() < size) {
                idx++;
                if (idx >= size) {
                    idx = 0;
                }
            }
            indices.clear();
        }

        for (int i = 0; i < size; i++) {
            ind1.setGene(i,(int) child2Rep.get(i));
            ind2.setGene(i,(int) child1Rep.get(i));
        }
    }

    @Override
    public String toString(){
        return "Cycle Recombination";
    }
}