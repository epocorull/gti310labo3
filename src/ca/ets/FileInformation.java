package ca.ets;

import java.util.ArrayList;

public class FileInformation {
    public int nb_sommet;
    public long val_infinie;
    public int sommet_depart;
    public ArrayList< int[] > chemins;

    public FileInformation(int nb_sommet, long val_inf, int sommet_depart) {
        this.nb_sommet = nb_sommet;
        this.val_infinie = val_inf;
        this.sommet_depart = sommet_depart;
        this.chemins = new ArrayList<>();
    }

    public int getNb_sommet() {
        return nb_sommet;
    }

    public long getVal_inf() {
        return val_infinie;
    }

    public int[] chemin(int line) {
        return chemins.get(line);
    }

    public void setChemin(int[] chemin) {
        this.chemins.add(chemin);
    }
}

