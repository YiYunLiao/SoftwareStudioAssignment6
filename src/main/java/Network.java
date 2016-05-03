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
	private MusicClip addMusic, removeMusic, buttonMusic;
	
	//constructor of Network
	public Network(PApplet parent, float x, float y, float radius){
		this.parent = parent;
		centerX = x;
		centerY = y;
		this.radius = radius;
		characters = new ArrayList<Character>();
		
		addMusic = new MusicClip("./music/add.wav");
		removeMusic = new MusicClip("./music/remove.wav");
		buttonMusic = new MusicClip("./music/button.wav");
	}
	
	//display the circle and characters on circle
	public void display(){
		parent.noFill();
		if(arrowIsInNetwork()){
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
			characters.get(i).setXInNetwork(nextX);
			characters.get(i).setYInNetwork(nextY);
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
		addMusic.play();
		characters.add(ch);
		update();
	}
	
	//remove character in the circle and update circle
	public void remove(Character ch){
		removeMusic.play();
		characters.remove(ch);
		ch.goBack();
		update();
	}
	
	//add all characters to circle
	public void addAll(ArrayList<Character> chs){
		if(chs.size() != characters.size()){
			addMusic.play();
			for(Character ch: chs){
				if(!characters.contains(ch)){
					add(ch);
				}
			}
		}else{
			buttonMusic.play();
		}
	}
	
	//remove all characters in circle
	public void clearAll(){
		if(containsCharacters()){
			removeMusic.play();
			for(Character ch: characters){
				ch.goBack();
			}
			characters.clear();
			update();
		}else{
			buttonMusic.play();
		}
	}
	
	//check whether network contained characters
	public boolean containsCharacters(){
		if(characters.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	//get center of network in x-axis
	public float getX(){
		return centerX;
	}
	
	//get center of network in y-axis
	public float getY(){
		return centerY;
	}
	
	//get radius of network
	public float getRadius(){
		return radius;
	}
	
	//detect if arrow was in circle
	@SuppressWarnings("static-access")
	public boolean arrowIsInNetwork(){
		if(parent.dist(parent.mouseX, parent.mouseY, centerX, centerY) <= radius){
			return true;
		}else{
			return false;
		}
	}
	
}
