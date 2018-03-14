package ca.ets.solver;

import ca.ets.FileInformation;
import ca.ets.InformationKeeper;

import java.util.ArrayList;

/**
 * This class find the solution.
 * First, a check of the existence of a solution is done.
 * Then, we looking for a solution with the method explain in the article :
 *   https://www.enseignement.polytechnique.fr/informatique/INF431/X12-2013-2014/PC/pc-cpp/pc-corrige.pdf
 *
 * @author Elsa Pocorull, Valentin Gaillard
 */
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

    /**
     * The verification method checks if there is a perfect way.
     * The Euler Theorem is used to do this check.
     *
     * @param fileInformation An instance of FileInformation in which information is kept.
     * @return A boolean the possibility of a perfect way.
     *
     * Complexity : O(n+m) where n is the number of ways and m the number of summits.
     *
     * But in the worse case m=n², if all summits are linked in the two side with every other summit.
     *
     * So, the complexity is O(n²)
     */
    private boolean verification(FileInformation fileInformation) {
        // Selon le théorème d'Euler, il existe au moins un cycle eulérien si le nombre d'arrètes entrantes et sortantes
        // de chaques sommets est pair.
        // On utilise la liste des chemins entre les sommets pour faire cette vérification.

        ArrayList<int[]> ListeArc = fileInformation.chemins;
        int[] sommets = new int[fileInformation.nb_sommet + 1];

        for (int[] chemin: ListeArc ) {       // Complexité, O(n), n: nombre de chemins
            sommets[chemin[0]] = sommets[chemin[0]] + 1;
            sommets[chemin[1]] = sommets[chemin[1]] + 1;
        }

        for (int sommet: sommets ) {   // Complexité O(m) m: nombre de sommets.
            if (sommet % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
