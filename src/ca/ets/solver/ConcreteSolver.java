package ca.ets.solver;

import ca.ets.FileInformation;
import ca.ets.InformationKeeper;

import java.util.ArrayList;

public class ConcreteSolver implements Solver {

    @Override
    public InformationKeeper solve(FileInformation fileInformation) {

        if(verification()) {
            ArrayList<int[]> cycles = new ArrayList<>();

            CycleEulerien premier_cycle = new CycleEulerien(fileInformation.chemins,
                    fileInformation.sommet_depart,
                    fileInformation.nb_sommet);
            premier_cycle.calcul();
            cycles.add(premier_cycle.getResult());

            InformationKeeper informationKeeper = new InformationKeeper(cycles);
            informationKeeper.setCyclesEulerien();
            return informationKeeper ;
        }
        else {
            System.out.println("Il n'y a pas de chemins parfaits ! ");
                return null;
            }
    }

    private boolean verification() {
        // compter à partir de la matrice de pds !
        return true;
    }
}
