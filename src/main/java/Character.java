package main.java;

import java.util.HashMap;
import java.util.Map;

import de.looksgood.ani.Ani;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name, colour;
	private float defaultX, defaultY, curX, curY, radius;
	private CharacterLabel label;
	private Map<Character, Integer> targets;


	public Character(MainApplet parent, String name, String colour, float x, float y, float radius){
		this.parent = parent;
		this.name = name;
		this.colour = colour;
		defaultX = x;
		defaultY = y;
		curX = x;
		curY = y;
		this.radius = radius;
		label = new CharacterLabel(parent, name);
		targets = new HashMap<Character, Integer>();
	}

	
	@SuppressWarnings("static-access")
	public void display(){
		parent.noStroke();
		if(arrowIsInCharacter()){
			if(parent.mousePressed){
				parent.fill(parent.unhex(colour), 255);
				parent.ellipse(curX, curY, radius+5, radius+5);
			}else{
				parent.fill(parent.unhex(colour), 180);
				parent.ellipse(curX, curY, radius+5, radius+5);
			}
		}else{
			parent.fill(parent.unhex(colour), 125);
			parent.ellipse(curX, curY, radius, radius);
		}
	}
	
	
	@SuppressWarnings("static-access")
	public boolean arrowIsInCharacter(){
		float diffXSquare = parent.sq(parent.mouseX - curX);
		float diffYSquare = parent.sq(parent.mouseY - curY);

		if(diffXSquare + diffYSquare < parent.sq(radius)){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void addTarget(Character target, int value){
		targets.put(target, value);
	}
	
	
	public Map<Character, Integer> getTargets(){
		return targets;
	}
	
	
	CharacterLabel getLabel(){
		return label;
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public float getX(){
		return curX;
	}
	
	
	public float getY(){
		return curY;
	}
	
	
	public void goTo(float x, float y){
		Ani.to(this, (float)0.5, "curX", x, Ani.QUINT_IN);
		Ani.to(this, (float)0.5, "curY", y, Ani.QUINT_IN);
	}
	
	
	public void goBack(){
		goTo(defaultX, defaultY);
	}
}
