package librInterpreteur;

import java.util.Vector;

public class Rail {
	Vector<Case> v;
	private int head;
	
	public Rail() {
		v = new Vector<Case>();
		setHead(0);
	}
	
	public void addCase(Case c) {
		v.addElement(c);
		setHead(v.size()-1);
		//System.out.println(head+" ajout de la case : "+v.elementAt(head).getContent());
	}
	public void deleteCase(Case c) {
		v.remove(c);
		setHead(v.size()-1);
		//System.out.println(head+" suppression de la case : "+v.elementAt(head).getContent());
	}
	public Case getCaseInVect(int pos) {
		return v.get(pos);
	}
	
	public void deleteRail() {
		v.removeAllElements();
		setHead(0);
	}

	public int getHead() {
		return head;
	}
	
	public Vector<Case> getVectorRail() {
		return v;
	}

	public void setHead(int head) {
		this.head = head;
	}
	
	public boolean isInRail(Case c) {
		if(v.contains(c)) {
			return true;
		}
		return false;
	}
	
	public String railTailStr() {
		return v.get(0).getContent();
	}
	
	public String railHeadStr() {
		return v.get(head).getContent();
	}
	
	public Case railTail() {
		return v.get(0);
	}
	
	public Case railHead() {
		return v.get(head);
	}
}
