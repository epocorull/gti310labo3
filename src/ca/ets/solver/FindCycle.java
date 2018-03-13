package ca.ets.solver;

import java.util.ArrayList;

public class FindCycle {
	
	private ArrayList<Integer> cycle ;
	private int[][] matriceDePoid ;
	private int sommetDepart ;
	
	public FindCycle(int[][] matriceDePoid, int sommetDepart) 
	{
		this.matriceDePoid = matriceDePoid;
		this.sommetDepart = sommetDepart;
		this.cycle = new ArrayList<>();
	}
	
	
	public void calcul() 
	{
		boolean verif = true ;
		int longueurMat = matriceDePoid.length ;
		int sommetCourant = sommetDepart ;
		int elmtRestant = 1;   //premier element non null d'une ligne de la matrice de poid
        cycle.add(sommetDepart);
		
		
		//on boucle tant que le sommet de départ a toujours des arcs non utilisés dans un cycle (sommet non isolé)
		while (verif) {
			verif = false ; 

			//On verifie s'il y a encore des arcs qui partent du sommet choisi
			for (int i = 0 ; i < longueurMat ; i++ ) {
				if (matriceDePoid[sommetDepart - 1 ][i] > 0)   //-1 veut dire que la case est vide
				{ 
					verif = true ;
					i=longueurMat;
				}
			}
			
			//On cherche le prochain element du cycle
			while (matriceDePoid[sommetCourant - 1][elmtRestant -1] < 0 && elmtRestant < matriceDePoid.length )
			{
				elmtRestant = elmtRestant + 1 ;
			}

			cycle.add(elmtRestant);

			//On enlève le chemin qu'on vient de rajouter au cycle de la matrice de poid
			matriceDePoid[sommetCourant - 1][elmtRestant -1] = -1 ;
			
			//Le sommet courant devient le dernier element du cycle
			sommetCourant = elmtRestant ;
			elmtRestant = 1;
		}
	}
	
	public ArrayList<Integer> getCycle()
	{
		
		return cycle ;
	}

	public int[][] getMatriceDePoid() {
	    return this.matriceDePoid;
	}
	
}
