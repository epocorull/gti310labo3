package ca.ets.solver;

import java.util.ArrayList;

/**
 * The objects of this class are matrix.
 * The matrix represents the roads in the town.
 *
 * @author Elsa Pocorull, Valentin Gaillard
 */
public class MatriceDePoid {

    private int[][] matrice;
    private ArrayList<int[]> listeArc;

    /**
     * The constructer of the matrix is called by instances of CycleEulerien.
     * Dimensions of the matrix are the numbers of ways.
     * The matrix is iniatialized with -1 for all elements
     * (there is no negative weight but it could be the value of infinite)
     *
     * @param listeArc The list of the roads.
     * @param nb_sommet The number of summits.
     *
     * Complexity O(n*n), n is the number of summits.
     */
    public MatriceDePoid(ArrayList<int[]> listeArc, int nb_sommet) {
        this.listeArc = listeArc;
        this.matrice = new int[nb_sommet][nb_sommet];

        //On initialise chaque case de la matrice à -1
        for (int i = 0; i < nb_sommet; i++) {
            for (int j = 0; j < nb_sommet; j++) {
                matrice[i][j] = -1;
            }
        }
    }


    /**
     * The calcul method set the weights into the matrix.
     * The weight of each road is added. So, the complexity is on O(n) where n is the number of roads.
     */
    public void calcul() {
        int ligne;
        int colonne;
        int poid;

        // On traite chaque element de la liste des arcs pour créer la matrice de poids
        // Pour chaque chemin, on enregistre le poids à la case située à
        // la ligne représentant le sommet de départ et la colonne représentant le sommet d'arrivée.
        for (int[] aListeArc : listeArc) {
            ligne = aListeArc[0];
            colonne = aListeArc[1];
            poid = aListeArc[2];

            matrice[ligne -1][colonne -1 ] = poid;
        }

    }


    public int[][] getMatriceDePoid() {
        return matrice;
    }
}