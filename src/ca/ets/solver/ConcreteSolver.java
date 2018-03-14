package ca.ets.solver;

import ca.ets.FileInformation;
import ca.ets.InformationKeeper;

import java.util.ArrayList;

public class ConcreteSolver implements Solver {

    @Override
    public InformationKeeper solve(FileInformation fileInformation) {

        if(verification(fileInformation)) {
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

    private boolean verification(FileInformation fileInformation) {
        // Selon le théorème d'Euler, il existe au moins un cycle eulérien si le nombre d'arrètes entrantes et sortantes
        // de chaques sommets est pair.
        // On utilise la matrice de poids pour faire cette vérification.

        ArrayList<int[]> ListeArc = fileInformation.chemins;
        int[] sommets = new int[fileInformation.nb_sommet + 1];

        for (int[] chemin: ListeArc ) {
            sommets[chemin[0]] = sommets[chemin[0]] + 1;
            sommets[chemin[1]] = sommets[chemin[1]] + 1;
        }

        for (int sommet: sommets ) {
            if (sommet % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
