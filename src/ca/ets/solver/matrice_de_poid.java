package cycle_eulerien;

public class matrice_de_poid {
	private int[][] matrice ;
	private int[][] listeArc ;
	private int tailleCycle ;
	
	public matrice_de_poid(int[][] listeArc) {
		this.listeArc = listeArc ;
		this.tailleCycle = listeArc.length ;
	}

	
	
	public void calcul() 
	{
		int tailleMatrice = matrice.length ;
		int nbArc = listeArc.length ;
		int ligne ;
		int colonne ;
		int poid ;
		
		//On initialise chaque case de la matrice à -1
		for (int i = 0 ; i <tailleMatrice; i++ ) 
		{
			for (int j = 0 ; j <tailleMatrice; j++ ) 
			{
				matrice[i][j] = -1 ;
			}
		}
		
		//On traite chaque element de la liste des arcs
		for (int i = 0 ; i < nbArc ; i++ )
		{
			ligne = listeArc[i][0] ;
			colonne = listeArc[i][1] ;
			poid = listeArc[i][2] ;
			
			matrice[ligne][colonne] = poid ;
		}
	}
	
	
	
	public int[][] getMatriceDePoid()
	{	
		return this.matrice ;
	}
	
	public int getTailleCycle()
	{
		return this.tailleCycle ;
	}
}
