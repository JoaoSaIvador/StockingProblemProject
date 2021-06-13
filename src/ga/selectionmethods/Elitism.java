package ga.selectionmethods;

import algorithms.Individual;
import algorithms.Problem;
import ga.GeneticAlgorithm;
import ga.Population;
import ga.geneticoperators.Mutation;
import ga.geneticoperators.Recombination;

public class Elitism <I extends Individual, P extends Problem<I>> extends SelectionMethod <I, P>{
    SelectionMethod<I, P> selection;

    public Elitism(int popSize, SelectionMethod<I, P> selection) {
        super(popSize);
        this.selection = selection;
    }

    @Override
    public Population<I, P> run(Population<I, P> original) {
        I best = original.getBest();
        Population<I, P> pop2 = selection.run(original);

        //recombination.run(pop2);
        //mutation.run(pop2);

        I item = pop2.getIndividual(GeneticAlgorithm.random.nextInt(popSize));
        pop2.removeIndividual(item);
        pop2.addIndividual(best);

        return pop2;
    }

    @Override
    public String toString(){
        return "Elitism";
    }
}
