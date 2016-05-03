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
	private int curEpisode, nextEpisode;
	private String topLabel;
	private Button addAll, clear;
	private ArrayList<ArrayList<Character>> episodes;
	private CharacterLabel chLabel;
	private Network network;
	
	private MinimPlayer bgm;
	private MusicClip changeEpisode, goBackMusic;
	
	//initial MainApplet
	public void setup(){
		size(width, height);
		Ani.init(this);
		
		addAll = new Button(this, "ADD ALL", 920, 100, 200, 50);
		clear = new Button(this, "CLEAR", 920, 200, 200, 50);
		
		episodes = new ArrayList<ArrayList<Character>>();
		chLabel = null;
		
		network = new Network(this, width/2, height/2, height/2-100);
		
		curEpisode = 1;
		topLabel = "Star Wars " + String.valueOf(curEpisode);
		
		smooth();
		loadData();

		//bgm = new MinimPlayer(this, "./music/bgMusic.wav");
		//bgm.loop();
		changeEpisode = new MusicClip("./music/changeEpisode.wav");
		goBackMusic = new MusicClip("./music/goBack.wav");
	}

	//draw the applet
	public void draw(){
		background(255);
		ellipseMode(RADIUS);
		
		drawTopLabel();
		
		addAll.display();
		clear.display();
		
		network.display();
		
		chLabel = null;
		for(Character ch: episodes.get(curEpisode-1)){
			ch.display();
			if(ch.arrowIsInCharacter()){
				chLabel = ch.getLabel();
			}
		}
		if(chLabel != null){
			chLabel.display();
		}
	}
	
	
	private void drawTopLabel(){
		textAlign(CENTER, CENTER);
		if(isChangingEpisode()){
			fill(200, 5, 50);
			textSize(60);
		}else{
			fill(120, 40, 30);
			textSize(40);
		}
		text(topLabel, width/2, 50);
	}
	
	
	public void mouseReleased(){
		for(Character ch: episodes.get(curEpisode-1)){
			if(ch.isMovingInNetwork()){
				if(network.exists(ch)){
					goBackMusic.play();
					ch.goBackToNetwork();
				}else{
					network.add(ch);
				}
			}else if(ch.isMoving()){
				if(network.exists(ch)){
					network.remove(ch);
				}else{
					goBackMusic.play();
					ch.goBack();
				}
			}
			ch.setMoving(false);
		}
	}
	
	
	public void mousePressed(){
		for(Character ch: episodes.get(curEpisode-1)){
			if(ch.arrowIsInCharacter()){
				ch.setMoving(true);
			}
		}
	}
	
	//implement mouseClicked
	public void mouseClicked(){
		if(addAll.arrowIsInButton()){
			network.addAll(episodes.get(curEpisode-1));
		}else if(clear.arrowIsInButton()){
			network.clearAll();
		}
		for(Character ch: episodes.get(curEpisode-1)){
			if(ch.arrowIsInCharacter()){
				if(!network.exists(ch)){
					network.add(ch);
				}else{
					network.remove(ch);
				}
			}
		}
	}
	
	//implement keyPressed
	public void keyPressed(){
		switch(key){
			case '1':
				nextEpisode = 1;
				break;
			case '2':
				nextEpisode = 2;
				break;
			case '3':
				nextEpisode = 3;
				break;
			case '4':
				nextEpisode = 4;
				break;
			case '5':
				nextEpisode = 5;
				break;
			case '6':
				nextEpisode = 6;
				break;
			case '7':
				nextEpisode = 7;
				break;
			default:
				nextEpisode = 0;
		}
	}
	
	//implement keyReleased
	public void keyReleased(){
		if(isChangingEpisode()){
			setEpisode(nextEpisode);
		}
		nextEpisode = 0;
	}
	
	//set the episode and update label of applet
	private void setEpisode(int episode){
		if(!network.containsCharacters()){
			changeEpisode.play();
		}
		curEpisode = episode;
		network.clearAll();
		topLabel = "Star Wars " + String.valueOf(curEpisode);
	}
	
	
	private boolean isChangingEpisode(){
		if(nextEpisode >= 1 && nextEpisode <=7 && curEpisode != nextEpisode){
			return true;
		}else{
			return false;
		}
	}

	//load all data
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
				Character ch = new Character(this, network, name, colour, (col+2)*offset, (row+2)*offset, 15);
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
