package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class Mutation2<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation2(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO
        int cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int cut2;
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (cut1==cut2);
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        for (int i = cut1+1; i < cut2; i++) {
            int randomIndexToSwap = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
            int temp = ind.getGene(randomIndexToSwap);
            ind.setGene(randomIndexToSwap, ind.getGene(i));
            ind.setGene(i, temp);
        }
    }

    @Override
    public String toString(){
        return "Scramble";
    }
}