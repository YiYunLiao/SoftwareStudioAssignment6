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
	private Network network;
	private String name, colour;
	private float defaultX, defaultY, curX, curY, radius;
	private CharacterLabel label;
	private Map<Character, Integer> targets;
	private boolean moving;
	private float xInNetwork, yInNetwork;

	//constructor of character
	public Character(MainApplet parent, Network network, String name, String colour, float x, float y, float radius){
		this.parent = parent;
		this.network = network;
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

	//display the character
	@SuppressWarnings("static-access")
	public void display(){
		parent.noStroke();
		if(isMoving()){
			movingWithArrow();
		}
		if(arrowIsInCharacter()){
			if(isMoving()){
				parent.fill(parent.unhex(colour), 255);
				parent.ellipse(parent.mouseX, parent.mouseY, radius+5, radius+5);
			}else{
				parent.fill(parent.unhex(colour), 180);
				parent.ellipse(curX, curY, radius+5, radius+5);
			}
		}else{
			parent.fill(parent.unhex(colour), 125);
			parent.ellipse(curX, curY, radius, radius);
		}
	}
	
	
	private void movingWithArrow(){
		curX = parent.mouseX;
		curY = parent.mouseY;
	}
	
	//detect if arrow was on character
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
	
	//add a target character to source character with a value
	//representing times they interact
	public void addTarget(Character target, int value){
		targets.put(target, value);
	}
	
	//get all target characters of character
	public Map<Character, Integer> getTargets(){
		return targets;
	}
	
	//get label of character
	CharacterLabel getLabel(){
		return label;
	}
	
	//get name of character
	public String getName(){
		return name;
	}
	
	//get destination in x-axis of character
	public float getX(){
		return curX;
	}
	
	//get destination in y-axis of character
	public float getY(){
		return curY;
	}
	
	//move character to new place(x, y)
	public void goTo(float x, float y){
		Ani.to(this, (float)0.5, "curX", x, Ani.QUINT_IN);
		Ani.to(this, (float)0.5, "curY", y, Ani.QUINT_IN);
	}
	
	//make character to original place
	public void goBack(){
		goTo(defaultX, defaultY);
	}
	
	
	public void goBackToNetwork(){
		goTo(xInNetwork, yInNetwork);
	}
	
	
	public void setMoving(boolean moving){
		this.moving = moving;
	}
	
	
	public boolean isMoving(){
		return moving;
	}
	
	
	public boolean isMovingInNetwork(){
		if(isMoving() && network.arrowIsInNetwork()){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void setXInNetwork(float x){
		xInNetwork = x;
	}
	
	
	public void setYInNetwork(float y){
		yInNetwork = y;
	}

}
