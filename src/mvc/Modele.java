package mvc;

import java.util.Observable;
import java.util.Vector;

import librInterpreteur.Case;
import librInterpreteur.Grille;
import librInterpreteur.Rail;

public class Modele extends Observable {
	
	private Grille grid;
	private Vector<Rail> rr;
	private int headRail;
	
	public Modele(int gridLongueur, int gridLargeur) {
		grid = createGrid(gridLongueur,gridLargeur);
		rr = new Vector<Rail>();
		Rail nouv = new Rail();
		rr.addElement(nouv);
		setHeadRail(0);
		System.out.println("Creation d'un rail , number : "+this.getHeadRail());
	}
	public void changestate(Case n){
        if(n.getContent() == "O"){
        	n.setContent("O");
        }
        else {
        	n.setContent("*");
        }
        setChanged(); // APPEL LA FONCTION OBSERVEUR UPDATE
        notifyObservers();
    }
	public void fullReset() {
		grid.resetAll();
		rr.removeAllElements();
		Rail nouv = new Rail();
		rr.addElement(nouv);
		setHeadRail(0);
	}
	public Grille createGrid(int longueur, int larg) {
		grid = new Grille(longueur,larg);
		return grid;
	}
	public Grille getGrid() {
		return grid;
	}
	public void deleteRail(int indexRail) {
		Vector<Case> tempVector = rr.elementAt(indexRail).getVectorRail();
		for(int i = 0; i < tempVector.size() ; i++) {
			Case tempCase = tempVector.elementAt(i);
			if(!grid.getSymbs().isSymbole(tempCase.getNewCase().getText())) {
				tempCase.setHoovered(false);
				tempCase.setContent("O");
				tempCase.getNewCase().setText("O");
			}
		}
		rr.elementAt(indexRail).deleteRail();
		rr.elementAt(indexRail).setHead(0);
	}
	public Rail getRr(int n) {
		return rr.elementAt(n);
	}
	public void addNewRail() {
		Rail nouv = new Rail();
		rr.addElement(nouv);
		this.setHeadRail(this.getHeadRail()+1);
		System.out.println("Creation d'un rail , number : "+this.getHeadRail());
	}
	public int getHeadRail() {
		return headRail;
	}
	public void setHeadRail(int headRail) {
		this.headRail = headRail;
	}
	public boolean railAlreadyExist(Rail r) {
		if(this.rr.contains(r)) {
			return true;
		}
		return false;
	}
	public int getSizeOfVectRails() {
		return rr.size();
	}
}
