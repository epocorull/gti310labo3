package ca.ets.solver;

import java.util.ArrayList;

/**
 * This class is used to find an eulerian cycle.
 * Instances of EulerianCycle are created by a ConcreteSolver instance.
 * This class will find only one eulerian cycle and will keep it in the cycle_eulerien attribute.
 *
 * @author Elsa Pocorull Valentin Gaillard
 */
public class CycleEulerien {
	
	private int[][] matriceDePoid ;
	private int[] cycle_eulerien ;
	private int sommetDepart ;

    /**
     * Constructor is called by ConcreteSolver.
     * @param listeArc The list of the roads in the town
     * @param sommetDepart The starting summit
     * @param nb_sommet The number of summits
     */
	public CycleEulerien(ArrayList<int[]> listeArc, int sommetDepart, int nb_sommet)
	{

		MatriceDePoid matrice = new MatriceDePoid(listeArc, nb_sommet) ;
		matrice.calcul();
		this.matriceDePoid = matrice.getMatriceDePoid() ;
		this.sommetDepart = sommetDepart ;
		this.cycle_eulerien = new int[listeArc.size() + 1];
	}


    /**
     * The calcul method is designed to find one eulerian cycle.
     * To see more details about the explanation of the algorithm :
     * https://www.enseignement.polytechnique.fr/informatique/INF431/X12-2013-2014/PC/pc-cpp/pc-corrige.pdf
     *
     * Complexity:
     * O( n² + m*n² + 2n² +n +n² +m ) = O((m+2)*n² +m) with n, number of summits and m number of roads.
     *
     * But, m, the number of roads can be (in the worse case) n². In this case, the complexity is O(n²*n²).
     */
	public void calcul() {

        boolean matriceNonVide = true;
        // Ce boolean est vrai tant qu'il y a des routes non traitées.
        int tailleMatrice = matriceDePoid.length;
        int longueurCycle;
        // Cet entier représente la taille du cycle trouvé. Il change au cours de la methode.
        int sommet = sommetDepart;

        ArrayList<Integer> PartieCycle2;
        ArrayList<Integer> PartieCycle1 = new ArrayList<>();
        ArrayList<Integer> PartieCycle3 = new ArrayList<>();

        ArrayList<Integer> cycleIntermediaire;


        // On commence par trouver un premier cycle non eulérien.
        // Ce cycle part du sommet de départ (pour Grosse-Neige, le sommet 1).
        FindCycle premierCycle = new FindCycle(matriceDePoid, sommet);
        premierCycle.calcul();   // O(n²) avec n: nombre de sommets.
        cycleIntermediaire = premierCycle.getCycle();
        // récupération de la nouvelle matrice de poids. Les chemins utilisés en ont été retirés.
        this.matriceDePoid = premierCycle.getMatriceDePoid();
        longueurCycle = cycleIntermediaire.size();

        //On boucle tant que la matrice de poids contient des arcs
        // Pire cas: O(m) où m est le nombre d'arcs.
        while (matriceNonVide) {
            matriceNonVide = false;

            // On trouve un sommet non isolé qui servira de départ à un nouveau cycle.
            // Le nouveau cycle sera ensuite inséré dans le premier cycle à l'endroit du sommet non isolé.
            // On boucle sur tous les sommets présents dans le cycle, c'est a dire au pire n sommets: O(n)
            for (int ind_elmt = 0; ind_elmt < longueurCycle; ind_elmt++) {
                int elmt = cycleIntermediaire.get(ind_elmt);
                int col = 1;
                while (col <= tailleMatrice ) { // Pire cas: O(n)
                    // Pour chaques sommets du cycle on regarde si il existe une route non traitée dans la matrice.
                    if (matriceDePoid[elmt - 1 ][col -1] != -1) {

                        //On recopie la première partie du cycle dans un tableau,
                        // c'est à dire la partie du cycle avant le sommet qui sera traité.
                        // Pire cas: O(n) pour les deux recopies.
                        PartieCycle1 = new ArrayList<>();
                        for (int k = 0; k < ind_elmt; k++) {
                            PartieCycle1.add(cycleIntermediaire.get(k));
                        }

                        //On recopie la deuxième partie du cycle
                        PartieCycle3 = new ArrayList<>();
                        for (int k = ind_elmt + 1; k < longueurCycle; k++) {
                            PartieCycle3.add(cycleIntermediaire.get(k));
                        }

                        //elmt devient le sommet de départ du prochain petit cycle cherché par FindCycle.
                        sommet = elmt;

                        //On sort des boucles for
                        elmt = longueurCycle;
                        col = tailleMatrice + 1;
                    } else col = col + 1;
                }
            }

            //On trouve un nouveau cycle à partir du nouveau sommet non isolé, et on modifie la matrice de poid.
            // O(n²)
            FindCycle nouveauCycle = new FindCycle(matriceDePoid, sommet);
            nouveauCycle.calcul();
            PartieCycle2 = nouveauCycle.getCycle();
            // Récupération de la nouvelle matrice de poids.
            this.matriceDePoid = nouveauCycle.getMatriceDePoid();

            cycleIntermediaire = new ArrayList<>();

            //On remplie de nouveau le cycle intermediaire avec le petit cycle inséré au milieu
            // O(n)
            cycleIntermediaire.addAll(PartieCycle1);
            cycleIntermediaire.addAll(PartieCycle2);
            cycleIntermediaire.addAll(PartieCycle3);

            longueurCycle = cycleIntermediaire.size();


            //On regarde si il reste des chemins non traités dans la matrice de poids.
            // O(n²)
            for (int[] aMatriceDePoid : matriceDePoid) {
                for (int j = 0; j < tailleMatrice; j++) {
                    if (aMatriceDePoid[j] > 0) {
                        matriceNonVide = true;
                    }
                }
            }
        }


    //Quand il n'y a plus d'arc, on remplie le cycle eulerien
    //  O(m)
    for (int i=0; i< cycleIntermediaire.size(); i++ ) this.cycle_eulerien[i] = cycleIntermediaire.get(i);

	}

	public int[] getResult()
	{
		return this.cycle_eulerien ;
	}
}
