package ca.ets.solver;

import java.util.ArrayList;

/**
 * This class is used to find a cycle. A cycle start and finish in the same summit.
 * Objects of FindCycle class are created by instances of CycleEulerien.
 *
 * For example, in the first call, this class will keep the first cycle in the cycle attribute : [1, 8, 1]
 *
 * @author Elsa Pocorull Valentin Gaillard
 */
public class FindCycle {
	
	private ArrayList<Integer> cycle ;
	private int[][] matriceDePoid ;
	private int sommetDepart ;

    /**
     * The FindCycle constructor is called by CycleEulerien. The starting summit and the current matrix are given.
     * Indeed the matrix is changed. When roads are added to a cycle, the roads disappear from the matrix.
     *
     * @param matriceDePoid the current matrix.
     * @param sommetDepart the starting summit chose for this cycle.
     */
	public FindCycle(int[][] matriceDePoid, int sommetDepart)
	{
		this.matriceDePoid = matriceDePoid;
		this.sommetDepart = sommetDepart;
		this.cycle = new ArrayList<>();
	}


    /**
     * The calcul method find a cycle and put it into the cycle attribute.
     *
     * In order to do that, the matrix of weigh is used.
     * At the end, all roads presents in the cycle are deleted from the matrix.
     *
     * More details on: https://www.enseignement.polytechnique.fr/informatique/INF431/X12-2013-2014/PC/pc-cpp/pc-corrige.pdf
     *
     * Complexity: O(n*(n+n))=O(n²) with n: number of summits.
     */
	public void calcul()
	{
		boolean verif = true ;
		int longueurMat = matriceDePoid.length ;
		int sommetCourant = sommetDepart ;
        //premier element non null d'une ligne de la matrice de poids
		int elmtRestant = 1;
        cycle.add(sommetDepart);
		
		
		//on boucle tant que le sommet de départ a toujours des arcs non utilisés dans un cycle (sommet non isolé)
        // Pire cas: O(n) avec n le nombre de sommet. (Un aller retour depuis le sommet de base vers chaque autre sommets.)
        while (verif) {
		    verif = false ;

			//On verifie s'il y a encore des arcs qui partent du sommet choisi (sommet non isolé)
            // Pire cas: O(n)
			for (int i = 0 ; i < longueurMat ; i++ ) {
                // O(1)
				if (matriceDePoid[sommetDepart - 1 ][i] > 0)   //-1 veut dire que la case est vide
				{ 
					verif = true ; // O(1)
					i=longueurMat; // O(1)
				}
			}
			
			//On cherche le prochain element du cycle
            // Pire cas: O(n)
			while (matriceDePoid[sommetCourant - 1][elmtRestant -1] < 0 && elmtRestant < matriceDePoid.length )
			{
				elmtRestant = elmtRestant + 1 ; // O(1)
			}

			cycle.add(elmtRestant); // O(1)

			//On enlève le chemin  de la matrice de poids qu'on vient de rajouter au cycle
			matriceDePoid[sommetCourant - 1][elmtRestant -1] = -1 ; // O(1)
			
			//Le sommet courant devient le dernier element du cycle
			sommetCourant = elmtRestant ; // O(1)
			elmtRestant = 1; // O(1)
		}
	}
	
	public ArrayList<Integer> getCycle() {return cycle; }

	public int[][] getMatriceDePoid() {return this.matriceDePoid; }
	
}
