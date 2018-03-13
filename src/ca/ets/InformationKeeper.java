package ca.ets;

import java.util.ArrayList;

public class InformationKeeper {
    public String[] cyclesEulerien;
    private ArrayList<int[]> dijkstra;

    public InformationKeeper(ArrayList<int[]> dijkstraResultat) {
        this.dijkstra = dijkstraResultat;
        this.cyclesEulerien = new String[dijkstraResultat.size()];
    }

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
