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
		if(moving){
			movingWithArrow();
		}
		if(arrowIsInCharacter()){
			if(parent.mousePressed){
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
	
	//make character move with arrow
	private void movingWithArrow(){
		curX = parent.mouseX;
		curY = parent.mouseY;
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
	
	//make character go back to network
	public void goBackToNetwork(){
		goTo(xInNetwork, yInNetwork);
	}
	
	//set character movable or not
	public void setMoving(boolean moving){
		this.moving = moving;
	}
	
	//check whether character were moving
	public boolean isMoving(){
		return moving;
	}
	
	//set destination in x-axis in network
	public void setXInNetwork(float x){
		xInNetwork = x;
	}
	
	//set destination in y-axis in network
	public void setYInNetwork(float y){
		yInNetwork = y;
	}

	//detect if arrow was on character before character turn larger
	@SuppressWarnings("static-access")
	public boolean arrowIsInCharacter(){
		if(parent.dist(parent.mouseX, parent.mouseY, curX, curY) < radius){
			return true;
		}else{
			return false;
		}
	}
	
	//check whether character were being dragged out of original place 
	@SuppressWarnings("static-access")
	public boolean isMovingOutFromOrigin(){
		if(moving && parent.dist(parent.mouseX, parent.mouseY, defaultX, defaultY) > radius+5 &&
			!network.exists(this)){
			return true;
		}else{
			return false;
		}
	}
	
	//check whether character were being dragged out of network
	@SuppressWarnings("static-access")
	public boolean isMovingOutFromNetwork(){
		if(moving && parent.dist(parent.mouseX, parent.mouseY, xInNetwork, yInNetwork) > radius+5 &&
			network.exists(this)){
			return true;
		}else{
			return false;
		}
	}
	
	//check whether character were over network
	@SuppressWarnings("static-access")
	public boolean isOverNetwork(){
		if(parent.dist(parent.mouseX, parent.mouseY, network.getX(), network.getY()) <= network.getRadius()){
			return true;
		}else{
			return false;
		}
	}

}
