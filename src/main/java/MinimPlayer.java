package main.java;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class MinimPlayer {

	//private PApplet parent;
	private Minim minim;
	private AudioPlayer song;
	
	public MinimPlayer(PApplet parent, String url){
		//this.parent = parent;
		minim = new Minim(parent);
		song = minim.loadFile(this.getClass().getResource(url).getPath());
	}
	
	public void play(){
		song.play();
	}
	
	public void loop(){
		song.loop();;
	}
}
