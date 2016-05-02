package main.java;

import java.util.ArrayList;
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
	
	//constructor of Network
	public Network(PApplet parent, float x, float y, float radius){
		this.parent = parent;
		centerX = x;
		centerY = y;
		this.radius = radius;
		characters = new ArrayList<Character>();
	}
	
	//display the circle and characters on circle
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
	
	//display characters
	private void displayCharacters(){
		parent.stroke(200, 40, 180);
		for(Character ch: characters){
			Map<Character, Integer> targets = ch.getTargets();
			for(Character target: targets.keySet()){
				if(characters.contains(target)){
					parent.strokeWeight((float)targets.get(target)/4);
					parent.beginShape();
					parent.vertex(ch.getX(), ch.getY());
					parent.quadraticVertex(centerX, centerY, target.getX(), target.getY());
					parent.endShape();
				}
			}
		}
	}
	
	//update the characters on circle when clicking any characters
	@SuppressWarnings("static-access")
	private void update(){
		int num = characters.size();
		for(int i=0; i<num; i++){
			float nextX = centerX + radius*parent.cos(parent.TWO_PI/num*i);
			float nextY = centerY + radius*parent.sin(parent.TWO_PI/num*i);
			characters.get(i).goTo(nextX, nextY);
		}
	}
	
	//check if character was on the circle
	public boolean exists(Character ch){
		if(characters.contains(ch)){
			return true;
		}else{
			return false;
		}
	}
	
	//add character to circle and update circle
	public void add(Character ch){
		characters.add(ch);
		update();
	}
	
	//remove character in the circle and update circle
	public void remove(Character ch){
		characters.remove(ch);
		ch.goBack();
		update();
	}
	
	//add all characters to circle
	public void addAll(ArrayList<Character> chs){
		for(Character ch: chs){
			if(!characters.contains(ch)){
				add(ch);
			}
		}
	}
	
	//remove all characters in circle
	public void clearAll(){
		for(Character ch: characters){
			ch.goBack();
		}
		characters.clear();
		update();
	}
	
	//detect if arrow was in circle
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
