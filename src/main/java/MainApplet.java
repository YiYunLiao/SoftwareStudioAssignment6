package main.java;

import processing.core.PApplet;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String[] files = {"starwars-episode-1-interactions.json",
							  "starwars-episode-2-interactions.json",
							  "starwars-episode-3-interactions.json",
							  "starwars-episode-4-interactions.json",
							  "starwars-episode-5-interactions.json",
							  "starwars-episode-6-interactions.json",
							  "starwars-episode-7-interactions.json"};
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		size(width, height);
		Ani.init(this);
		smooth();
		loadData();
		
	}

	public void draw() {

	}

	private void loadData(){

	}

}
