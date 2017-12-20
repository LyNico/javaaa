package librInterpreteur;

import javafx.scene.control.Button;
import java.util.Vector;

public class Grille {
	
	private Case[][] tab;
	Case newCase;
	int l; // longueur
	int L; // Largeur
	Vector<Integer> randomLarg;
	Vector<Integer> randomLong;
	private int difficulty;
	private ListSymboles symbs;
	
    public Grille(int longueur, int largeur) {
    	l = longueur;
    	L = largeur;
        tab = new Case[l][L];
        // ICI on regle le nombre de paires de symboles sur la grille
        difficulty = 2;
        symbs = new ListSymboles(difficulty, longueur, largeur);
        int nbCurrSymb = 0;
        while(!symbs.getValue().empty()) {
	        for(int i =0; i<l; i++)
	        	for(int k=0; k<L; k++) {
	        		if(((k == symbs.getValue().peek().getSecond() && i == symbs.getValue().peek().getThird()) || (k == symbs.getValue().peek().getFourth() && i == symbs.getValue().peek().getFifth())) && nbCurrSymb < 2) {
	        			System.out.println(" k == " +k+" i == "+i+"");
	        			System.out.println("creation de la case avec la lettre : "+symbs.getValue().peek().getFirst());
	        			newCase = new Case(symbs.getValue().peek().getFirst(),i,k);
	        			newCase.setContent(symbs.getValue().peek().getFirst());
		        		tab[i][k] = newCase;
		        		nbCurrSymb += 1;
	        		} else {
	        			if (tab[i][k] == null ) {
			        		newCase = new Case("O",i,k);
			        		newCase.setContent("O");
			        		tab[i][k] = newCase;
	        			}
	        		}
	        	}
	        nbCurrSymb = 0;
	        symbs.popFirstInList();
	        System.out.println("taille de la pile a la fin du while : " + symbs.getValue().size());
        }
    }
    public Case getCaseTab(int x, int y) {
    	return tab[x][y];
    }
	public Case getButtonNewCase() {
		return newCase;
	}
    public void setCaseTab(int x, int y, String str) {
    	Case temp = tab[x][y];
    	temp.setContent(str);
    	temp.getNewCase().setText(str);
    }
    public void startDragNDrop(int i, int j) {
    	
    }
    public boolean voisins(Button c, Button d) {
    	return false;
    }
    public int orientationCasePrecedente() {
    	return 0;
    }
    public void resetAll() {
    	for(int i =0; i<tab.length; i++)
        	for(int k=0; k<tab[i].length; k++) {
        		Case temp = tab[i][k];
        		if(!symbs.isSymbole(temp.getContent())) {
        			this.setCaseTab(i,k,"O");
        		}
        		temp.setLinked(false);
        		temp.setHoovered(false);
        	}
    }
    public ListSymboles getSymbs() {
    	return symbs;
    }
    public int getDifficulty() {
    	return difficulty;
    }
    public boolean estVoisine(Case c, Case d) {
    	// voisines des cases dans les coins
		if((c.getPosX() == 0 && c.getPosY() == 0)) { // haut gauche
			if((d.getPosX() == c.getPosX()+1) || (d.getPosY() == c.getPosY()+1)) {
				return true;
			}
		}
		else if((c.getPosX() == 0 && c.getPosY() == L-1) ) { // haut droite
			if((d.getPosX() == c.getPosX()+1) || (d.getPosY() == c.getPosY()-1)) {
				return true;
			}
		}
		else if((c.getPosX() == l-1 && c.getPosY() == 0)) { // bas gauche
			if((d.getPosX() == c.getPosX()-1) || (d.getPosY() == c.getPosY()+1)) {
				return true;
			}
		}
		else if((c.getPosX() == l-1 && c.getPosY() == L-1)) { // bas droite
			if((d.getPosX() == c.getPosX()-1) || (d.getPosY() == c.getPosY()-1)) {
				return true;
			}
		}
		
		// voisines des cases sur les bords
		else if(c.getPosX() == 0) { // haut
			if((d.getPosX() == c.getPosX()+1) || (d.getPosY() == c.getPosY()+1) || (d.getPosY() == c.getPosY()-1)) {
				return true;
			}
		}
		else if(c.getPosY() == 0) { // gauche
			if((d.getPosX() == c.getPosX()-1) || (d.getPosY() == c.getPosY()+1) || (d.getPosX() == c.getPosX()+1)) {
				return true;
			}
		}
		else if(c.getPosX() == l-1) { // bas
			if((d.getPosX() == c.getPosX()-1) || (d.getPosY() == c.getPosY()-1) || (d.getPosY() == c.getPosY()+1)) {
				return true;
			}
		}
		else if(c.getPosY() == L-1) { // droite
			if((d.getPosX() == c.getPosX()-1) || (d.getPosY() == c.getPosY()-1) || (d.getPosX() == c.getPosX()+1)) {
				return true;
			}
		}
		
		else { // toutes les cases du milieu
			if((d.getPosX() == c.getPosX()-1) || (d.getPosX() == c.getPosX()+1) || 
					(d.getPosY() == c.getPosY()-1) || (d.getPosY() == c.getPosY()+1)) {
				return true;
			}
		}
		return false;
	}
}
