package main.java;

import java.util.HashMap;
import java.util.Map;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name, colour;
	private float defaultX, defaultY, curX, curY, radius;
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
		targets = new HashMap<Character, Integer>();
	}

	public void display(){
		parent.noStroke();

	}
	
	
	@SuppressWarnings("static-access")
	public boolean arrowIsInCircle(){
		float diffX = parent.mouseX - curX;
		float diffY = parent.mouseY - curY;

		if(parent.sqrt(parent.sq(diffX) + parent.sq(diffY)) < radius){
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
	
	
	public String getName(){
		return name;
	}
	
	
	public float getX(){
		return curX;
	}
	
	
	public float getY(){
		return curY;
	}
}
