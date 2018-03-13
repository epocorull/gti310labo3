package ca.ets.solver;

import java.util.ArrayList;

public class Matrice_de_poid {

    private int[][] matrice;
    private ArrayList<int[]> listeArc;

    public Matrice_de_poid(ArrayList<int[]> listeArc, int nb_sommet) {
        this.listeArc = listeArc;
        this.matrice = new int[nb_sommet][nb_sommet];

        //On initialise chaque case de la matrice à -1
        for (int i = 0; i < nb_sommet; i++) {
            for (int j = 0; j < nb_sommet; j++) {
                matrice[i][j] = -1;
            }
        }
    }


    public void calcul() {
        int ligne;
        int colonne;
        int poid;

        //On traite chaque element de la liste des arcs pour créer la matrice de poids
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