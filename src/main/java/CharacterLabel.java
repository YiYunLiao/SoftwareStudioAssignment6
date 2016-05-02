package main.java;

public class CharacterLabel {
	
	private MainApplet parent;
	private String name;
	private float labelWidth, labelHeight;
	
	public CharacterLabel(MainApplet parent, String name){
		this.parent = parent;
		this.name = name;
		labelWidth = this.name.length()*15;
		labelHeight = 25;
	}
	
	
	public void display(){
		parent.fill(20, 80, 220);
		parent.rect(parent.mouseX, parent.mouseY, labelWidth, labelHeight, 7);
		parent.textSize(15);
		parent.fill(255);
		parent.text(name, parent.mouseX + labelWidth/2 + 5, parent.mouseY + labelHeight/2);
	}
}
