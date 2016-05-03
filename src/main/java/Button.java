package main.java;

public class Button {
	private MainApplet parent;
	private String name;
	private float x, y, width, height;
	
	// constructor of Button
	public Button(MainApplet parent, String name, float x, float y, float width, float height){
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	// display button
	public void display(){
		if(arrowIsInButton() && !parent.mousePressed){
			parent.stroke(110, 110, 110);
			parent.strokeWeight(2);
			parent.fill(60, 170, 250);
		}else if(arrowIsInButton() && parent.mousePressed){
			parent.stroke(80, 80, 80);
			parent.strokeWeight(4);
			parent.fill(0, 107, 171);
		}else{
			parent.noStroke();
			parent.fill(130, 200, 250);
		}
		parent.rect(x, y, width, height);
		parent.fill(255);
		parent.textSize(30);
		parent.text(name, x + width/2, y + height/2);
	}
	
	
	// detect whether arrow was in button
	public boolean arrowIsInButton(){
		if(parent.mouseX > x && parent.mouseX < x + width &&
			parent.mouseY > y && parent.mouseY < y + height){
			return true;
		}else{
			return false;
		}	
	}
}
