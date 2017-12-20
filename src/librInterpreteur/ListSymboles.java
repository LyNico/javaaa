package librInterpreteur;
import java.util.Stack;


public class ListSymboles {
	
	private Stack< Quituplet <String,Integer,Integer,Integer,Integer> > mValue;
	// on va garder en memoire la position des symboles pour faciliter leur placement
	private int charA = (int) 'A';
	private int charZ = (int) 'Z';
	
	private int randomFromLarg1;
	private int randomFromLong1;
	
	private int randomFromLarg2;
	private int randomFromLong2;

	public ListSymboles(int nbSymboles, int longueur, int largeur) {
		this.mValue = new Stack< Quituplet <String,Integer,Integer,Integer,Integer> >();
		for(int i = charA; i<charA+nbSymboles;i++) {
			if(i>charZ) {
				break;
			}
			else {
				fullRandom(longueur, largeur);
				char iIntToChar = (char) i;
				String tempCharToString = iIntToChar+"";
				Quituplet <String,Integer,Integer,Integer,Integer> temp = new Quituplet <String,Integer,Integer,Integer,Integer>(tempCharToString,
						getRandomLarg1(),
						getRandomLong1(),
						getRandomLarg2(),
						getRandomLong2());
				this.mValue.push(temp); //mValue = Stack["A", "B", "C", "D", "E", "F", "G", "H"];
				System.out.println("ON PUSH CA " + mValue.peek().getFirst());
			}
		}
	}
	public int getFirstLong() {
		return mValue.peek().getSecond();
	}
	public int getFirstLarg() {
		return mValue.peek().getThird();
	}
	public void popFirstInList() {
		mValue.pop();
	}
	public void setRandomLarg1(int larg) {
		 this.randomFromLarg1 = (int) (Math.random() * larg);
	}
	public void setRandomLong1(int longu) {
		 this.randomFromLong1 = (int) (Math.random() * longu);
	}
	public int getRandomLarg1() {
		 return this.randomFromLarg1;
	}
	public int getRandomLong1() {
		 return this.randomFromLong1;
	}
	public void setRandomLarg2(int larg) {
		 this.randomFromLarg2 = (int) (Math.random() * larg);
	}
	public void setRandomLong2(int longu) {
		 this.randomFromLong2 = (int) (Math.random() * longu);
	}
	public void fullRandom(int l, int L) {
		
		this.setRandomLarg1(L);
		this.setRandomLong1(l);
		
		this.setRandomLong2(l);
		this.setRandomLarg2(L);
		int rand1 = getRandomLarg1();
		int rand2 = getRandomLong1();
		int rand3 = getRandomLarg2();
		int rand4 = getRandomLong2();
		System.out.println("ON SET LES RANDS");
		System.out.println(" Larg 1 = "+rand1);
		System.out.println(" Long 1 = "+rand2);
		System.out.println(" Larg 2 = "+rand3);
		System.out.println(" Long 2 = "+rand4);
		while ((rand1 == rand3) && (rand2 == rand4)) {
			this.setRandomLarg1(L);
			this.setRandomLong1(l);
			
			this.setRandomLong2(l);
			this.setRandomLarg2(L);
			rand1 = getRandomLarg1();
			rand2 = getRandomLong1();
			rand3 = getRandomLarg2();
			rand4 = getRandomLong2();
		}
	}
	public int getRandomLarg2() {
		 return this.randomFromLarg2;
	}
	public int getRandomLong2() {
		 return this.randomFromLong2;
	}
	public Stack< Quituplet <String,Integer,Integer,Integer,Integer> > getValue() {
		return mValue;
	}
	
	public void setValue(Stack< Quituplet <String,Integer,Integer,Integer,Integer> > val) {
		mValue=val;
	}
	
	public boolean isSymbole(String symb) {
		if(symb == "*" || symb == "O")
			return false;
		return true;
	}
}
