package ca.ets;

import java.util.ArrayList;

/**
 * This class is designed to keep information provided by the input file.
 * The number of tops, the value of the infinite, the starting summit and the list of way constitute information.
 *
 * @author Elsa Pocorull, Valentin Gaillard
 */
public class FileInformation {
    public int nb_sommet;
    public long val_infinie;
    public int sommet_depart;
    public ArrayList< int[] > chemins;

    /**
     * FileInformation is called by a ConcreteParser instance.
     * All the parameters are extracted from the input file.
     * @param nb_sommet the number of summits
     * @param val_inf value of infinite
     * @param sommet_depart starting summit
     */
    public FileInformation(int nb_sommet, long val_inf, int sommet_depart) {
        this.nb_sommet = nb_sommet;
        this.val_infinie = val_inf;
        this.sommet_depart = sommet_depart;
        this.chemins = new ArrayList<>();
    }

    /**
     * This method is used to set a new way in the list.
     * @param chemin A way represents by a board.
     */
    public void setChemin(int[] chemin) {
        this.chemins.add(chemin);
    }
}

