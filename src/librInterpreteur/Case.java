package librInterpreteur;

import javafx.scene.control.Button;

public class Case {
	
	
	private int posX;
	private int posY;
	private String content;
	private Button newCase;
	private boolean linked;
	private boolean Hoovered;
	
	public Case(String s, int x, int y) {
		newCase = new Button(s);
		setContent(s);
		setPosX(x);
		setPosY(y);
		setLinked(false);
		setHoovered(false);
	}

	public Button getNewCase() {
		return newCase;
	}
/*
	public void setNewCase(String str) {
		this.newCase = new Button(str);
	}
*/
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String s) {
		this.content = s;
		this.newCase.setText(s);
	}

	public boolean isLinked() {
		return linked;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}

	public boolean isHoovered() {
		return Hoovered;
	}

	public void setHoovered(boolean hoovered) {
		Hoovered = hoovered;
	}
	
}
