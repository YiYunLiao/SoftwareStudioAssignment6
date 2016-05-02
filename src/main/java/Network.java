package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		parent.noFill();
		if(arrowIsOnCircle()){
			if(parent.mousePressed){
				parent.strokeWeight(9);
				parent.stroke(120, 40, 180);
			}else{
				parent.strokeWeight(6);
				parent.stroke(180, 110, 220);
			}
		}else{
			parent.strokeWeight(3);
			parent.stroke(200, 150, 220);
		}
		parent.ellipse(centerX, centerY, radius, radius);
		
		displayCharacters();
	}
	
	
	private void displayCharacters(){
		
	}
	
	
	public boolean exist(Character ch){
		if(characters.contains(ch)){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void add(Character ch){
		characters.add(ch);
	}
	
	
	public void remove(Character ch){
		characters.remove(ch);
		ch.goBack();
	}
	
	
	public void addAll(){
		
	}
	
	
	public void clearAll(){
		for(Character ch: characters){
			ch.goBack();
		}
		characters.clear();
	}
	
	
	@SuppressWarnings("static-access")
	private boolean arrowIsOnCircle(){
		float diffXSquare = parent.sq(parent.mouseX - centerX);
		float diffYSquare = parent.sq(parent.mouseY - centerY);
		
		if(diffXSquare + diffYSquare <= parent.sq(radius)){
			return true;
		}else{
			return false;
		}
	}
	
}
