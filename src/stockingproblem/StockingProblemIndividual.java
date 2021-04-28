package stockingproblem;

import algorithms.Algorithm;
import algorithms.IntVectorIndividual;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes

    public StockingProblemIndividual(StockingProblem problem, int size) {
        super(problem, size);
        //TODO

        for (int i=0; i < size; i++) {
            genome[i] = i;
        }

        for (int i = 0; i < genome.length; i++) {
            int randomIndexToSwap = Algorithm.random.nextInt(genome.length);
            int temp = genome[randomIndexToSwap];
            genome[randomIndexToSwap] = genome[i];
            genome[i] = temp;
        }
    }

    public void placePiece(Item item, int[][] material, int lineIndex, int columnIndex){
        for (int i = 0; i < material.length; i++) {
            for (int j = 0; j < material[i].length; j++) {
                material[lineIndex + i][columnIndex + j] = (char) item.getId();
                //x + y * with
            }
        }
    }

    public StockingProblemIndividual(StockingProblemIndividual original) {
        super(original);
        //TODO
    }

    @Override
    public double computeFitness() {
        //TODO
        int[][] material = new int[genome.length][problem.getMaxWidth()];

        //Contar os cortes verticais

        //Contar os cortes  horizontais

        return this.fitness;
    }

    private boolean checkValidPlacement(Item item, int[][] material, int lineIndex, int columnIndex) {
        int[][] itemMatrix = item.getMatrix();
        for (int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                if (itemMatrix[i][j] != 0) {
                    if ((lineIndex + i) >= material.length
                            || (columnIndex + j) >= material[0].length
                            || material[lineIndex + i][columnIndex + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        //TODO
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(StockingProblemIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public StockingProblemIndividual clone() {
        return new StockingProblemIndividual(this);

    }
}