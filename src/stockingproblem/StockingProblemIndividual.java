package stockingproblem;

import algorithms.Algorithm;
import algorithms.IntVectorIndividual;

import java.util.Arrays;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private int[][] material;

    public StockingProblemIndividual(StockingProblem problem, int size) {
        super(problem, size);
        //TODO
        this.material = new int[problem.getMaterialHeight()][problem.getMaxWidth()];

        for (int i=0; i < size; i++) {
            genome[i] = i;
        }

        for (int i = 0; i < genome.length; i++) {
            int randomIndexToSwap = Algorithm.random.nextInt(genome.length);
            int temp = genome[randomIndexToSwap];
            genome[randomIndexToSwap] = genome[i];
            genome[i] = temp;
        }

        /*for (int i : genome) {
            Item item = problem.getItems().get(i);
            System.out.println(item + " letra " + item.getRepresentation());
        }*/

        for (int i = 0; i < genome.length; i++) {
            Item itemAux = problem.getItems().get(genome[i]);
            boolean valid = false;
            for (int column = 0; column < problem.getMaxWidth(); column++) {
                for (int row = 0; row < problem.getMaterialHeight(); row++) {
                    if (valid = checkValidPlacement(itemAux, row , column)){
                        placePiece(itemAux, row, column);
                        break;
                    }
                }
                if (valid){
                    break;
                }
            }
        }
    }

    public StockingProblemIndividual(StockingProblemIndividual original) {
        super(original);
        //TODO
        this.material = original.material;

    }

    public void placePiece(Item item, int lineIndex, int columnIndex){
        for (int i = 0; i < item.getLines(); i++) {
            for (int j = 0; j < item.getColumns(); j++) {
                material[lineIndex + i][columnIndex + j] = item.getRepresentation();
            }
        }
    }

    @Override
    public double computeFitness() {
        //TODO
        int cuts = 0;
        //usar o menor numero de colunas

        for (int i = 0; i < material.length; i++) {
            for (int j = 0; j < material[i].length; j++) {
                if (j == material[i].length-1) {
                    break;
                }

                //Contar os cortes verticais
                if ((char)material[i+1][j] != (char) material[i][j]) {
                    cuts++;
                }

                //Contar os cortes  horizontais
                if ((char) material[i][j+1] != (char) material[i][j]) {
                    cuts++;
                }
            }
        }

        System.out.println(cuts);

        return this.fitness;
    }

    private boolean checkValidPlacement(Item item, int lineIndex, int columnIndex) {
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
        sb.append("Fitness: ");
        sb.append(fitness);
        //TODO
        sb.append("\n");
        sb.append("Genome: ");
        sb.append(Arrays.toString(genome)).append("->").append((genome.length));
        sb.append("\n");
        sb.append("Best Solution Matrix: \n");
        for (int i = 0; i < problem.getMaterialHeight(); i++) {
            sb.append("[ ");
            for (int j = 0; j < problem.getMaxWidth(); j++) {
                char pieceLetter = (char) material[i][j];
                sb.append(pieceLetter == 0 ? "-" : pieceLetter).append(" ");
            }
            sb.append("]\n");
        }
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