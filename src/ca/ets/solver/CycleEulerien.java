package ca.ets.solver;

import java.util.ArrayList;

public class CycleEulerien {
	
	private int[][] matriceDePoid ;
	private int[] cycle_eulerien ;
	private int sommetDepart ;

	public CycleEulerien(ArrayList<int[]> listeArc, int sommetDepart, int nb_sommet)
	{

		Matrice_de_poid matrice = new Matrice_de_poid(listeArc, nb_sommet) ;
		matrice.calcul();
		this.matriceDePoid = matrice.getMatriceDePoid() ;
		this.sommetDepart = sommetDepart ;
		this.cycle_eulerien = new int[listeArc.size() + 1];
	}
	
	
	
	public void calcul() {

        boolean matriceNonVide = true;
        int tailleMatrice = matriceDePoid.length;
        int longueurCycle;
        int sommet = sommetDepart;

        ArrayList<Integer> PartieCycle2;
        ArrayList<Integer> PartieCycle1 = new ArrayList<>();
        ArrayList<Integer> PartieCycle3 = new ArrayList<>();

        ArrayList<Integer> cycleIntermediaire;


        //On commence par trouver un premier cycle non eulérien
        FindCycle premierCycle = new FindCycle(matriceDePoid, sommet);
        premierCycle.calcul();
        cycleIntermediaire = premierCycle.getCycle();
        this.matriceDePoid = premierCycle.getMatriceDePoid();
        longueurCycle = cycleIntermediaire.size();

        //On boucle tant que la matrice de poids contient des arcs
        while (matriceNonVide) {
            matriceNonVide = false;

            //On trouve un sommet non isolé qui servira de départ à un nouveau cycle
            for (int ind_elmt = 0; ind_elmt < longueurCycle; ind_elmt++) {
                int elmt = cycleIntermediaire.get(ind_elmt);
                int col = 1;
                while (col <= tailleMatrice ) {
                    //On trouve un sommet (elmt) présent dans un le cycle qui est non isolé
                    if (matriceDePoid[elmt - 1 ][col -1] != -1) {

                        //On recopie la première partie du cycle dans un tableau
                        PartieCycle1 = new ArrayList<>();
                        for (int k = 0; k < ind_elmt; k++) {
                            PartieCycle1.add(cycleIntermediaire.get(k));
                        }

                        //On recopie la deuxième partie du cycle
                        PartieCycle3 = new ArrayList<>();
                        for (int k = ind_elmt + 1; k < longueurCycle; k++) {
                            PartieCycle3.add(cycleIntermediaire.get(k));
                        }

                        //elmt devient le sommet de départ du prochain cycle
                        sommet = elmt;

                        //On sort des boucles for
                        elmt = longueurCycle;
                        col = tailleMatrice + 1;
                    } else col = col + 1;
                }
            }

            //On trouve un nouveau cycle à partir du nouveau sommet et on modifie la matrice de poid
            FindCycle nouveauCycle = new FindCycle(matriceDePoid, sommet);
            nouveauCycle.calcul();
            PartieCycle2 = nouveauCycle.getCycle();
            this.matriceDePoid = nouveauCycle.getMatriceDePoid();

            cycleIntermediaire = new ArrayList<>();

            //On remplie de nouveau le cycle intermediaire avec le petit cycle inséré au milieu
            cycleIntermediaire.addAll(PartieCycle1);
            cycleIntermediaire.addAll(PartieCycle2);
            cycleIntermediaire.addAll(PartieCycle3);

            longueurCycle = cycleIntermediaire.size();


            //On regarde si il reste des éléments dans la matrice de poids
            for (int[] aMatriceDePoid : matriceDePoid) {
                for (int j = 0; j < tailleMatrice; j++) {
                    if (aMatriceDePoid[j] > 0) {
                        matriceNonVide = true;
                    }
                }
            }
        }


    //Quand il n'y a plus d'arc, on remplie le cycle eulerien
    for (int i=0; i< cycleIntermediaire.size(); i++ ) this.cycle_eulerien[i] = cycleIntermediaire.get(i);

	}

	public int[] getResult()
	{
		return this.cycle_eulerien ;
	}
}
