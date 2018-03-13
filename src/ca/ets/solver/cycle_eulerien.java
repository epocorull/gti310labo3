package cycle_eulerien;

public class cycle_eulerien {
	
	private int[][] matriceDePoid ;
	private int[] cycle_eulerien ;
	private int sommetDepart ;
	private int tailleCycleEulerien ;
	
	public cycle_eulerien(int[][] listeArc, int sommetDepart) 
	{
		
		matrice_de_poid matrice = new matrice_de_poid(listeArc) ;
		this.matriceDePoid = matrice.getMatriceDePoid() ;
		this.sommetDepart = sommetDepart ;
		this.tailleCycleEulerien = matrice.getTailleCycle() ;
	}
	
	
	
	public void calcul() 
	{
		boolean matriceNonVide = true ;
		int tailleMatrice = matriceDePoid.length ;
		int longueurCycle = 0 ;
		int sommet = sommetDepart ;
		int tailleMax = tailleMatrice*tailleMatrice ;
		int[] PartieCycle1 = new int[tailleMax];
		int[] PartieCycle2 = new int[tailleMax];
		int[] PartieCycle3 = new int[tailleMax];
		int longueur1 ;
		int longueur2 ;
		int longueur3 ;
		int tailleCycle = this.tailleCycleEulerien ;
		int[] cycleIntermediaire = new int[tailleCycle] ;
		
		
		//On commence par trouver un cycle
		FindCycle premierCycle = new FindCycle(matriceDePoid, sommet) ;
		PartieCycle2 = premierCycle.getCycle() ;
		matriceDePoid = premierCycle.getMatriceDePoid() ;
		
		//On boucle tant que la matrice n'est pas vide
		while (matriceNonVide == true)
		{
			matriceNonVide = false ;

			//On trouve un nouveau sommet de départ
			for (int elmt = 0 ; elmt < longueurCycle ; elmt++)
			{
				for (int i = 0 ; i < tailleMatrice ; i ++)
				{
					for (int j = 0 ; j < tailleMatrice ; j ++)
					{
						//On trouve un élément de la matrice de poid non vide ET présent dans un le cycle
						if (matriceDePoid[i][j] != -1 && i == cycle_eulerien[elmt])
						{
							//On remplie la première partie du cycle
							for (int k = 0 ; k < elmt ; k++)
							{
								PartieCycle1[k] = cycle_eulerien[k] ;
							}
							
							//On remplie la troisieme partie du cycle
							for (int k = elmt ; k < longueurCycle ; k++)
							{
								PartieCycle3[k-elmt] = cycle_eulerien[k] ;
							}
							
							//elmt devient le sommet de départ du prochain cycle
							sommet = elmt ;
							
							//On sort des boucles for
							elmt = longueurCycle ;
							i = tailleMatrice ;
							j = tailleMatrice ;
						}
					}
				}
			}
			
			//On trouve un nouveau cycle et on modifie la matrice de poid
			FindCycle nouveauCycle = new FindCycle(matriceDePoid, sommet) ;
			PartieCycle2 = nouveauCycle.getCycle() ;
			matriceDePoid = nouveauCycle.getMatriceDePoid() ;
			
			//On trouve les longueurs de chaque morceau de cycle
			longueur1 = PartieCycle1.length ;
			longueur2 = PartieCycle2.length ;
			longueur3 = PartieCycle3.length ;
			
			//On remplie un cycle intermediaire
			//qui sera notre cycle eulerien après la dernière itération
			for (int i = 0; i < longueur1 ; i ++) 
			{
				cycleIntermediaire[i] = PartieCycle1[i] ;
			}
			
			for (int i = 0; i < longueur2 ; i ++) 
			{
				cycleIntermediaire[longueur1 + i] = PartieCycle1[i] ;
			}
			
			for (int i = 0; i < longueur3 ; i ++) 
			{
				cycleIntermediaire[longueur1 + longueur2 + i] = PartieCycle1[i] ;
			}
			
			
			
			//On regarde si il reste des éléments dans la matrice de poids
			for(int i = 0 ; i < tailleMatrice ; i ++)
			{
				for(int j = 0 ; j < tailleMatrice ; j ++)
				{
					if (matriceDePoid[i][j] > 0)
					{
						matriceNonVide = true ;
					}
				}
			}
			
		}
		
		//On remplie le cycle eulerien
		this.cycle_eulerien = cycleIntermediaire ;

	}
	
	
	
	
	public int[] getResult()
	{
		return this.cycle_eulerien ;
	}
	
}
