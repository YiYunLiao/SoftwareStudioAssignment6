package main.java;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

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
	private int curEpisode;
	private String label;
	private Button addAll, clear;
	private ArrayList<ArrayList<Character>> episodes;
	
	
	
	public void setup(){
		size(width, height);
		Ani.init(this);
		
		episodes = new ArrayList<ArrayList<Character>>();
		
		smooth();
		loadData();
	}

	
	public void draw(){
		background(255);
		ellipseMode(RADIUS);
		
		textAlign(CENTER, CENTER);
		fill(150, 45, 45);
		textSize(40);
		text(label, width/2, 50);
	}
	
	
	public void mouseClicked(){
		
	}
	
	
	public void keyPressed(){
		if(key == '1'){
			
		}else if(key == '2'){
			
		}else if(key == '3'){
			
		}else if(key == '4'){
			
		}else if(key == '5'){
			
		}else if(key == '6'){
			
		}else if(key == '7'){
			
		}	
	}

	
	private void loadData(){
		JSONObject data;
		JSONArray nodes, links;
		
		int maxCol = 4;
		int offset = 50;
		
		for(String file: files){
			data = loadJSONObject(path + file);
			nodes = data.getJSONArray("nodes");
			links = data.getJSONArray("links");
			
			ArrayList<Character> episode = new ArrayList<Character>();
			int row = 0, col = 0;
			
			for(int i = 0; i < nodes.size(); i++){
				JSONObject node = nodes.getJSONObject(i);
				String name = node.getString("name");
				String colour = node.getString("colour").substring(1);
				Character ch = new Character(this, name, colour, (col+2)*offset, (row+2)*offset, 15);
				episode.add(ch);
				
				if(col < maxCol-1){
					col++;
				}else{
					col = 0;
					row++;
				}
			}
			
			for(int i = 0; i < links.size(); i++){
				JSONObject link = links.getJSONObject(i);
				int source = link.getInt("source");
				int target = link.getInt("target");
				int value = link.getInt("value");
				episode.get(source).addTarget(episode.get(target), value);
			}
			
			episodes.add(episode);
		}
	}

}
