package ca.ets;

import java.util.ArrayList;

/**
 * This class keeps the solution into a board of String.
 * InformationKeeper is read by ConcreteWriter in order to write the solution.
 *
 * @author Elsa Pocorull, Valentin Gaillard
 */
public class InformationKeeper {
    public String[] cyclesEulerien;
    private ArrayList<int[]> dijkstra;

    /**
     * The constructer is called by an instance of ConcreteSolver.
     * The solution is passed as a parameter.
     * @param dijkstraResultat the solution found by ConcreteSolver is a board filled with boards of int.
     *                         Each board represents one way.
     */
    public InformationKeeper(ArrayList<int[]> dijkstraResultat) {
        this.dijkstra = dijkstraResultat;
        this.cyclesEulerien = new String[dijkstraResultat.size()];
    }

    /**
     * The setCyclesEulerien method is designed to fill the cyclesEulerien board
     * with solution writed in a correct format.
     */
    public void setCyclesEulerien() {
        for (int line = 0; line < dijkstra.size(); line++) {
            StringBuilder Line = new StringBuilder();
            for (int col = 0; col< dijkstra.get(line).length; col ++)

                if (col == 0)
                   Line = new StringBuilder(String.format(" %s", dijkstra.get(line)[0]));

               else Line.append(String.format(" -> %s", dijkstra.get(line)[col]));

           cyclesEulerien[line] = Line.toString();
        }
    }

    public String[] getCyclesEulerien() {
        return cyclesEulerien;
    }
}
