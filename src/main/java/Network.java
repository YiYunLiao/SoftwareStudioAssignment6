package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private float centerX, centerY, radius;
	private ArrayList<Character> characters;

	public Network(PApplet parent, float x, float y, float radius){
		this.parent = parent;
		centerX = x;
		centerY = y;
		this.radius = radius;
		characters = new ArrayList<Character>();
	}

	public void display(){
		
	}
	
	
	public void addAll(){
		
	}
	
	
	public void clearAll(){
		
	}
	
	
	public boolean exist(Character ch){
		if(characters.contains(ch)){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void add(Character ch){
		
	}
	
	
	public void remove(Character ch){
		
	}
	
	
	@SuppressWarnings("static-access")
	public boolean arrowIsOnCircle(){
		float diffXSquare = parent.sq(parent.mouseX - centerX);
		float diffYSquare = parent.sq(parent.mouseY - centerY);
		
		if(diffXSquare + diffYSquare <= parent.sq(radius)){
			return true;
		}else{
			return false;
		}
	}
	
}
