package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class Mutation3<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation3(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO
        int gene1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int gene2;
        do {
            gene2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (gene1==gene2);

        int temp = ind.getGene(gene1);
        ind.setGene(gene1, ind.getGene(gene2));
        ind.setGene(gene2, temp);
    }

    @Override
    public String toString(){
        return "Swap";
    }
}