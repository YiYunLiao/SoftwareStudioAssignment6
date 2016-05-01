package main.java;

public class Button {
	private MainApplet parent;
	private String name;
	private float x, y, width, height;
	
	
	public Button(MainApplet parent, String name, float x, float y, float width, float height){
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	public void display(){
		
	}
	
	
	public boolean arrowIsInButton(){		
		if(parent.mouseX > x && parent.mouseX < x + width &&
				parent.mouseY > y && parent.mouseY < y + height){
				return true;
			}else{
				return false;
			}	
	}
}
